/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.subproductos.service;

import com.comunidad.simplecatproductorest.subproductos.dao.DetalleSubprodDao;
import com.comunidad.simplecatproductorest.subproductos.dao.vo.DetalleSubprodVo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santiago
 */
@Service
public class DetalleSubprodServiceImpl implements DetalleSubprodService {

    @Autowired
    private DetalleSubprodDao detalleSubprodDao;

    @Override
    public List<DetalleSubprodVo> lista(DetalleSubprodVo detalleSubprodVo) throws Exception {
        return detalleSubprodDao.lista(detalleSubprodVo);
    }

    public void setDetalleSubprodDao(DetalleSubprodDao detalleSubprodDao) {
        this.detalleSubprodDao = detalleSubprodDao;
    }

}
