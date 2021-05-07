/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.catalogo.service;

import com.comunidad.simplecatproductorest.departamentos.dao.vo.DepartamentosVo;
import java.util.List;

/**
 *
 * @author Santiago
 */
public interface CatalogoService {

    DepartamentosVo getDepartamentosVo(int idDep, int idCat, int idProd, int idSubprod) throws Exception;

    List<DepartamentosVo> lista() throws Exception;

}
