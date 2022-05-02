/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.evaluacion2concesionario.classes;

/**
 *
 * @author alumno
 */
public enum TipoVehiculo{
    
    TURISMO, RANCHERA, MONOVOLUMEN, SUV, FURGONETA, OTRO;
    
    public static TipoVehiculo of(int tipo){
        if(tipo < 1 || tipo > TipoVehiculo.values().length){
            throw new IllegalArgumentException();
        }
        else{
            return TipoVehiculo.values()[tipo - 1];
        }
    }   
    
}
