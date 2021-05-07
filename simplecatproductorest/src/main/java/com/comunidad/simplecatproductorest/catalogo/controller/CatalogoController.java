/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.catalogo.controller;

import com.comunidad.simplecatproductorest.catalogo.service.CatalogoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Santiago
 */
@RestController
@RequestMapping("/api/v1/catalogo")
public class CatalogoController {

    @Autowired
    private CatalogoService catalogoService;

    @GetMapping({"/departamentos"})
    public ResponseEntity<Object> getAllDepartamentos() throws Exception {
        return new ResponseEntity<>(catalogoService.lista(), HttpStatus.OK);
    }

    @GetMapping({"/departamento/{idDep}"})
    public ResponseEntity<Object> getDepartamentos(@PathVariable int idDep) throws Exception {
        return new ResponseEntity<>(catalogoService.getDepartamentosVo(idDep, 0, 0, 0), HttpStatus.OK);
    }

    @GetMapping({"/departamento/{idDep}/categoria/{idCat}"})
    public ResponseEntity<Object> getCategorias(@PathVariable int idDep, @PathVariable int idCat) throws Exception {
        return new ResponseEntity<>(catalogoService.getDepartamentosVo(idDep, idCat, 0, 0), HttpStatus.OK);
    }

    @GetMapping({"/departamento/{idDep}/categoria/{idCat}/producto/{idProd}"})
    public ResponseEntity<Object> getProductos(@PathVariable int idDep, @PathVariable int idCat, @PathVariable int idProd) throws Exception {
        return new ResponseEntity<>(catalogoService.getDepartamentosVo(idDep, idCat, idProd, 0), HttpStatus.OK);
    }

    @GetMapping({"/departamento/{idDep}/categoria/{idCat}/producto/{idProd}/subproducto/{idSubprod}"})
    public ResponseEntity<Object> getSubproductos(@PathVariable int idDep, @PathVariable int idCat, @PathVariable int idProd, @PathVariable int idSubprod) throws Exception {
        return new ResponseEntity<>(catalogoService.getDepartamentosVo(idDep, idCat, idProd, idSubprod), HttpStatus.OK);
    }
}
