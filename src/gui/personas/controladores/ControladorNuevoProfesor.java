/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.controladores;

import gui.personas.modelos.Cargo;
import gui.personas.modelos.GestorPersonas;
import gui.personas.modelos.Profesor;
import gui.personas.vistas.VentanaAMProfesor;
import gui.personas.vistas.VentanaPersonas;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import gui.interfaces.IControladorAMProfesor;


public class ControladorNuevoProfesor implements IControladorAMProfesor {

    private final VentanaAMProfesor ventProfesor;
    private final VentanaPersonas ventPersonas;
    private final GestorPersonas gp = GestorPersonas.crear();
    int codigo=0;
    private Profesor profesorM;//referencia al profesor que se modificara

    public ControladorNuevoProfesor( VentanaPersonas ventPersonas, int i) {

        this.ventPersonas = ventPersonas;
        ventProfesor = new VentanaAMProfesor( true, ventPersonas, this);
        this.ventProfesor.setResizable(false);
        codigo=i;
        switch (i) { //i es el codigo del Profesor
            case -1: // Cuando voy a crear, i=-1 entonces estoy seleccionando el boton nuevo
                ventProfesor.setTitle("Crear un Profesor");
                break;
            default : //Cuando voy a editar, si i>=0 estoy editando el profesor correspondiente
                ventProfesor.setTitle("Modificar los datos de un Profesor");
                Profesor unProfesor = gp.dameProfesor(Integer.parseInt(this.ventPersonas.getTablaProfesores().getValueAt(this.ventPersonas.getTablaProfesores().getSelectedRow(), 2).toString()));
                profesorM=gp.dameProfesor(Integer.parseInt(this.ventPersonas.getTablaProfesores().getValueAt(this.ventPersonas.getTablaProfesores().getSelectedRow(), 2).toString()));
                this.ventProfesor.getTxtNombres().setText(unProfesor.verNombres());
                this.ventProfesor.getTxtApellidos().setText(unProfesor.verApellidos());
                this.ventProfesor.getTxtDNI().setText(Integer.toString(unProfesor.verDNI()));
                this.ventProfesor.getTxtDNI().setEditable(false);
                this.ventProfesor.getBoxCargo().setSelectedItem(unProfesor.verCargo());
                break;
        }
        ventProfesor.setLocationRelativeTo(null);
        ventProfesor.setVisible(true);
    }

    
    
    @Override
    public void btnGuardarClic(ActionEvent evt) {
//si i=-1 entonces estoy guardando un profesor nuevo.
        if(codigo==-1){
            try{
                if (ventProfesor.campoApellido().isEmpty() || ventProfesor.campoNombre().isEmpty() || ventProfesor.campoDNI().isEmpty()){
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
                    
                }
                else{
                gp.nuevoProfesor(ventProfesor.campoApellido(), ventProfesor.campoNombre(), Integer.parseInt(ventProfesor.campoDNI()), (Cargo) ventProfesor.getBoxCargo().getSelectedItem());
                ventPersonas.cargarTablaProfesor();
                ventProfesor.dispose();
                }
            }
            catch(Exception e){
                JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
            }
        }
        else{//cuando i>=0 entonces estoy modificando un profesor
            try{ //Actúa de manera similar a lo antes explicado, solo que para este caso, modifica 
                    if(ventProfesor.campoApellido().isEmpty() || ventProfesor.campoNombre().isEmpty()){
                        JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
                       
                    }
                    else{
                        ventProfesor.getTxtDNI().setEditable(false);
                        gp.modificarProfesor(profesorM, ventProfesor.campoApellido(), ventProfesor.campoNombre(), (Cargo) ventProfesor.getBoxCargo().getSelectedItem());
                        ventPersonas.cargarTablaProfesor();
                        ventProfesor.getTxtDNI().setEditable(true);
                        ventProfesor.dispose();
                    }
                }
                catch (Exception e){
                    JOptionPane.showMessageDialog(null, "No puede haber campos vacíos");
                }
            }
        
       gp.escribirArchivo();//se escribe el archivo con la lista modificada.
    }

    @Override
    public void btnCancelarClic(ActionEvent evt) {
        ventProfesor.dispose();
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
    
    
}
