package com.aerotaller.modules.articulo.dto;

import com.aerotaller.modelos.Articulo;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class ArticuloResponse {

    private Integer idArticulo;
    private String codigo;
    private String noSerie;
    private String descripcion;
    private Integer categoria;
    private Integer unidadMedida;
    private Integer almacen;
    private String ubicacion;
    private Integer proveedor;
    private BigDecimal precioCompra;
    private Integer stock;
    private Integer condicion;

    public static ArticuloResponse fromEntity(Articulo entity) {
        if (entity == null) {
            return null;
        }

        ArticuloResponse dto = new ArticuloResponse();
        dto.setIdArticulo(entity.getIdArticulo());
        dto.setCodigo(entity.getCodigo());
        dto.setNoSerie(entity.getNoSerie());
        dto.setDescripcion(entity.getDescripcion());
        dto.setCategoria(entity.getCategoria());
        dto.setUnidadMedida(entity.getUnidadMedida());
        dto.setAlmacen(entity.getAlmacen());
        dto.setUbicacion(entity.getUbicacion());
        dto.setProveedor(entity.getProveedor());
        dto.setPrecioCompra(entity.getPrecioCompra());
        dto.setStock(entity.getStock());
        dto.setCondicion(entity.getCondicion());
        return dto;
    }

    public Integer getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(Integer idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    // Compat: el entity expone "noParte" como alias de "codigo".
    @JsonProperty("noParte")
    public String getNoParte() {
        return codigo;
    }

    @JsonProperty("noParte")
    public void setNoParte(String noParte) {
        this.codigo = noParte;
    }

    public String getNoSerie() {
        return noSerie;
    }

    public void setNoSerie(String noSerie) {
        this.noSerie = noSerie;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(Integer unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public Integer getAlmacen() {
        return almacen;
    }

    public void setAlmacen(Integer almacen) {
        this.almacen = almacen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Integer getProveedor() {
        return proveedor;
    }

    public void setProveedor(Integer proveedor) {
        this.proveedor = proveedor;
    }

    public BigDecimal getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(BigDecimal precioCompra) {
        this.precioCompra = precioCompra;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCondicion() {
        return condicion;
    }

    public void setCondicion(Integer condicion) {
        this.condicion = condicion;
    }
}

