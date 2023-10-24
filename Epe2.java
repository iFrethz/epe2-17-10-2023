package epe2;

import java.util.Scanner;


public class Epe2 {

 public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static int cantidad = 0;
    public static float ventas[] = new float[100];
    public static int top[] = new int[100];

    public static void main(String[] args) throws InterruptedException {

        int op = 0, x = 0;
        int isbn[] = new int[100];
        String titulo[] = new String[100], sino = "";
        String autor[] = new String[100];
        float precio[] = new float[100];

        int stock[] = new int[100];
        Scanner scan = new Scanner(System.in);
        Scanner scan1 = new Scanner(System.in);

        do {
            op = menu(scan);
            opcion(op, scan, scan1, isbn, titulo, autor, precio, stock);

        } while (op != 5);
    }

    private static int menu(Scanner scan) {
        
        for (int j = 0; j < 10; j++) {
            System.out.println("");
        }
        
        int op1 = 0;
        System.out.println(ANSI_BLUE + "----------------------");
        System.out.println("   MENÚ PRINCIPAL   ");
        System.out.println(ANSI_BLUE + "----------------------");
        System.out.println("1) Agregar libros");
        System.out.println("2) Vender libros");
        System.out.println("3) Inventario");
        System.out.println("4) Reportes");
        System.out.println("5) Salir");
        System.out.println(ANSI_BLUE + "----------------------");
        System.out.println("Seleccione una opción...");
        op1 = scan.nextInt();
        
        for (int j = 0; j < 10; j++) {
            System.out.println("");
        }

        return op1;

    }

    private static void reporteIventario(int cantidad, int[] isbn, String[] titulo, String[] autor, float[] precio, int[] stock) throws InterruptedException {
        System.out.println(ANSI_BLUE + "----------------------");
        System.out.println("INVENTARIO COMPLETO");

        for (int i = 0; i < cantidad; i++) {
            System.out.println(ANSI_BLUE + "----------------------");
            System.out.println("ISBN: " + isbn[i]);
            System.out.println("Título: " + titulo[i]);
            System.out.println("Autor: " + autor[i]);
            System.out.println("Precio: " + precio[i]);
            System.out.println("Stock: " + stock[i]);
            System.out.println(ANSI_BLUE + "----------------------");
        }
        
        System.out.println("Espere 5 segundos...");
        Thread.sleep(5000);
        
    }

    private static void alertabajo(int cantidad, int[] isbn, String[] titulo, int[] stock) {
        for (int i = 0; i < cantidad; i++) {
            if (stock[i] < 5) {
                System.out.println("ISBN: " + isbn[i]);
                System.out.println("Título: " + titulo[i]);
                System.out.println("Stock: " + stock[i]);
            }

        }

    }

    private static void libroingresos(float[] precio, int[] stock) throws InterruptedException {
        System.out.println(ANSI_BLUE + "----------------------");
        System.out.println("    INGRESOS ");
        System.out.println(ANSI_BLUE + "----------------------");

        float cantvendida = 0;
        for (int i = 0; i < stock.length; i++) {
            cantvendida = cantvendida + ventas[i];

        }
        System.out.println("Ingresos generados: " + cantvendida);
        
        System.out.println("Espere 5 segundos...");
        Thread.sleep(5000);
    }

 private static void librosnovendidos (int cantidad, int[] isbn, String[] titulo, int[]top){
        System.out.println("Libros no vendidos o cantidad menor a 5");
        for(int i=0;i<cantidad;i++){
                            if (top[i] == 0 || (top[i] > 0 && top[i] < 5)){
                            System.out.println("ISBN: "+isbn[i]);
                                System.out.println("Titulo: "+titulo[i]);
                                System.out.println("Cantidad vendida: "+ top[i]);
                            }
                            }
    
    }

