/*
 * Christopher Deckers (chrriis@nextencia.net)
 * http://www.nextencia.net
 *
 * See the file "readme.txt" for information on usage and redistribution of
 * this file, and for a DISCLAIMER OF ALL WARRANTIES.
 */
package chrriis.dj.nativeswing;

import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.AWTEventListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerEvent;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.awt.event.HierarchyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;
import java.util.Arrays;

import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.RootPaneContainer;
import javax.swing.SwingUtilities;

import chrriis.common.Filter;
import chrriis.common.UIUtils;
import chrriis.dj.nativeswing.NativeComponentWrapper.NativeComponentHolder;

import com.sun.jna.examples.WindowUtils;


/**
 * @author Christopher Deckers
 */
class NativeComponentProxyPanel extends NativeComponentProxy {

  private static final boolean IS_DEBUGGING_SHAPE = Boolean.parseBoolean(System.getProperty("nativeswing.components.debug.printshapecomputing"));

  private boolean isProxiedFiliation;

  private AWTEventListener shapeAdjustmentEventListener;

  private boolean isDestructionOnFinalization;
  private boolean isVisibilityConstrained;

  NativeComponentProxyPanel(NativeComponentWrapper nativeComponentWrapper, boolean isVisibilityConstrained, boolean isDestructionOnFinalization, boolean isProxiedFiliation) {
    super(nativeComponentWrapper);
    this.isDestructionOnFinalization = isDestructionOnFinalization;
    this.isVisibilityConstrained = isVisibilityConstrained;
    hierarchyListener = new HierarchyListener() {
      public void hierarchyChanged(HierarchyEvent e) {
        long changeFlags = e.getChangeFlags();
        if((changeFlags & (HierarchyEvent.SHOWING_CHANGED)) != 0) {
          if(NativeComponentProxyPanel.this.isVisibilityConstrained) {
            adjustEmbeddedPanelShape();
          } else {
            adjustEmbeddedPanelBounds();
          }
        }
      }
    };
    if(isVisibilityConstrained) {
      shapeAdjustmentEventListener = new AWTEventListener() {
        public void eventDispatched(AWTEvent e) {
          boolean isAdjustingShape = false;
          switch(e.getID()) {
            case ContainerEvent.COMPONENT_ADDED:
            case ContainerEvent.COMPONENT_REMOVED:
            case ComponentEvent.COMPONENT_RESIZED:
            case ComponentEvent.COMPONENT_MOVED:
              isAdjustingShape = true;
              break;
            case ComponentEvent.COMPONENT_SHOWN:
            case ComponentEvent.COMPONENT_HIDDEN:
              if(e.getSource() instanceof Window) {
                isAdjustingShape = true;
              }
              break;
          }
          if(isAdjustingShape) {
            if(NativeComponentProxyPanel.this.nativeComponentWrapper.getNativeComponentProxy() == NativeComponentProxyPanel.this) {
              adjustEmbeddedPanelShape();
            }
          }
        }
      };
    }
    this.isProxiedFiliation = isProxiedFiliation;
  }

  private HierarchyBoundsListener hierarchyBoundsListener = new HierarchyBoundsListener() {
    public void ancestorMoved(HierarchyEvent e) {
      Component component = e.getChanged();
      if(component instanceof Window) {
        return;
      }
      adjustEmbeddedPanelBounds();
    }
    public void ancestorResized(HierarchyEvent e) {
      adjustEmbeddedPanelBounds();
    }
  };

