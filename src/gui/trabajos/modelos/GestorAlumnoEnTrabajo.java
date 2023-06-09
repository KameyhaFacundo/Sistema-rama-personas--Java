/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.interfaces.IGestorAlumnosEnTrabajos;
import gui.personas.modelos.Alumno;
import java.time.LocalDate;

/**
 *
 * @author Edunec
 */
public class GestorAlumnoEnTrabajo implements IGestorAlumnosEnTrabajos {
    
    private static GestorAlumnoEnTrabajo gestor;
    
    
    private GestorAlumnoEnTrabajo(){


    }
    
    public GestorAlumnoEnTrabajo instanciar(){
    
        if(gestor ==null){
        
            gestor= new GestorAlumnoEnTrabajo();
        
        }
    
    return gestor;
    }
    
    
        public AlumnoEnTrabajo nuevoAlumnoEnTrabajo( LocalDate fechaDesde, Alumno unAlumno){
        
            
        if(unAlumno==null){
        
        return null;
        
        }
        AlumnoEnTrabajo alumno;
        
        return alumno=new AlumnoEnTrabajo(fechaDesde,unAlumno);
        
        
        }

    @Override
    public AlumnoEnTrabajo nuevoAlumnoEnTrabajo(Alumno alumno, LocalDate fechaDesde) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
