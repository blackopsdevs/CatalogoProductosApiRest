/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.subproductos.dao;

import com.comunidad.simplecatproductorest.productos.dao.ProductoDaoImpl;
import com.comunidad.simplecatproductorest.subproductos.dao.vo.SubproductosVo;
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
public class SubproductoDaoImpl extends JdbcDao implements SubproductoDao {

    private static final Logger log = Logger.getLogger(ProductoDaoImpl.class);

    private JdbcQueryParameters qryTodo;

    private static RowMapper<SubproductosVo> MAPPER_SUBPRODUCTOS = (ResultSet rs, int rowNum) -> {
        SubproductosVo obj = new SubproductosVo();
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
    public List<SubproductosVo> lista(SubproductosVo subproductosVo) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("lista...");
        }

        StringBuffer stb = new StringBuffer(qryTodo.getSqlBase());
        ArrayList parameters = new ArrayList();
        Properties params = qryTodo.getOptionalParameters();

        if (subproductosVo != null) {
            if (subproductosVo.getId() != 0) {
                stb.append(" ").append(params.get("id"));
                parameters.add(subproductosVo.getId());
            }
            if (subproductosVo.getIdProd() != 0) {
                stb.append(" ").append(params.get("idProd"));
                parameters.add(subproductosVo.getIdProd());
            }
            if (subproductosVo.getStatus() != 0) {
                stb.append(" ").append(params.get("estatus"));
                parameters.add(subproductosVo.getStatus());
            }
        }
        if (qryTodo.getSqlLast() != null && !qryTodo.getSqlLast().equals("")) {
            stb.append(" ").append(qryTodo.getSqlLast());
        }

        Object[] args = parameters.toArray();
        return (List<SubproductosVo>) jdbcTemplate.query(stb.toString(), args, MAPPER_SUBPRODUCTOS);
    }

    public void setQryTodo(JdbcQueryParameters qryTodo) {
        this.qryTodo = qryTodo;
    }

}