  private MouseAdapter mouseListener = new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
      adjustFocus();
    }
    protected void adjustFocus() {
      for(Component parent = NativeComponentProxyPanel.this; parent != null && !(parent instanceof Window); parent = parent.getParent()) {
        if(parent instanceof JInternalFrame) {
          Window windowAncestor = SwingUtilities.getWindowAncestor(NativeComponentProxyPanel.this);
          if(windowAncestor != null) {
            boolean focusableWindowState = windowAncestor.getFocusableWindowState();
            windowAncestor.setFocusableWindowState(false);
            try {
              ((JInternalFrame)parent).setSelected(true);
            } catch (PropertyVetoException e1) {
            }
            windowAncestor.setFocusableWindowState(focusableWindowState);
          }
          break;
        }
      }
    }
  };


  private void adjustEmbeddedPanelBounds() {
    if(embeddedPanel == null) {
      return;
    }
    if(!isVisibilityConstrained) {
      boolean isShowing = isShowing();
      if(isShowing != embeddedPanel.isVisible()) {
        embeddedPanel.setVisible(isShowing);
      }
    }
    Point location = SwingUtilities.convertPoint(this, new Point(0, 0), embeddedPanel.getParent());
    Dimension size = getSize();
    Rectangle bounds = new Rectangle(location.x, location.y, size.width, size.height);
    if(!embeddedPanel.getBounds().equals(bounds)) {
      embeddedPanel.setBounds(bounds);
      embeddedPanel.invalidate();
      embeddedPanel.validate();
      embeddedPanel.repaint();
      if(isVisibilityConstrained) {
        adjustEmbeddedPanelShape();
      }
    }
  }

  private volatile boolean isInvoking;

  private void adjustEmbeddedPanelShape() {
    if(isInvoking) {
      return;
    }
    isInvoking = true;
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        isInvoking = false;
        adjustEmbeddedPanelShape_();
      }
    });
  }

  private Rectangle[] lastArea = new Rectangle[] {new Rectangle(getSize())};

  private void adjustEmbeddedPanelShape_() {
    if(embeddedPanel == null) {
      return;
    }
    Rectangle[] rectangles = computePeerShapeArea();
    if(Arrays.equals(lastArea, rectangles)) {
      return;
    }
    lastArea = rectangles;
    if(rectangles.length == 0) {
      embeddedPanel.setVisible(false);
    } else {
      if(!embeddedPanel.isVisible()) {
        embeddedPanel.setVisible(true);
      }
      WindowUtils.setComponentMask(embeddedPanel, rectangles);
//      nativeComponent.repaintNativeControl();
    }
  }

  private Rectangle[] computePeerShapeArea() {
    if(IS_DEBUGGING_SHAPE) {
      System.err.println("Computing shape: [" + NativeComponentProxyPanel.this.getWidth() + "x" + NativeComponentProxyPanel.this.getHeight() + "] " + nativeComponentWrapper.getComponentDescription());
    }
    Rectangle[] shape = UIUtils.getComponentVisibleArea(this, new Filter<Component>() {
      public boolean accept(Component c) {
        boolean isAccepted = !(c instanceof EmbeddedPanel);
        if(IS_DEBUGGING_SHAPE && isAccepted) {
          Rectangle intersectionRectangle = SwingUtilities.convertRectangle(c, new Rectangle(c.getSize()), NativeComponentProxyPanel.this).intersection(new Rectangle(NativeComponentProxyPanel.this.getSize()));
          if(!intersectionRectangle.isEmpty()) {
            System.err.println("  -> Subtracting [" + intersectionRectangle.x + "," + intersectionRectangle.y + "," + intersectionRectangle.width + "x" + intersectionRectangle.height + "] " + c);
          }
        }
        return isAccepted;
      }
    }, false);
    if(shape.length == 0) {
      return shape;
    }
    Window windowAncestor = SwingUtilities.getWindowAncestor(this);
    Rectangle tempRectangle = new Rectangle();
    for(Window window: NativeSwing.getWindows()) {
      if(window.isVisible()) {
        for(Window owner = window; (owner=owner.getOwner()) != null; ) {
          if(owner == windowAncestor) {
            tempRectangle.setBounds(0, 0, window.getWidth(), window.getHeight());
            shape = UIUtils.subtract(shape, SwingUtilities.convertRectangle(window, tempRectangle, this));
            break;
          }
        }
      }
    }
    return shape;
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    // On Linux, a JInternalFrame brought to the front may generate a paint call only to that one.
    // We need to adjust the shape of the frames that go to the back as well.
    for(NativeComponentWrapper ncw: NativeSwing.getNativeComponentWrappers()) {
      NativeComponentProxy nativeComponentProxy = ncw.getNativeComponentProxy();
      if(nativeComponentProxy instanceof NativeComponentProxyPanel) {
        if(((NativeComponentProxyPanel)nativeComponentProxy).isVisibilityConstrained) {
          ((NativeComponentProxyPanel)nativeComponentProxy).adjustEmbeddedPanelShape();
        }
      }
    }
  }

  @SuppressWarnings("deprecation")
  @Override
  public void reshape(int x, int y, int w, int h) {
    if(x == getX() && y == getY() && w == getWidth() && h == getHeight()) {
      return;
    }
    super.reshape(x, y, w, h);
    adjustEmbeddedPanelBounds();
  }

  private static class EmbeddedPanel extends Panel implements NativeComponentHolder {

    public EmbeddedPanel() {
      super(new BorderLayout());
    }

    @Override
    public boolean contains(int x, int y) {
      return false;
    }

    @Override
    public boolean contains(Point p) {
      return false;
    }

  }

  private EmbeddedPanel embeddedPanel;
  private HierarchyListener hierarchyListener;

  @Override
  public void addNotify() {
    super.addNotify();
    if(hierarchyListener != null) {
      addHierarchyListener(hierarchyListener);
    }
    if(shapeAdjustmentEventListener != null) {
      Toolkit.getDefaultToolkit().addAWTEventListener(shapeAdjustmentEventListener, AWTEvent.COMPONENT_EVENT_MASK | AWTEvent.CONTAINER_EVENT_MASK);
    }
    boolean isEmbeddedPanelCreated = embeddedPanel != null;
    if(!isEmbeddedPanelCreated) {
      embeddedPanel = new EmbeddedPanel();
      embeddedPanel.add(nativeComponentWrapper.getNativeComponent(), BorderLayout.CENTER);
    }
    adjustEmbeddedPanelBounds();
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        addHierarchyBoundsListener(hierarchyBoundsListener);
        adjustEmbeddedPanelBounds();
      }
    });
    nativeComponentWrapper.getNativeComponent().addMouseListener(mouseListener);
    if(!isEmbeddedPanelCreated) {
      for(Component parent = this; (parent = parent.getParent()) != null; ) {
        if(!parent.isLightweight() && parent instanceof RootPaneContainer) {
          if(isProxiedFiliation) {
            JLayeredPane layeredPane = ((RootPaneContainer)parent).getLayeredPane();
            layeredPane.setLayer(embeddedPanel, Integer.MIN_VALUE);
            layeredPane.add(embeddedPanel);
          } else {
            add(embeddedPanel, BorderLayout.CENTER);
          }
          return;
        }
      }
      throw new IllegalStateException("The window ancestor must be a root pane container!");
    }
  }

  @Override
  public void removeNotify() {
    super.removeNotify();
    if(hierarchyListener != null) {
      removeHierarchyListener(hierarchyListener);
    }
    if(shapeAdjustmentEventListener != null) {
      Toolkit.getDefaultToolkit().removeAWTEventListener(shapeAdjustmentEventListener);
    }
    if(isDestructionOnFinalization) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          removeHierarchyBoundsListener(hierarchyBoundsListener);
          adjustEmbeddedPanelBounds();
        }
      });
      nativeComponentWrapper.getNativeComponent().removeMouseListener(mouseListener);
      if(isVisibilityConstrained) {
        adjustEmbeddedPanelShape();
      } else {
        adjustEmbeddedPanelBounds();
      }
      return;
    }
    dispose();
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    if(embeddedPanel != null) {
      SwingUtilities.invokeLater(new Runnable() {
        public void run() {
          dispose();
        }
      });
    }
  }

  @Override
  public void dispose() {
    if(embeddedPanel == null) {
      return;
    }
    EmbeddedPanel panel = embeddedPanel;
    embeddedPanel = null;
    Container parent = panel.getParent();
    if(parent != null) {
      parent.remove(panel);
      parent.invalidate();
      parent.validate();
      parent.repaint();
    }
  }

  @Override
  public Dimension getPreferredSize() {
    if(embeddedPanel != null) {
      return embeddedPanel.getPreferredSize();
    }
    return super.getPreferredSize();
  }

}
