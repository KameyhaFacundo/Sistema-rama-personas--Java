/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

public abstract class Persona implements Comparable<Persona>{
    private String apellidos;
    private String nombres;
    private int dni;

    /**
     * Constructor
     * @param apellidos apellidos de una persona
     * @param nombres nombres de una persona
     * @param dni dni de una persona
     */
    public Persona(String apellidos, String nombres, int dni) {
        this.apellidos = apellidos;
        this.nombres = nombres;
        this.dni = dni;
    }  

    /**
     * Muestra los apellidos de una persona
     * @return String  - apellidos de una persona
     */
    public String verApellidos() {
        return this.apellidos;
    }

    /**
     * Asigna los apellidos a una persona
     * @param apellidos apellidos de una persona
     */
    public void asignarApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    /**
     * Muestra los nombres de una persona
     * @return String  - nombres de una persona
     */    
    public String verNombres() {
        return this.nombres;
    }

    /**
     * Asigna los nombres a una persona
     * @param nombres nombres de una persona
     */    
    public void asignarNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Muestra el dni de una persona
     * @return int  - dni de una persona
     */    
    public int verDNI() {
        return this.dni;
    }

    /**
     * Convierte una persona a cadena
     * @return String  - cadena que representa una persona ("apellidos, nombres")
     */
    @Override
    public String toString() {
        return this.apellidos + ", " + this.nombres;
    }
  
   public void mostrar(){
   
   System.out.println("["+ dni + "]"+ apellidos+","+nombres);
   }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + this.dni;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Persona other = (Persona) obj;
        if (this.dni != other.dni) {
            return false;
        }
        
        return true;
    }
    
    public int compareTo(Persona o) {
        int a=apellidos.compareToIgnoreCase(o.apellidos) ,n=nombres.compareToIgnoreCase(o.nombres);
        if(a==0){  //Si los apellidos son iguales compara los nombres
            return n;
        }
        return a;
    }
   
    
}
