/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.productos.dao;

import com.comunidad.simplecatproductorest.productos.dao.vo.ProductosVo;
import java.util.List;

/**
 *
 * @author Santiago
 */
public interface ProductoDao {

    List<ProductosVo> lista(ProductosVo productosVo) throws Exception;
}
