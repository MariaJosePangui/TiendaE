package com.mycompany.tiendae;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class TiendaE {

    public static void main(String[] args) {
      Inventario inventario = new Inventario();
      Scanner scanner = new Scanner(System.in);
      
        // Agregar productos de ejemplo
        inventario.agregarProducto(new Producto("Producto 1", "Descripcion del producto 1", 10.99, 50, "Categoria A"));
        inventario.agregarProducto(new Producto("Producto 2", "Descripcion del producto 2", 25.55, 30, "Categoria B"));
        inventario.agregarProducto(new Producto("Producto 3", "Descripcion del producto 3", 19.55, 20, "Categoria C"));
        inventario.agregarProducto(new Producto("Producto 4", "Descripcion del producto 4", 12.99, 10, "Categoria D"));
        
        // Mostrar todos los productos disponibles
        List<Producto> productos = inventario.getProductos();
        for (Producto producto : productos) {
            System.out.println(producto);
        }
          // Buscar producto por nombre o categoria
        System.out.println("Ingresa el nombre o la categoria del producto que deseas buscar: ");
        String busqueda = scanner.nextLine();

        List<Producto> productosEncontrados = new ArrayList<>();
        
        for (Producto producto : productos) {
            if (producto.getNombre().equalsIgnoreCase(busqueda) || producto.getCategoria().equalsIgnoreCase(busqueda)) {
                productosEncontrados.add(producto);
            }
        }

        if (!productosEncontrados.isEmpty()) {
            System.out.println("Producto encontrado:");
            for (Producto producto : productosEncontrados) {
                System.out.println(producto);
            }
        } else {
            System.out.println("No se encontraron productos con el nombre o categoria proporcionados.");
        }
        // Crear lista de productos para la compra
        List<Producto> productosCompra = new ArrayList<>();
        
        // Permitir al usuario agregar productos a la lista de compra
        boolean seguirComprando = true;
        while (seguirComprando) {
            System.out.println("Ingresa el nombre del producto que deseas agregar a la compra o escribe 'fin' para finalizar la compra): ");
            String nombreProducto = scanner.nextLine();
            if (nombreProducto.equalsIgnoreCase("fin")) {
                seguirComprando = false;
            } else {
                Producto productoEncontrado = inventario.buscarProductoPorNombre(nombreProducto);
                if (productoEncontrado != null) {
                    System.out.println("Ingresa la cantidad de " + productoEncontrado.getNombre() + " que deseas comprar: ");
                    int cantidad = Integer.parseInt(scanner.nextLine());
                    if (cantidad > 0 && cantidad <= productoEncontrado.getStock()) {
                        productoEncontrado.reducirStock(cantidad);
                        productosCompra.add(productoEncontrado);
                        System.out.println("Producto agregado a la lista de compra.");
                    } else {
                        System.out.println("Cantidad no valida o insuficiente stock.");
                    }
                } else {
                    System.out.println("Producto no encontrado.");
                }
            }
        }
        // Mostrar productos en la lista de compra
        if (!productosCompra.isEmpty()) {
            System.out.println("Productos en la lista de compra:");
            for (Producto producto : productosCompra) {
                System.out.print(producto.getNombre() + " - Cantidad en la lista: " + producto.getStock());

                int cantidadDisponibleEnLista = producto.getStock(); // Cantidad disponible en la lista
                System.out.print(". Ingresa la cantidad que deseas comprar (hasta " + cantidadDisponibleEnLista + "): ");
                int cantidadCompra = Integer.parseInt(scanner.nextLine());

                if (cantidadCompra > 0 && cantidadCompra <= cantidadDisponibleEnLista) {
                    producto.reducirStock(cantidadCompra);
                    System.out.println(" Producto comprado. Nuevo stock: " + producto.getStock());
                } else {
                    System.out.println(" Cantidad no válida o insuficiente stock para la compra.");
                }
            }
        } else {
            System.out.println("La lista de compra está vacía");
        }
       
        // Eliminar un producto de la lista
        System.out.println("Ingresa el nombre del producto que deseas eliminar: ");
        String nombreProductoEliminar = scanner.nextLine();
        
        Producto productoEliminar = inventario.buscarProductoPorNombre(nombreProductoEliminar);
        
        if (productoEliminar != null) {
            inventario.eliminarProducto(nombreProductoEliminar);
            System.out.println("Producto eliminado exitosamente.");
        } else {
            System.out.println("Producto no encontrado.");
        }
        
        // Modificar informacion de un producto existente
        System.out.println("Ingresa el nombre del producto que deseas modificar: ");
        String nombreProductoModificar = scanner.nextLine();
        Producto productoModificar = inventario.buscarProductoPorNombre(nombreProductoModificar);
        
        if (productoModificar != null) {
            System.out.println("Producto encontrado:\n" + productoModificar);
            System.out.println("Ingresa la nueva descripcion: ");
            String nuevaDescripcion = scanner.nextLine();
            productoModificar.setDescripcion(nuevaDescripcion);
            System.out.println("Ingresa el nuevo precio: ");
            double nuevoPrecio = Double.parseDouble(scanner.nextLine());
            productoModificar.setPrecio(nuevoPrecio);
            System.out.println("Ingresa el nuevo stock: ");
            int nuevoStock = Integer.parseInt(scanner.nextLine());
            productoModificar.setStock(nuevoStock);
            System.out.println("Ingresa la nueva categoria: ");
            String nuevaCategoria = scanner.nextLine();
            productoModificar.setCategoria(nuevaCategoria);
            System.out.println("Informacion del producto modificada:\n" + productoModificar);
        } else {
            System.out.println("Producto no encontrado.");
        }

       // Realizar una compra y actualizar el stock
        System.out.println("Ingresa el nombre del producto que deseas comprar: ");
        String nombreProductoComprar = scanner.nextLine();
        
        Producto productoComprado = inventario.buscarProductoPorNombre(nombreProductoComprar);
        
        if (productoComprado != null) {
            System.out.println("Ingresa la cantidad que deseas comprar: ");
            int cantidadCompra = scanner.nextInt();
            scanner.nextLine();  // Consumir la línea vacía después del nextInt
            
            productoComprado.reducirStock(cantidadCompra); 
            System.out.println("Compra realizada. Nuevo stock: " + productoComprado.getStock());
        } else {
            System.out.println("Producto no encontrado.");
        }

        scanner.close(); 
    }
}
