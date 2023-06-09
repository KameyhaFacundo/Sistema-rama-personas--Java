/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.principal.controladores;

import gui.areas.controladores.ControladorAreas;
import gui.areas.modelos.Area;
import gui.areas.modelos.GestorAreas;
import gui.interfaces.IControladorAreas;
import gui.interfaces.IControladorPersonas;
import gui.interfaces.IControladorPrincipal;
import gui.personas.controladores.ControladorPersonas;
import gui.personas.modelos.Cargo;
import gui.personas.modelos.GestorPersonas;
import gui.principal.vistas.VentanaPrincipal;
import gui.trabajos.modelos.AlumnoEnTrabajo;
import gui.trabajos.modelos.GestorTrabajos;
import gui.trabajos.modelos.Rol;
import gui.trabajos.modelos.RolEnTrabajo;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class ControladorPrincipal implements IControladorPrincipal {
    private VentanaPrincipal ventana;

    /**
     * Constructor
     * Muestra la ventana principal
     */
    public ControladorPrincipal() {
        this.ventana = new VentanaPrincipal(this);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setVisible(true);
    }

    /**
     * Acción a ejecutar cuando se selecciona el botón Areas
     * @param evt evento
     */                            
    @Override
    public void btnAreasClic(ActionEvent evt) {
 
    }

    /**
     * Acción a ejecutar cuando se selecciona el botón Personas
     * @param evt evento
     */                            
    @Override
    public void btnPersonasClic(ActionEvent evt) {
        ////***************************Areas**********************************   
//PRUEBA DE AREAS-  PRUEBA DE AREAS-PRUEBA DE AREAS-PRUEBA DE AREAS-PRUEBA DE AREAS-PRUEBA DE AREAS- 
        GestorAreas ga=GestorAreas.instanciar();
       System.out.println(ga.nuevaArea("Software"));
       System.out.println(ga.nuevaArea("Hardware"));
             


       
//        ArrayList<AlumnoEnTrabajo> listaAeT = new ArrayList<>();
      ArrayList <Area> listaAreasT= new ArrayList(); //lista de areas usada en el trabajo
      listaAreasT.add(ga.dameArea("Software"));
      listaAreasT.add(ga.dameArea("Hardware"));

        GestorPersonas gestorp=GestorPersonas.crear();

//Profesor usado en el trabajo de prueba para probar el boton borrar
        System.out.println(gestorp.nuevoProfesor("Juarez", "Juan José", 12345678, Cargo.ASOCIADO));

//        Alumno usado e el trabajo de prueba para probar el boton borrar
        System.out.println(gestorp.nuevoAlumno("Apud", "Josefina", 45367890, "18345"));
//fecha utilizadas en el trabajo
        LocalDate fecha1 = LocalDate.of(2018, 11, 2);
        LocalDate fecha4= LocalDate.of(2017, 8, 12);


//TRABAJO
        ArrayList<AlumnoEnTrabajo> listaAeT = new ArrayList<>();
        ArrayList<RolEnTrabajo> listaRT = new ArrayList<>();
        //Alumnos en el Trabajo
        AlumnoEnTrabajo aET11 = new AlumnoEnTrabajo(fecha1, gestorp.dameAlumno("18345"));
        listaAeT.add(aET11);
        //profesor en trabajo
        RolEnTrabajo rt11= new RolEnTrabajo(fecha4,gestorp.dameProfesor(12345678) , Rol.TUTOR);
        listaRT.add(rt11);

        GestorTrabajos gt=GestorTrabajos.instanciar();
        
  
        gt.nuevoTrabajo("TRABAJO DE PRUEBA", listaAreasT, 6, fecha4, null, listaAeT, listaRT);

      
        
        
        
        
        //gestor que muestra la ventana
        IControladorPersonas gestor = new ControladorPersonas();

    }

    /**
     * Acción a ejecutar cuando se selecciona el botón Trabajos
     * @param evt evento
     */                            
    @Override
    public void btnTrabajosClic(ActionEvent evt) {
    }
    
    /**
     * Acción a ejecutar cuando se selecciona el botón Salir
     * @param evt evento
     */                            
    @Override
    public void btnSalirClic(ActionEvent evt) {
        int opcion = JOptionPane.showOptionDialog(null, CONFIRMACION, TITULO_VENTANA, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, this);
        if (opcion == JOptionPane.YES_OPTION) {
            this.ventana.dispose();
            System.exit(0);
        }       
    }
        
    public static void main(String[] args) {
        IControladorPrincipal controladorPrincipal = new ControladorPrincipal();
        
    }    
}
