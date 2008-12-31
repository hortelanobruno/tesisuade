/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import controladores.MercaderiaJpaController;
import entidades.Mercaderia;
import java.util.ArrayList;
import java.util.List;
import vo.MercaderiaVO;

/**
 *
 * @author Administrador
 */
public class ManagerMercaderias {

    private MercaderiaJpaController mercaderiaController;

    public ManagerMercaderias() {
        mercaderiaController = new MercaderiaJpaController();
    }

    public String createMercaderia(MercaderiaVO mercaderiaVO) {
        Mercaderia mercaderia = new Mercaderia();
        mercaderia.setVO(mercaderiaVO);
        mercaderiaController.create(mercaderia);
        return "ok";
    }

    public List<MercaderiaVO> getMercaderias() {
        List<Mercaderia> mercaderias = mercaderiaController.findMercaderiaEntities();
        List<MercaderiaVO> mercaderiasVO = new ArrayList<MercaderiaVO>();
        for(Mercaderia mercaderia : mercaderias){
            mercaderiasVO.add(mercaderia.getVO());
        }
        return mercaderiasVO;
    }

    public String removeMercaderia(String mercaderia) {
        
        return "ok";
    }


}
