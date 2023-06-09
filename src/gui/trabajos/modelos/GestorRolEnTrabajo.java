/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.interfaces.IGestorRolesEnTrabajos;
import gui.personas.modelos.Alumno;
import gui.personas.modelos.Profesor;
import java.time.LocalDate;

/**
 *
 * @author Edunec
 */
public class GestorRolEnTrabajo implements IGestorRolesEnTrabajos{
    
    private static GestorRolEnTrabajo roltrabajo;
    
    private GestorRolEnTrabajo(){
    }
    
    public GestorRolEnTrabajo instanciar(){
    
        if(roltrabajo==null){
            roltrabajo=new GestorRolEnTrabajo();
            return roltrabajo;
        }  
            return null;
    }
    
    public RolEnTrabajo nuevoRolEnTrabajo(LocalDate fechaDesde,Profesor unProfesor,Rol unRol){
        
            
        if(unProfesor==null){
            return null;
        }
        RolEnTrabajo roltrabajo;
        return roltrabajo=new RolEnTrabajo(fechaDesde,unProfesor,unRol);
        }

    @Override
    public RolEnTrabajo nuevoRolEnTrabajo(Profesor profesor, Rol rol, LocalDate fechaDesde) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    
   

    
    
    
    
}
