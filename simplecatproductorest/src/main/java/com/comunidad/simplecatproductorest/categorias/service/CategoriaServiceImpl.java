/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.categorias.service;

import com.comunidad.simplecatproductorest.categorias.dao.CategoriaDao;
import com.comunidad.simplecatproductorest.categorias.dao.vo.CategoriasVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Santiago
 */
@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaDao categoriaDao;

    @Override
    public List<CategoriasVo> lista(CategoriasVo categoriasVo) throws Exception {
        return categoriaDao.lista(categoriasVo);
    }

    public void setCategoriaDao(CategoriaDao categoriaDao) {
        this.categoriaDao = categoriaDao;
    }

}
