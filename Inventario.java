package com.mycompany.tiendae;
import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos;

    public Inventario() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void modificarProducto(String nombre, Producto nuevoProducto) {
        for (int i = 0; i < productos.size(); i++) {
            if (productos.get(i).getNombre().equalsIgnoreCase(nombre)) {
                productos.set(i, nuevoProducto);
                return;
            }
        }
        System.out.println("Producto no encontrado.");
    }

    public void eliminarProducto(String nombre) {
        productos.removeIf(producto -> producto.getNombre().equalsIgnoreCase(nombre));
    }

    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null;
    }

    public List<Producto> buscarProductoPorCategoria(String categoria) {
        List<Producto> productosEnCategoria = new ArrayList<>();
        for (Producto producto : productos) {
            if (producto.getCategoria().equalsIgnoreCase(categoria)) {
                productosEnCategoria.add(producto);
            }
        }
        return productosEnCategoria;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
