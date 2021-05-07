/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.categorias.dao;

import com.comunidad.simplecatproductorest.categorias.dao.vo.CategoriasVo;
import java.util.List;

/**
 *
 * @author Santiago
 */
public interface CategoriaDao {

    List<CategoriasVo> lista(CategoriasVo categoriasVo) throws Exception;
}
