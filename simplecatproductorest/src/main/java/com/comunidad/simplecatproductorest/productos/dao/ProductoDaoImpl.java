/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.productos.dao;

import com.comunidad.simplecatproductorest.productos.dao.vo.ProductosVo;
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
public class ProductoDaoImpl extends JdbcDao implements ProductoDao {

    private static final Logger log = Logger.getLogger(ProductoDaoImpl.class);

    private JdbcQueryParameters qryTodo;

    private static RowMapper<ProductosVo> MAPPER_PRODUCTOS = (ResultSet rs, int rowNum) -> {
        ProductosVo obj = new ProductosVo();
        obj.setId(rs.getInt("ID"));
        obj.setIdCatego(rs.getInt("ID_CATEG"));
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

    @Override
    public List<ProductosVo> lista(ProductosVo productosVo) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("lista...");
        }

        StringBuffer stb = new StringBuffer(qryTodo.getSqlBase());
        ArrayList parameters = new ArrayList();
        Properties params = qryTodo.getOptionalParameters();

        if (productosVo != null) {
            if (productosVo.getId() != 0) {
                stb.append(" ").append(params.get("id"));
                parameters.add(productosVo.getId());
            }
            if (productosVo.getIdCatego() != 0) {
                stb.append(" ").append(params.get("idCatego"));
                parameters.add(productosVo.getIdCatego());
            }
            if (productosVo.getStatus() != 0) {
                stb.append(" ").append(params.get("estatus"));
                parameters.add(productosVo.getStatus());
            }
        }
        if (qryTodo.getSqlLast() != null && !qryTodo.getSqlLast().equals("")) {
            stb.append(" ").append(qryTodo.getSqlLast());
        }

        Object[] args = parameters.toArray();
        return (List<ProductosVo>) jdbcTemplate.query(stb.toString(), args, MAPPER_PRODUCTOS);
    }

    public void setQryTodo(JdbcQueryParameters qryTodo) {
        this.qryTodo = qryTodo;
    }

}
