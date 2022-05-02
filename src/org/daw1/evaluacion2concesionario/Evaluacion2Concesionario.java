/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.daw1.evaluacion2concesionario;

import java.util.regex.Pattern;
import org.daw1.evaluacion2concesionario.classes.Coche;
import org.daw1.evaluacion2concesionario.classes.Concesionario;
import org.daw1.evaluacion2concesionario.classes.TipoVehiculo;

/**
 *
 * @author Rafael González Centeno
 */
public class Evaluacion2Concesionario {

    private static java.util.Scanner teclado;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        teclado = new java.util.Scanner(System.in);
        String opcion;
        Concesionario concesionario = crearConcesionario();
        do{
            System.out.println("*************************************************************");
            System.out.println("* 1. Alta vehículo                                          *");
            System.out.println("* 2. Registrar venta vehículo                               *");
            System.out.println("* 3. Mostrar número e importe de vehículos no vendidos      *");
            System.out.println("* 4. Mostrar número e importe de vehículos vendidos         *");
            System.out.println("* 5. Mostrar listado de vehículos no vendidos               *");
            System.out.println("* 6. Mostrar listado de vehículos vendidos                  *");
            System.out.println("*                                                           *");
            System.out.println("* 0. Salir                                                  *");
            System.out.println("*************************************************************");
            opcion = teclado.nextLine();
            switch(opcion){
                case "1":
                    //Damos de alta un nuevo vehículo y lo guardamos en el concesionario. Validar datos.
                    Coche coche = crearCoche();
                    if(concesionario.añadirCoche(coche)){
                        System.out.println("El coche se añadio correctamente");
                    }
                    else{
                        System.out.println("ERROR: El coche que desea introducir ya existe");
                    }
                    break;
                case "2":
                    //Mostramos vehículos no vendidos. El usuario inserta la matrícula y se marca como vendido el vehículo a fecha de hoy (Esta función te puede ayudar: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html#now())
                    System.out.println(concesionario.mostrarStock());
                    String bastidor = checkString("Introduzca el numero de bastidor del coche", "ERROR: El numero de bastidor debe seguir el siguiente formato (ABCDEF1GHI234567)", Coche.PATRON_BASTIDOR);
                    if(concesionario.venderCoche(bastidor)){
                        System.out.println("El coche se ha vendido con exito");
                    }
                    else{
                        System.out.println("ERROR: El coche no ha podido venderse (Se encuentra vendido o no existe)");
                    }
                    break;
                case "3":
                    //Muestra cuantos coches están pendientes de vender y el importe total de venta de estos (teniendo en cuenta que el PVP sigue la fórmula del documento).
                    System.out.println(concesionario.mostrarValorStock());
                    break;
                case "4":
                    //Muestra cuantos coches están vendidos y el importe total de venta de estos (teniendo en cuenta que el PVP sigue la fórmula del documento).
                    System.out.println(concesionario.mostrarValorVendidos());
                    break;
                case "5":
                    //Muestra los datos de los coches no vendidos haciendo un System.out.println(vehiculo). Se muestran todos los datos del coche.
                    System.out.println(concesionario.mostrarStock());
                    break;
                case "6":
                    //Muestra los datos de los coches vendidos haciendo un System.out.println(vehiculo). Se muestran todos los datos del coche.
                    System.out.println(concesionario.mostrarVendidos());
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
        while(!opcion.equals("0"));
        
    }  
    
    private static Concesionario crearConcesionario(){
        String nombre = checkString("Introduzca el nombre del concesionario", "ERROR: El nombre solo puede estar formado por letras, numeros y puntos", Concesionario.PATRON_NOMBRE);
        String cif = checkString("Introduzca el cif del concesionario", "ERROR: El cif debe seguir el siguiente formato (A12345678)", Concesionario.PATRON_CIF);
        return new Concesionario(cif, nombre);
    }
    
    private static Coche crearCoche(){
        String marca = checkString("Introduzca la marca del coche", "ERROR: La marca debe estar formada unicamente por letras y numeros", Coche.PATRON_MARCA_MODELO);
        String modelo = checkString("Introduzca el modelo del coche", "ERROR: El modelo debe estar formada unicamente por letras y numeros", Coche.PATRON_MARCA_MODELO);
        String bastidor = checkString("Introduzca el numero de bastidor del coche", "ERROR: El numero de bastidor debe seguir el siguiente formato (ABCDEF1GHIJ234567)", Coche.PATRON_BASTIDOR);
        
        TipoVehiculo tipoVehiculo = null;
        System.out.println("Inserte el numero del tipo de vehiculo que desea");
        int opcionVehiculo = -1;
        do{
            mostrarVehiculos();
            if(teclado.hasNextInt()){
                opcionVehiculo = teclado.nextInt();
                if(opcionVehiculo < 1 || opcionVehiculo > TipoVehiculo.values().length){
                    System.out.println("ERROR: El numero insertado no es una opcion valida");
                }
                else{
                    tipoVehiculo = TipoVehiculo.of(opcionVehiculo);
                }
            }
            else{
                System.out.println("ERROR: Debe insertar un numero");
            }
            teclado.nextLine();
        }
        while(tipoVehiculo == null);
        
        double precioCompra = -1;
        do{
            System.out.println("Introduzca el precio de compra del vehiculo");
            if(teclado.hasNextDouble()){
                precioCompra = teclado.nextDouble();
                if(precioCompra <= 0){
                    System.out.println("ERROR: El precio del vehiculo debe ser mayor que 0€");
                }
            }
            else{
                System.out.println("ERROR: El precio del vehiculo debe ser un numero");
            }
            teclado.nextLine();
        }
        while(precioCompra <= 0);
        
        int margen = -1;
        do{
            System.out.println("Introduzca el margen de beneficios");
            if(teclado.hasNextInt()){
                margen = teclado.nextInt();
                if(margen <= 0){
                    System.out.println("ERROR: El margen de beneficios debe ser mayor que 0€");
                }
            }
            else{
                System.out.println("ERROR: Debe insertar un numero");
            }
            teclado.nextLine();
        }
        while(margen <= 0);
        
        return new Coche(marca, modelo, bastidor, tipoVehiculo, precioCompra, margen);
    }
    
    private static String checkString(String mensaje, String error, Pattern patron){
        String s = "";
        do{
            System.out.println(mensaje);
            s = teclado.nextLine();
            if(s == null || s.isBlank() || !patron.matcher(s).matches()){
                System.out.println(error);
            }
        }
        while(s == null || s.isBlank() || !patron.matcher(s).matches());
        return s;
    }
    
    private static void mostrarVehiculos(){
        int i = 1;
        for(TipoVehiculo t : TipoVehiculo.values()){
            System.out.println(i + ".- " + t);
            i++;
        }
    }
}
