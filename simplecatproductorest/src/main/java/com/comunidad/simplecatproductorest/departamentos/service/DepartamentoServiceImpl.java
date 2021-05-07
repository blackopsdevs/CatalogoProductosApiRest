package com.comunidad.simplecatproductorest.departamentos.service;

import com.comunidad.simplecatproductorest.departamentos.dao.DepartamentoDao;
import com.comunidad.simplecatproductorest.departamentos.dao.vo.DepartamentosVo;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepartamentoServiceImpl implements DepartamentoService {

    @Autowired
    private DepartamentoDao departamentoDao;

    @Override
    public List<DepartamentosVo> lista(DepartamentosVo departamentoVo) throws Exception {
        return departamentoDao.lista(departamentoVo);
    }

    @Override
    public DepartamentosVo buscar(int idDep) throws Exception {
        return departamentoDao.buscar(idDep);
    }

}
