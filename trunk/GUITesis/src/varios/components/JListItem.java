/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package varios.components;

import javax.swing.ImageIcon;

/**
 *
 * @author Admin
 */
public class JListItem {

  private String title;

  private String imagePath;

  private ImageIcon image;

  public JListItem(String title, String imagePath) {
    this.title = title;
    this.imagePath = imagePath;
  }

  public void setTitle(String title){
      this.title = title;
  }
  
  public String getTitle() {
    return title;
  }

  public ImageIcon getImage() {
    if (image == null) {
      image = new ImageIcon(imagePath);
    }
    return image;
  }

  // Override standard toString method to give a useful result
  public String toString() {
    return title;
  }

}
