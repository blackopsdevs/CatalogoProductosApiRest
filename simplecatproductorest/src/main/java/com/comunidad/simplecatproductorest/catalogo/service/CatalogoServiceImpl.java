/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.comunidad.simplecatproductorest.catalogo.service;

import com.comunidad.simplecatproductorest.categorias.dao.vo.CategoriasVo;
import com.comunidad.simplecatproductorest.categorias.service.CategoriaService;
import com.comunidad.simplecatproductorest.departamentos.dao.vo.DepartamentosVo;
import com.comunidad.simplecatproductorest.departamentos.service.DepartamentoService;
import com.comunidad.simplecatproductorest.productos.dao.vo.ProductosVo;
import com.comunidad.simplecatproductorest.productos.service.ProductoService;
import com.comunidad.simplecatproductorest.subproductos.dao.vo.DetalleSubprodVo;
import com.comunidad.simplecatproductorest.subproductos.dao.vo.SubproductosVo;
import com.comunidad.simplecatproductorest.subproductos.service.DetalleSubprodService;
import com.comunidad.simplecatproductorest.subproductos.service.SubproductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Santiago
 */
@Service
public class CatalogoServiceImpl implements CatalogoService {

    @Autowired
    private DepartamentoService departamentoService;
    private DepartamentosVo departamentosVo = null;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private SubproductoService subproductoService;

    @Autowired
    private DetalleSubprodService detalleSubprodService;

    @Override
    public DepartamentosVo getDepartamentosVo(int idDep, int idCat, int idProd, int idSubprod) throws Exception {
        if (idDep != 0) {
            departamentosVo = departamentoService.buscar(idDep);
            if (idCat != 0) {
                CategoriasVo catVo = new CategoriasVo();
                catVo.setIdDepart(departamentosVo.getId());
                List<CategoriasVo> listaCat = categoriaService.lista(catVo);
                if (!listaCat.isEmpty() && listaCat.size() > 0) {
                    if (idProd != 0) {
                        ProductosVo productosVo = null;
                        for (CategoriasVo caVo : listaCat) {
                            productosVo = new ProductosVo();
                            productosVo.setIdCatego(caVo.getId());
                            List<ProductosVo> listaProd = productoService.lista(productosVo);
                            if (!listaProd.isEmpty() && listaProd.size() > 0) {
                                if (idSubprod != 0) {
                                    SubproductosVo subProductosVo = null;
                                    for (ProductosVo prodVo : listaProd) {
                                        subProductosVo = new SubproductosVo();
                                        subProductosVo.setIdProd(prodVo.getId());
                                        List<SubproductosVo> listaSubprod = subproductoService.lista(subProductosVo);
                                        if (!listaSubprod.isEmpty() && listaSubprod.size() > 0) {
                                            DetalleSubprodVo detSubprodVo = null;
                                            for (SubproductosVo subprodVo : listaSubprod) {
                                                detSubprodVo = new DetalleSubprodVo();
                                                detSubprodVo.setIdSubprod(subprodVo.getId());
                                                List<DetalleSubprodVo> listaDetSubprodVo = detalleSubprodService.lista(detSubprodVo);
                                                if (!listaDetSubprodVo.isEmpty() && listaDetSubprodVo.size() > 0) {
                                                    subprodVo.setDetalles(listaDetSubprodVo);
                                                }
                                            }
                                            prodVo.setListSubproductoVo(listaSubprod);
                                        }
                                    }
                                }
                                caVo.setListProductoVo(listaProd);
                            }
                        }
                    }
                    departamentosVo.setListCategoriaVo(listaCat);
                }
            }
        }
        return departamentosVo;
    }

    @Override
    public List<DepartamentosVo> lista() throws Exception {
        return departamentoService.lista(departamentosVo);
    }

}
