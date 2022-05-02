/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.evaluacion2concesionario.classes;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author alumno
 */
public class Concesionario{
    
    private final String cif;
    private final String nombre;
    private final Map<String, Coche> stock;
    private final Map<String, Coche> vendidos;
    
    public static final  java.util.regex.Pattern PATRON_CIF = java.util.regex.Pattern.compile("[A-Z]{1}[0-9]{8}");
    public static final java.util.regex.Pattern PATRON_NOMBRE = java.util.regex.Pattern.compile("[a-zA-Z0-9.]{1,}");
    
    public Concesionario(String cif, String nombre){
        checkNombre(nombre);
        checkCIF(cif);
        this.nombre = nombre;
        this.cif = cif;
        stock = new LinkedHashMap<>();
        vendidos = new LinkedHashMap<>();
    }
    
    
    
    
    private  void checkCIF(String s){
        if(s == null || s.isBlank() || !PATRON_CIF.matcher(s).matches()){
            throw new IllegalArgumentException();
        }
    }
          
    private void checkNombre(String s){
        if(s == null || s.isBlank() || !PATRON_NOMBRE.matcher(s).matches()){
            throw new IllegalArgumentException();
        }
    }
    
    
    
    
    public boolean añadirCoche(Coche coche){
        if(!this.stock.containsKey(coche.getBastidor()) && !this.vendidos.containsKey(coche.getBastidor())){
            this.stock.put(coche.getBastidor(), coche);
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean venderCoche(String bastidor){
        if(this.stock.containsKey(bastidor) && !this.vendidos.containsKey(bastidor)){
            this.vendidos.put(bastidor, stock.get(bastidor));
            this.vendidos.get(bastidor).vendido();
            this.stock.remove(bastidor);
            return true;
        }
        else{
            return false;
        }
    }
    
    
    
    
    public StringBuilder mostrarStock(){
        StringBuilder txt = new StringBuilder("");
        for(String key : stock.keySet()){
            txt.append(stock.get(key).toString());   
        }
        return txt;
    }
    
    public StringBuilder mostrarVendidos(){
        StringBuilder txt = new StringBuilder("");
        for(String key : vendidos.keySet()){
            txt.append(vendidos.get(key).toString());   
        }
        return txt;
    }
    
    public String mostrarValorStock(){
        int pvp = 0;
        for(String key : this.stock.keySet()){
            pvp += this.stock.get(key).getPvp();
        }
        String txt = (this.stock.values().size() + " vehiculos vendidos con un importe de "  + pvp + "€");
        return txt;
    }
    
    public String mostrarValorVendidos(){
        int pvp = 0;
        for(String key : this.vendidos.keySet()){
            pvp += this.vendidos.get(key).getPvp();
        }
        String txt = (this.vendidos.values().size() + " vehiculos vendidos con un importe de "  + pvp + "€");
        return txt;
    }  
}