    private static void opcion(int op, Scanner scan, Scanner scan1, int isbn[], String titulo[], String autor[], float precio[], int stock[]) throws InterruptedException {
        int i = 0, x = 0;
        String sino = "";
        int buscador = 0;
        Integer st = 0;

        switch (op) {
            case 1:
                do {
                    cantidad = cantidad + 1;
                    System.out.println(ANSI_BLUE + "----------------------");
                    System.out.println("  DETALLE DEL LIBRO " + cantidad + "°");
                    System.out.println("  SISTEMA DE INGRESO");
                    System.out.println(ANSI_BLUE + "----------------------");
                    System.out.print("Serie ISBN: ");
                    isbn[i] = scan.nextInt();

                    System.out.print("Titulo: ");
                    titulo[i] = scan1.nextLine();

                    System.out.print("Autor: ");
                    autor[i] = scan1.nextLine();

                    System.out.print("Precio: ");
                    precio[i] = scan.nextFloat();

                    System.out.print("Stock inicial: ");
                    stock[i] = scan.nextInt();
                    i++;
                    System.out.println(ANSI_BLUE + "----------------------");
                    System.out.print("¿Quieres agregar otro libro? si/no: ");
                    sino = scan1.nextLine();
                    if (sino.equalsIgnoreCase("si")) {
                        x = 1;
                    } else {
                        x = 0;
                    }
                } while (x == 1);

                break;

            case 2:
                do {
                    System.out.println(ANSI_BLUE + "----------------------");
                    System.out.println("VENTA DE LIBRO");
                    System.out.println(ANSI_BLUE + "----------------------");
                    System.out.println("Ingresa el ISBN del libro que deseas vender: ");
                    buscador = scan.nextInt();
                    for (i = 0; i < 99; i++) {
                        if (buscador == isbn[i]) {
                               System.out.println(ANSI_BLUE + "----------------------");
                            if (stock[i] > 0) {
                                System.out.println("STOCK DISPONIBLE");
                                System.out.println(ANSI_BLUE + "----------------------");
                                System.out.println("Nos quedan " + stock[i] + " de stock");
                                System.out.print("¿Cuántos quieres vender? ");
                                st = scan.nextInt();
                                if (stock[i] >= st) {
                                    System.out.println("Perfecto vendiste " + st + " libros");
                                    System.out.println(ANSI_BLUE + "----------------------");
                                    stock[i] = stock[i] - st;
                                    ventas[i] = ventas[i] + (st * precio[i]);
                                    top[i] = top[i] + st;
                                    
                                    Thread.sleep(2000);
        
                                } else {
                                    System.out.println("Lo siento excediste la venta");
                                }

                            } else {
                                System.out.println("No tenemos stock");
                            }
                        }

                    }
                    
                    System.out.println("Quieres vender otro libro? si/no");
                    sino = scan1.nextLine();
                    if (sino.equalsIgnoreCase("si")) {
                        x = 1;
                    } else {
                        x = 0;
                    }

                } while (x == 1);
                break;
            case 3:
                System.out.println(ANSI_BLUE + "----------------------");
                System.out.println("   INVENTARIO   ");
                System.out.println("Cantidad:" + cantidad);
                System.out.println(ANSI_BLUE + "----------------------");
                for (i = 0; i < cantidad; i++) {
                    if (stock[i] > 10) {
                        System.out.println(ANSI_BLUE + "ISBN: " + isbn[i]);
                        System.out.println(ANSI_BLUE + "Titulo: " + titulo[i]);
                        System.out.println(ANSI_BLUE + "Stock: " + stock[i]);
                        System.out.println(":::::::::::::::::::::::");

                    } else if (stock[i] <= 10 && stock[i] > 0) {
                        System.out.println(ANSI_YELLOW + "Isbn: " + isbn[i]);
                        System.out.println(ANSI_YELLOW + "Titulo: " + titulo[i]);
                        System.out.println(ANSI_YELLOW + "Stock: " + stock[i]);
                        System.out.println(":::::::::::::::::::::::");

                    } else {
                        System.out.println(ANSI_RED + "Isbn: " + isbn[i]);
                        System.out.println(ANSI_RED + "Titulo: " + titulo[i]);
                        System.out.println(ANSI_RED + "Stock: " + stock[i]);
                        System.out.println(":::::::::::::::::::::::");
                    }

                }
               
                System.out.println("Espere 5 segundos...");
                Thread.sleep(5000);
                
                break;
            case 4:
                System.out.println("  ");
                System.out.println("1. Informe de inventario completo");
                System.out.println("2. Informe de libros más vendidos");
                System.out.println("3. Alerta de inventarios bajos");
                System.out.println("4. Reporte de ingresos");
                System.out.println("5. Reporte libros NO vendidos o cantidad vendida menor a 5");
                System.out.print("Seleccione una opción: ");
                int op2 = scan.nextInt();
                
                for (int j = 0; j < 10; j++) {
                    System.out.println("");
                }

                switch (op2) {
                    case 1:
                        reporteIventario(cantidad, isbn, titulo, autor, precio, stock);
                        break;
                    case 2:
                        
                        for (i = 0; i < top.length - 1; i++) {//ordenamiento burbuja
                            for (int j = 0; j < top.length - i - 1; j++) {
                                if (top[j] > top[j + 1]) {
                                    int temp = top[j];
                                    top[j] = top[j + 1];
                                    top[j + 1] = temp;
                                }
                            }
                        }

                        System.out.println(ANSI_BLUE + "----------------------");
                        System.out.println("   TOP 5 MAS VENDIDOS");
                        System.out.println(ANSI_BLUE + "----------------------");
                        for (i = 0; i < 5; i++) {
                            System.out.println("Top" + (i + 1) + ": " + top[top.length - (i + 1)] + " Nombre: " + titulo[i]);
                        }
                        System.out.println(ANSI_BLUE + "----------------------");

                        System.out.println("Espere 5 segundos...");
                        Thread.sleep(5000);
                        break;

                    case 3:
                        alertabajo(cantidad, isbn, titulo, stock);
                        break;
                    case 4:
                        libroingresos(precio, stock);
                        break;
                    case 5:
                        librosnovendidos(cantidad, isbn, titulo, top);
                        break;
                    default:
                        System.out.println("Opción invalida");
                        break;

                }

                break;
            default:
                break;

        }

    }
}