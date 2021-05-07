/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.subproductos.dao;

import com.comunidad.simplecatproductorest.subproductos.dao.vo.DetalleSubprodVo;
import com.comunidad.simplecatproductorest.common.DateUtils;
import com.comunidad.simplecatproductorest.jdbc.JdbcDao;
import com.comunidad.simplecatproductorest.jdbc.JdbcQueryParameters;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;

/**
 *
 * @author Santiago
 */
public class DetalleSubprodDaoImpl extends JdbcDao implements DetalleSubprodDao {

    private static final Logger log = Logger.getLogger(DetalleSubprodDaoImpl.class);

    private JdbcQueryParameters qryTodo;

    private static RowMapper<DetalleSubprodVo> MAPPER_DETALLESUBPROD = (ResultSet rs, int rowNum) -> {
        DetalleSubprodVo obj = new DetalleSubprodVo();
        obj.setId(rs.getInt("ID"));
        obj.setNombre(rs.getString("NOMBRE"));
        obj.setDescripcion(rs.getString("DESCR"));
        obj.setStatus(rs.getInt("STATUS_FLAG"));
        obj.setFechaExpiracion(rs.getDate("EXPIRY_DT"));
        obj.setFechaUltActual(rs.getDate("LAST_UPDATE_DT"));
        obj.setUserUltActual(rs.getString("LAST_UPDATE_USER"));

        return obj;
    };

    @Override
    public List<DetalleSubprodVo> lista(DetalleSubprodVo detalleSubprodVo) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("lista...");
        }

        StringBuffer stb = new StringBuffer(qryTodo.getSqlBase());
        ArrayList parameters = new ArrayList();
        Properties params = qryTodo.getOptionalParameters();

        if (detalleSubprodVo != null) {
            if (detalleSubprodVo.getId() != 0) {
                stb.append(" ").append(params.get("id"));
                parameters.add(detalleSubprodVo.getId());
            }
            if (detalleSubprodVo.getIdSubprod() != 0) {
                stb.append(" ").append(params.get("idSubprod"));
                parameters.add(detalleSubprodVo.getIdSubprod());
            }
            if (detalleSubprodVo.getStatus() != 0) {
                stb.append(" ").append(params.get("estatus"));
                parameters.add(detalleSubprodVo.getStatus());
            }
        }
        if (qryTodo.getSqlLast() != null && !qryTodo.getSqlLast().equals("")) {
            stb.append(" ").append(qryTodo.getSqlLast());
        }

        Object[] args = parameters.toArray();

        return (List<DetalleSubprodVo>) jdbcTemplate.query(stb.toString(), args, MAPPER_DETALLESUBPROD);
    }

    public void setQryTodo(JdbcQueryParameters qryTodo) {
        this.qryTodo = qryTodo;
    }

}
