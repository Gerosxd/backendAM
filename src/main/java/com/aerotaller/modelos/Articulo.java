package com.aerotaller.modelos;

public class Articulo {

    private int idArticulo;
    private String codigo;
    private String noSerie;
    private String descripcion;
    private int categoria;
    private int unidadMedida;
    private int almacen;
    private String ubicacion;
    private int proveedor;
    private double precioCompra;
    private int stock;
    private int condicion;

    public Articulo() {
    }

    public Articulo(int idArticulo, String codigo, String noSerie, String descripcion, int categoria, int unidadMedida, int almacen, String ubicacion, int proveedor, double precioCompra, int stock, int condicion) {
        this.setIdArticulo(idArticulo);
        this.setCodigo(codigo);
        this.setNoSerie(noSerie);
        this.setDescripcion(descripcion);
        this.setCategoria(categoria);
        this.setUnidadMedida(unidadMedida);
        this.setAlmacen(almacen);
        this.setUbicacion(ubicacion);
        this.setProveedor(proveedor);
        this.setPrecioCompra(precioCompra);
        this.setStock(stock);
        this.setCondicion(condicion);
    }


    public int getIdArticulo() {
        return idArticulo;
    }

    public void setIdArticulo(int idArticulo) {
        this.idArticulo = idArticulo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public int getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(int unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public int getAlmacen() {
        return almacen;
    }

    public void setAlmacen(int almacen) {
        this.almacen = almacen;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getProveedor() {
        return proveedor;
    }

    public void setProveedor(int proveedor) {
        this.proveedor = proveedor;
    }

    public double getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(double precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCondicion() {
        return condicion;
    }

    public void setCondicion(int condicion) {
        this.condicion = condicion;
    }
}
