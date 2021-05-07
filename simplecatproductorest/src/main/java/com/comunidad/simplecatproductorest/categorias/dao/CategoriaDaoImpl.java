/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.categorias.dao;

import com.comunidad.simplecatproductorest.categorias.dao.vo.CategoriasVo;
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
public class CategoriaDaoImpl extends JdbcDao implements CategoriaDao {

    private static final Logger log = Logger.getLogger(CategoriaDaoImpl.class);

    private JdbcQueryParameters qryTodo;

    private static RowMapper<CategoriasVo> MAPPER_CATEGORIA = (ResultSet rs, int rowNum) -> {
        CategoriasVo obj = new CategoriasVo();
        obj.setId(rs.getInt("ID"));
        obj.setIdDepart(rs.getInt("ID_DEPA"));
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
    public List<CategoriasVo> lista(CategoriasVo categoriasVo) throws Exception {
        if (log.isDebugEnabled()) {
            log.debug("listDepartamentoVo...");
        }

        StringBuffer stb = new StringBuffer(qryTodo.getSqlBase());
        ArrayList parameters = new ArrayList();
        Properties params = qryTodo.getOptionalParameters();

        if (categoriasVo != null) {
            if (categoriasVo.getId() != 0) {
                stb.append(" ").append(params.get("id"));
                parameters.add(categoriasVo.getId());
            }
            if (categoriasVo.getIdDepart() != 0) {
                stb.append(" ").append(params.get("idDepa"));
                parameters.add(categoriasVo.getIdDepart());
            }
            if (categoriasVo.getStatus() != 0) {
                stb.append(" ").append(params.get("estatus"));
                parameters.add(categoriasVo.getStatus());
            }
        }
        if (qryTodo.getSqlLast() != null && !qryTodo.getSqlLast().equals("")) {
            stb.append(" ").append(qryTodo.getSqlLast());
        }

        Object[] args = parameters.toArray();

        return (List<CategoriasVo>) jdbcTemplate.query(stb.toString(), args, MAPPER_CATEGORIA);
    }

    public void setQryTodo(JdbcQueryParameters qryTodo) {
        this.qryTodo = qryTodo;
    }

}
