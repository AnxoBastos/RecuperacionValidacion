/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.daw1.evaluacion2concesionario;

import java.time.LocalDate;
import org.daw1.evaluacion2concesionario.classes.Coche;
import org.daw1.evaluacion2concesionario.classes.Concesionario;

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
                    break;
                case "2":
                    //Mostramos vehículos no vendidos. El usuario inserta la matrícula y se marca como vendido el vehículo a fecha de hoy (Esta función te puede ayudar: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/LocalDate.html#now())
                    break;
                case "3":
                    concesionario.mostrarValorStock();
                    break;
                case "4":
                    concesionario.mostrarValorVendidos();
                    break;
                case "5":
                    concesionario.mostrarStock();
                    break;
                case "6":
                    concesionario.mostrarVendidos();
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
        System.out.println("Inserte el nombre del concesionario");
        String nombre = "";
        do{
            nombre = teclado.nextLine();
            if(!nombre.matches("[a-zA-Z.]{1,}")){
                System.out.println("El nombre no puede contener caracteres diferentes a letras, numeros y puntos");
            }
            teclado.nextLine();
        }
        while(!nombre.matches("[a-zA-Z.]{1,}"));
        System.out.println("Inserte el Cif del concesionario");
        String cif = "";
        do{
            nombre = teclado.nextLine();
            if(!cif.matches("[A-Z]{1}[0-9]{8}")){
                System.out.println("El nombre no puede contener caracteres diferentes a letras, numeros y puntos");
            }
            teclado.nextLine();
        }
        while(!cif.matches("[A-Z]{1}[0-9]{8}"));
        return new Concesionario(nombre, cif);
    }
}
