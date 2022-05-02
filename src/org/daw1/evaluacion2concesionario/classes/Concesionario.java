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
    
    public Concesionario(String cif, String nombre){
        checkNombre(nombre);
        checkCIF(cif);
        this.nombre = nombre;
        this.cif = cif;
        stock = new LinkedHashMap<>();
        vendidos = new LinkedHashMap<>();
    }
    
    
    
    private  void checkCIF(String s){
        if(s == null || s.isBlank() || !s.matches("[A-Z]{1}[0-9]{8}")){
            throw new IllegalArgumentException();
        }
    }
          
    private void checkNombre(String s){
        if(s == null || s.isBlank() || !s.matches("[a-zA-Z0-9.]{1,}")){
            throw new IllegalArgumentException();
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
    
    public void mostrarValorStock(){
        int pvp = 0;
        for(String key : this.stock.keySet()){
            pvp += this.stock.get(key).getPvp();
        }
        System.out.println(this.stock.values().size() + " vehiculos sin vender con un importe de " + pvp);
    }
    
    public void mostrarValorVendidos(){
        int pvp = 0;
        for(String key : this.vendidos.keySet()){
            pvp += this.vendidos.get(key).getPvp();
        }
        System.out.println(this.vendidos.values().size() + " vehiculos vendidos con un importe de "  + pvp);
    }
    
    public void venderCoche(String bastidor){
        if(stock.containsKey(bastidor) && !vendidos.containsKey(bastidor)){
            this.vendidos.put(bastidor, stock.get(bastidor));
            this.vendidos.get(bastidor).vendido();
            this.stock.remove(bastidor);
        }
    }
}
