/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.controladores;

import gui.personas.modelos.Alumno;
import gui.personas.modelos.GestorPersonas;
import gui.personas.vistas.VentanaAMAlumno;
import gui.personas.vistas.VentanaPersonas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import gui.interfaces.IControladorAMAlumno;


public class ControladorNuevoAlumno implements IControladorAMAlumno {
 
    
    private final VentanaAMAlumno ventAlumno;
    private final VentanaPersonas ventPersonas;
    private final GestorPersonas gp = GestorPersonas.crear();
    int codigo=0;
    Alumno alumnoM;

    /**
     * Constructor de ControladorAMAlumno
     * @param i es una variable de control para saber si estoy crendo un nuevo Alumno o editando un Alumno
 Si su valor es 0, estoy en el caso de crear un Alumno.
 Si su valor es -1, no se ha seleccionado nada de la tablaAlumno.
 Si su valor es positivo, representa el codigo de un Alumno.
 Su valor es guardado en la variable codigo para ser usada luego.
     */  
    public ControladorNuevoAlumno( VentanaPersonas ventPersonas, int i) {
        
       this.ventPersonas = ventPersonas;
        ventAlumno = new VentanaAMAlumno( true, ventPersonas, this);
        this.ventAlumno.setResizable(false);
        codigo=i;
        switch (i) { //i es el codigo del Alumno
            case -1: // Si no se selecciono ningun elemento i=-1 , entonces selecciono el boton nuevo
                ventAlumno.setTitle("Agregar un Alumno");
                break;
                    
            default : //Cuando voy a editar i>=0, se modifica el alumno correspondiente
                ventAlumno.setTitle("Modificar los datos de un alumno");
                Alumno unAlumno = gp.dameAlumno(this.ventPersonas.getTablaAlumnos().getValueAt(this.ventPersonas.getTablaAlumnos().getSelectedRow(),3).toString());
                alumnoM=gp.dameAlumno(this.ventPersonas.getTablaAlumnos().getValueAt(this.ventPersonas.getTablaAlumnos().getSelectedRow(),3).toString());
                this.ventAlumno.getTxtNombres().setText(unAlumno.verNombres());
                this.ventAlumno.getTxtApellidos().setText(unAlumno.verApellidos());
                this.ventAlumno.getTxtCX().setText(unAlumno.verCX());
                this.ventAlumno.getTxtDNI().setText(Integer.toString(unAlumno.verDNI()));
                this.ventAlumno.getTxtDNI().setEditable(false);
               

                break;
                
        }
        ventAlumno.setLocationRelativeTo(null);
        ventAlumno.setVisible(true);
    }
    
    
    
    @Override
    public void btnGuardarClic(ActionEvent evt) {

//si i=-1 entonces estoy agregando un alumno nuevo.
        if(codigo==-1){
            try{
                if (ventAlumno.campoApellido().isEmpty() || ventAlumno.campoNombre().isEmpty() || ventAlumno.campoCX().isEmpty() || ventAlumno.campoDNI().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");

                }
                else{
                gp.nuevoAlumno(ventAlumno.campoApellido(), ventAlumno.campoNombre(), Integer.parseInt(ventAlumno.campoDNI()), ventAlumno.campoCX());
                ventPersonas.cargarTablaAlumno();
                ventAlumno.dispose();
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
            }
        }
        else{//cuando i>=0, estoy modificando el alumno
            try{ //Actúa de manera similar a lo antes explicado, solo que para este caso, modifica
                    if(ventAlumno.campoApellido().isEmpty() || ventAlumno.campoNombre().isEmpty() || ventAlumno.campoCX().isEmpty()){
                        JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");

                    }
                    else{
                        
       

                        String B=ventAlumno.campoCX();
                        System.out.println(B);
                        ventAlumno.getTxtDNI().setEditable(false);
                      

                        gp.modificarAlumno(alumnoM, ventAlumno.campoApellido(), ventAlumno.campoNombre(),ventAlumno.campoCX());    
                       
                        ventPersonas.cargarTablaAlumno();
                        ventAlumno.dispose();
                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
                    System.out.println("se capturo una excepcion");
                }
            }
        gp.escribirArchivo();//despues de modificar los alumnos se escribe en el archivo de personas la lista modificada
    }
        
    @Override
    public void btnCancelarClic(ActionEvent evt) {
        ventAlumno.dispose();
    }

    @Override
    public void txtApellidosPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (!(k==32 || k==8 || (evt.getKeyCode() == KeyEvent.VK_ENTER) || k==127 || Character.isAlphabetic(c))) {
            //Solo permite que se presionen numeros, letras, y las teclas Backspace y Enter
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Ingrese letras o espacios", "Apellidos - Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void txtNombresPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (!(k==32 || k==8 || (evt.getKeyCode() == KeyEvent.VK_ENTER) || k==127 || Character.isAlphabetic(c))) {
            //Solo permite que se presionen numeros, letras, y las teclas Backspace y Enter
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Ingrese letras o espacios", "Nombres - Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void txtDocumentoPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (!(k==32 || k==8 || (evt.getKeyCode() == KeyEvent.VK_ENTER) || k==127 || Character.isDigit(c))) {
            //Solo permite que se presionen numeros, letras, y las teclas Backspace y Enter
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Ingrese números solamente", "DNI - Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void txtCXPresionarTecla(KeyEvent evt) {
        char c = evt.getKeyChar();
        int k = (int) evt.getKeyChar();//k = al valor de la tecla presionada
        if (!(k==32 || k==8 || (evt.getKeyCode() == KeyEvent.VK_ENTER) || k==127 || Character.isDigit(c))) {
            //Solo permite que se presionen numeros, letras, y las teclas Backspace y Enter
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);//Limpiar el caracter ingresado
            JOptionPane.showMessageDialog(null, "Ingrese números solamente", "CX - Advertencia", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
}
