package com.comunidad.simplecatproductorest.departamentos.service;

import com.comunidad.simplecatproductorest.departamentos.dao.vo.DepartamentosVo;
import java.util.List;

public interface DepartamentoService {

    List<DepartamentosVo> lista(DepartamentosVo departamentoVo) throws Exception;

    DepartamentosVo buscar(int idDep) throws Exception;
}
