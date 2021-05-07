package com.comunidad.simplecatproductorest.departamentos.dao;

import com.comunidad.simplecatproductorest.departamentos.dao.vo.DepartamentosVo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

import com.comunidad.simplecatproductorest.common.DateUtils;
import com.comunidad.simplecatproductorest.jdbc.JdbcDao;
import com.comunidad.simplecatproductorest.jdbc.JdbcQueryParameters;

public class DepartamentoDaoImpl extends JdbcDao implements DepartamentoDao {

    private static final Logger log = Logger.getLogger(DepartamentoDaoImpl.class);

    private JdbcQueryParameters qryTodo;
    private String qryBuscar;

    private static RowMapper<DepartamentosVo> MAPPER_DEPARTAMENTO = (ResultSet rs, int rowNum) -> {
        DepartamentosVo obj = new DepartamentosVo();
        obj.setId(rs.getInt("ID"));
        obj.setNombre(rs.getString("NOMBRE"));
        obj.setDescripcion(rs.getString("DESCR"));
        obj.setPath(rs.getString("PATH"));
        obj.setStatus(rs.getInt("STATUS_FLAG"));
        obj.setFechaExpiracion(rs.getDate("EXPIRY_DT"));
        obj.setFechaUltActual(rs.getDate("LAST_UPDATE_DT"));
        obj.setFechaAlta(rs.getDate("START_DATE"));
        obj.setUserUltActual(rs.getString("LAST_UPDATE_USER"));
        obj.setBorrado(rs.getInt("DEL_LOGIC"));
        return obj;
    };

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public List<DepartamentosVo> lista(DepartamentosVo departamentoVo) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("listDepartamentoVo...");
        }

        StringBuffer stb = new StringBuffer(qryTodo.getSqlBase());
        ArrayList parameters = new ArrayList();
        Properties params = qryTodo.getOptionalParameters();

        if (departamentoVo != null) {
            if (departamentoVo.getId() != 0) {
                stb.append(" ").append(params.get("id"));
                parameters.add(departamentoVo.getId());
            }
            if (departamentoVo.getStatus() != 0) {
                stb.append(" ").append(params.get("estatus"));
                parameters.add(departamentoVo.getStatus());
            }
        }
        if (qryTodo.getSqlLast() != null && !qryTodo.getSqlLast().equals("")) {
            stb.append(" ").append(qryTodo.getSqlLast());
        }

        Object[] args = parameters.toArray();

        return (List<DepartamentosVo>) jdbcTemplate.query(stb.toString(), args, MAPPER_DEPARTAMENTO);
    }

    @Override
    public DepartamentosVo buscar(int idDep) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("buscar...");
        }
        StringBuffer stb = new StringBuffer(qryBuscar);
        ArrayList parameters = new ArrayList();
            parameters.add(idDep);
        Object[] args = parameters.toArray();
        return jdbcTemplate.queryForObject(stb.toString(), args, MAPPER_DEPARTAMENTO);
    }

    public void setQryTodo(JdbcQueryParameters qryTodo) {
        this.qryTodo = qryTodo;
    }

    public void setQryBuscar(String qryBuscar) {
        this.qryBuscar = qryBuscar;
    }

}
