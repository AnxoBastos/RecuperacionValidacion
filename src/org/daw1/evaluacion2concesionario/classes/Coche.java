/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.daw1.evaluacion2concesionario.classes;

import java.time.LocalDate;

/**
 *
 * @author alumno
 */
public class Coche implements Comparable<Coche>{
    
    private final String marca;
    private final String modelo;
    private final String bastidor;
    private final TipoVehiculo tipo;
    private final double precioCompra;
    private double pvp;
    private final LocalDate fechaCompra;
    private LocalDate fechaVenta; 
    private int margen;
    
    public final java.util.regex.Pattern PATRON_MARCA_MODELO = java.util.regex.Pattern.compile("[A-Za-z0-9]{1,}");
    public final java.util.regex.Pattern PATRON_BASTIDOR = java.util.regex.Pattern.compile("[A-Z]{6}[0-9]{1}[A-Z]{4}[0-9]{6}");
    
    public Coche(String marca, String modelo, String bastidor, TipoVehiculo tipo, double precioCompra, int margen){
        this.marca = marca;
        this.modelo = modelo;
        this.bastidor = bastidor;
        this.tipo = tipo;
        this.precioCompra = precioCompra;
        this.margen = margen;
        this.fechaCompra = LocalDate.now();
        this.pvp = generatePVP();
        this.fechaVenta = null;
    }

    
    
    
    private void checkMarcaModelo(String s){
        if(s == null || s.isBlank() || !PATRON_MARCA_MODELO.matcher(s).matches()){
            throw new IllegalArgumentException();
        }
    }
    
    private void checkBastidor(String s){
        if(s == null || s.isBlank() || !PATRON_BASTIDOR.matcher(s).matches()){
            throw new IllegalArgumentException();
        }
    }
    
    private void checkTipoVehiculo(TipoVehiculo t){
        if(t == null){
            throw new IllegalArgumentException();
        }
    }
    
    private void checkPrecioCompra(double d){
        if(d <= 0){
            throw new IllegalArgumentException();
        }
    }
    
    private void checkMargen(int i){
        if(i < 0){
            throw new IllegalArgumentException();
        }
    }
    
    
    
    
    
    public String getMarca(){
        return marca;
    }

    public String getModelo(){
        return modelo;
    }

    public String getBastidor(){
        return bastidor;
    }

    public TipoVehiculo getTipo(){
        return tipo;
    }

    public double getPrecioCompra(){
        return precioCompra;
    }

    public double getPvp(){
        return pvp;
    }

    public LocalDate getFechaCompra(){
        return fechaCompra;
    }

    public LocalDate getFechaVenta(){
        return fechaVenta;
    }

    public int getMargen(){
        return margen;
    }

    
    
    
    
    public void setMargen(int margen){
        checkMargen(margen);
        this.margen = margen;
        this.pvp = generatePVP();
    }
    
    
    
    
    @Override
    public String toString(){
        return ("Marca: " + this.marca + "\nModelo: " + this.modelo + "\nBastidor: " + this.bastidor + "\nPrecio compra: " + this.precioCompra + "€" + "\nMargen: " + this.margen + "%" + "\nPVP: " + this.pvp + "€" +"\nFecha compra: " + this.fechaCompra + "\nFecha venta: " + this.fechaVenta + "\n\n");
    }

    @Override
    public int compareTo(Coche t){
        java.util.Objects.requireNonNull(t, "La vivienda no puede ser null");
        return this.bastidor.compareTo(t.bastidor);
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        else if(this == obj){
            return true;
        }
        else if(obj instanceof Coche){
            return this.bastidor.equals(((Coche)obj).bastidor);
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode(){
        return java.util.Objects.hashCode(this.bastidor);
    }
    
    
    
    protected void vendido(){
        this.fechaVenta = LocalDate.now();
        this.pvp = generatePVP();
    }
    
    private double generatePVP(){
        return this.precioCompra + ((this.precioCompra * this.margen)/100);
    }
}
