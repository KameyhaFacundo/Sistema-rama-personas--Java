/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

import java.util.Objects;

public class Profesor extends Persona {
    private Cargo cargo;

    /**
     * Constructor
     * @param apellidos apellidos de un profesor
     * @param nombres nombres de un profesor
     * @param dni dni de un profesor
     * @param cargo cargo de un profesor
     */
    public Profesor(String apellidos, String nombres, int dni, Cargo cargo) {
        super(apellidos, nombres, dni);
        this.cargo = cargo;
    }

    /**
     * Muestra el cargo de un profesor
     * @return Cargo  - cargo de un profesor
     */    
    public Cargo verCargo() {
        return this.cargo;
    }

    /**
     * Asigna el cargo a un profesor
     * @param cargo cargo de un profesor
     */    
    public void asignarCargo(Cargo cargo) {
        this.cargo = cargo;
    }
    
     @Override
    public String toString() {
        return "Profesor:" +super.verApellidos() +","+super.verNombres()+ " dni:["+ super.verDNI()+"]" ;

    }

    @Override
    public void mostrar() {
        
        
        super.mostrar();
        
        System.out.println("Cargo:");
        cargo.toString();
   
    }
    
    //redefinimos equal para que compare alumnos y profesores por DNI
        @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass().getSuperclass()!=obj.getClass().getSuperclass()){
        return false;
        }
        if(obj.getClass() ==Alumno.class){
        final Persona other1=(Persona)obj;
        if(this.verDNI()!=other1.verDNI()){
        return false;
            }
        }else{
                final Profesor other=(Profesor)obj;
                if(this.verDNI()!=other.verDNI()){
                
                return false;
                }
        
        }
        
        return true;
    }
    
    
    
}
