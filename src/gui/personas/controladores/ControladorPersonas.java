/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.controladores;

import gui.interfaces.IControladorPersonas;
import gui.personas.modelos.Alumno;
import gui.personas.modelos.GestorPersonas;
import gui.personas.modelos.ModeloTablaAlumno;
import gui.personas.modelos.ModeloTablaProfesor;
import gui.personas.modelos.Profesor;
import gui.personas.vistas.VentanaPersonas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ControladorPersonas implements IControladorPersonas{

    private final VentanaPersonas ventana;

    GestorPersonas gestor = GestorPersonas.crear();
    
    public ControladorPersonas() {

        this.ventana = new VentanaPersonas(this,true);
        this.ventana.cargarTablaAlumno();
        this.ventana.cargarTablaProfesor();
        this.configurarTablaAlumno();
        this.configurarTablaProfesor();
        this.ventana.setVisible(true);
        this.ventana.setLocationRelativeTo(null);
        this.ventana.setResizable(false);

    }
    
    private void configurarTablaAlumno() {
        JTable tablaAlumno = this.ventana.getTablaAlumnos();
        tablaAlumno.setModel(new ModeloTablaAlumno());
        tablaAlumno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.habilitarBtnAlumno(0); //Deshabilita los botones cada vez que se carga la tabla

    }
    
    private void configurarTablaProfesor() {
        JTable tablaProfesor = this.ventana.getTablaProfesores();
        tablaProfesor.setModel(new ModeloTablaProfesor());
        tablaProfesor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.habilitarBtnProfesor(0); //Deshabilita los botones cada vez que se carga la tabla

    }
    
    public void habilitarBtnAlumno(int opcion){
        if(opcion==1){
            this.ventana.getBtnBorrarAlumno().setEnabled(true);
            this.ventana.getBtnModificarAlumno().setEnabled(true);
        }
        else{
            this.ventana.getBtnBorrarAlumno().setEnabled(false);
            this.ventana.getBtnModificarAlumno().setEnabled(false);
        }
    }

    public void habilitarBtnProfesor(int opcion){
        if(opcion==1){
            this.ventana.getBtnBorrarProfesor().setEnabled(true);
            this.ventana.getBtnModificarProfesor().setEnabled(true);
        }
        else{
            this.ventana.getBtnBorrarProfesor().setEnabled(false);
            this.ventana.getBtnModificarProfesor().setEnabled(false);
        }
    }

    /**
     * Accion a ejecutar cuando se hace click sobre la Tabla de Materias
     * @param evt evento
     */
    public void clickTablaAlumno(MouseEvent evt) {
        JTable tablaAlumno = this.ventana.getTablaAlumnos();
        int fila = tablaAlumno.rowAtPoint(evt.getPoint());
        int columna = tablaAlumno.columnAtPoint(evt.getPoint());
        if ((fila > -1) && (columna > -1)){
            this.habilitarBtnAlumno(1); //Habilita los botones para profesores si se seleccionó un elemento de la tabla
        }
    }
    
    public void clickTablaProfesor(MouseEvent evt) {
        JTable tablaProfesor = this.ventana.getTablaProfesores();
        int fila = tablaProfesor.rowAtPoint(evt.getPoint());
        int columna = tablaProfesor.columnAtPoint(evt.getPoint());
        if ((fila > -1) && (columna > -1)){
            this.habilitarBtnProfesor(1); //Habilita los botones para alumnos si se seleccionó un elemento de la tabla
        }
    }

    @Override
    public void btnNuevoProfesorClic(ActionEvent evt) {
        ControladorNuevoProfesor controlador = new ControladorNuevoProfesor(ventana, -1);
        
    }

    @Override
    public void btnNuevoAlumnoClic(ActionEvent evt) {
       
        ControladorNuevoAlumno controlador = new ControladorNuevoAlumno(ventana, -1);
    }

    @Override
    public void btnModificarProfesorClic(ActionEvent evt) {
        ControladorNuevoProfesor controlador = new ControladorNuevoProfesor( ventana, ventana.getTablaProfesores().getSelectedRow());
        this.ventana.cargarTablaAlumno();
        this.ventana.cargarTablaProfesor();
    }

    @Override
    public void btnModificarAlumnoClic(ActionEvent evt) {
        ControladorNuevoAlumno controlador = new ControladorNuevoAlumno( ventana, ventana.getTablaAlumnos().getSelectedRow());
        this.ventana.cargarTablaAlumno();
        this.ventana.cargarTablaProfesor();
    }

    @Override
    public void btnBorrarProfesorClic(ActionEvent evt) {
           GestorPersonas gp = GestorPersonas.crear();
        if (JOptionPane.showConfirmDialog(null, "Seguro que desea borrar el profesor de DNI:\n "
                + (Integer.parseInt(this.ventana.getTablaProfesores().getValueAt(this.ventana.getTablaProfesores().getSelectedRow(),2).toString()))
                + "?", "Borrar el profesor", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, gp.borrarProfesor(gp.dameProfesor(Integer.parseInt(this.ventana.getTablaProfesores().getValueAt(this.ventana.getTablaProfesores().getSelectedRow(),2).toString()))));
            gp.mostrarAlumnos();
            gp.escribirArchivo();
            this.ventana.cargarTablaAlumno();
            this.ventana.cargarTablaProfesor();
        }
    }

    @Override
    public void btnBorrarAlumnoClic(ActionEvent evt) {
               GestorPersonas gp = GestorPersonas.crear();
        if (JOptionPane.showConfirmDialog(null, "Seguro que desea borrar el alumno de DNI:\n "
                + (Integer.parseInt(this.ventana.getTablaAlumnos().getValueAt(this.ventana.getTablaAlumnos().getSelectedRow(),2).toString()))
                + "?", "Borrar el alumno", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(null, gp.borrarAlumno(gp.dameAlumno(this.ventana.getTablaAlumnos().getValueAt(this.ventana.getTablaAlumnos().getSelectedRow(),3).toString())));
            gp.mostrarAlumnos();
            gp.escribirArchivo();
            this.ventana.cargarTablaAlumno();
            this.ventana.cargarTablaProfesor();
            
     }
    }

    @Override
    public void btnVolverClic(ActionEvent evt) {
               this.ventana.dispose();
        this.habilitarBtnAlumno(0);
        this.habilitarBtnProfesor(0); 
    }

    @Override
    public void btnBuscarProfesorClic(ActionEvent evt) {
        
        String filtro=this.ventana.getBusquedaProfesor().getText();
        System.out.println(filtro);
        JTable tablaProfesor = this.ventana.getTablaProfesores();
        tablaProfesor.setModel(new ModeloTablaProfesor(filtro));
        tablaProfesor.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
    }

    @Override
    public void btnBuscarAlumnoClic(ActionEvent evt) {
  
        String filtro=this.ventana.getBusquedaAlumno().getText();
        System.out.println(filtro);
        JTable tablaalumno = this.ventana.getTablaAlumnos();
        tablaalumno.setModel(new ModeloTablaAlumno(filtro));
        tablaalumno.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void ventanaGanaFoco(WindowEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void txtApellidosProfesorPresionarTecla(KeyEvent evt) {
        String filtro=this.ventana.getBusquedaProfesor().getText();
        if(filtro.isEmpty()){

            this.ventana.cargarTablaProfesor();
        }
    }

    @Override
    public void txtApellidosAlumnoPresionarTecla(KeyEvent evt) {
        String filtro=this.ventana.getBusquedaAlumno().getText();
        if(filtro.isEmpty()){
            
            this.ventana.cargarTablaAlumno();
        }

    }


    
    
}
