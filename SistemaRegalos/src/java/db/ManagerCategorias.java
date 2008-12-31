/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package db;

import controladores.CategoriaJpaController;
import entidades.Categoria;
import java.util.ArrayList;
import java.util.List;
import vo.CategoriaVO;

/**
 *
 * @author Administrador
 */
public class ManagerCategorias {

    private CategoriaJpaController categoriaController;
    
    public ManagerCategorias() {
        categoriaController = new CategoriaJpaController();

    }

    public List<CategoriaVO> getCategorias() {
        List<Categoria> categorias = categoriaController.findAll();
        List<CategoriaVO> categoriasVO = new ArrayList<CategoriaVO>();
        for(Categoria cat : categorias){
            categoriasVO.add(cat.getVO());
        }
        return categoriasVO;
    }

}
