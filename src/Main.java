import java.util.ArrayList;
import java.util.Scanner;

class Producto {
    private String nombre;
    private int cantidadInicial;
    private int unidadesVendidas;
    private double precio;

    public Producto(String nombre, int cantidadInicial, double precio) {
        this.nombre = nombre;
        this.cantidadInicial = cantidadInicial;
        this.unidadesVendidas = 0;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadInicial() {
        return cantidadInicial;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void vender(int cantidad) {
        if (cantidad <= disponible()) {
            unidadesVendidas += cantidad;
            System.out.println("Venta realizada: " + cantidad + " unidades de " + nombre);
        } else {
            System.out.println("No hay suficiente stock disponible para vender " + cantidad + " unidades de " + nombre);
        }
    }

    public int disponible() {
        return cantidadInicial - unidadesVendidas;
    }

    public void duplicarInventario() {
        cantidadInicial *= 2;
        System.out.println("Inventario de " + nombre + " duplicado. Nueva cantidad: " + cantidadInicial);
    }

    public void mostrarInformacion() {
        System.out.println("Información del producto " + nombre + ":");
        System.out.println("Cantidad inicial: " + cantidadInicial);
        System.out.println("Unidades vendidas: " + unidadesVendidas);
        System.out.println("Disponible en inventario: " + disponible());
        System.out.println("Precio por unidad: $" + precio);
        System.out.println("Valor total en inventario: $" + calcularValorTotal());
    }

    public double calcularValorTotal() {
        return disponible() * precio;
    }
}

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ArrayList<Producto> inventario = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n*** MENÚ ***");
            System.out.println("1. Agregar producto");
            System.out.println("2. Vender producto");
            System.out.println("3. Duplicar inventario de producto vendido totalmente");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Calcular valor total del inventario");
            System.out.println("6. Salir");
            System.out.print("Ingrese el número de la opción que desea realizar: ");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregarProducto();
                    break;
                case 2:
                    venderProducto();
                    break;
                case 3:
                    duplicarInventarioProducto();
                    break;
                case 4:
                    mostrarInventario();
                    break;
                case 5:
                    calcularValorTotalInventario();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
            }
        }
    }

    public static void agregarProducto() {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.next();
        System.out.print("Ingrese la cantidad inicial del producto: ");
        int cantidadInicial = scanner.nextInt();
        System.out.print("Ingrese el precio por unidad del producto: ");
        double precio = scanner.nextDouble();
        inventario.add(new Producto(nombre, cantidadInicial, precio));
        System.out.println("Producto " + nombre + " agregado al inventario con una cantidad inicial de " + cantidadInicial);
    }

    public static void venderProducto() {
        System.out.print("Ingrese el nombre del producto que desea vender: ");
        String nombre = scanner.next();
        System.out.print("Ingrese la cantidad que desea vender: ");
        int cantidad = scanner.nextInt();
        for (Producto producto : inventario) {
            if (producto.getNombre().equals(nombre)) {
                producto.vender(cantidad);
                return;
            }
        }
        System.out.println("El producto " + nombre + " no se encuentra en el inventario.");
    }

    public static void duplicarInventarioProducto() {
        System.out.print("Ingrese el nombre del producto que desea duplicar su inventario: ");
        String nombre = scanner.next();
        for (Producto producto : inventario) {
            if (producto.getNombre().equals(nombre)) {
                if (producto.getUnidadesVendidas() == 0) {
                    producto.duplicarInventario();
                } else {
                    System.out.println("No se puede duplicar el inventario de " + nombre + " porque no está agotado.");
                }
                return;
            }
        }
        System.out.println("El producto " + nombre + " no se encuentra en el inventario.");
    }

    public static void mostrarInventario() {
        System.out.println("Inventario actual:");
        for (Producto producto : inventario) {
            producto.mostrarInformacion();
        }
    }

    public static void calcularValorTotalInventario() {
        double valorTotal = 0;
        for (Producto producto : inventario) {
            valorTotal += producto.calcularValorTotal();
        }
        System.out.println("El valor total del inventario es: $" + valorTotal);
    }
}