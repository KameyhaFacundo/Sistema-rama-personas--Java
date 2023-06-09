/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.table.AbstractTableModel;


public class ModeloTablaProfesor extends AbstractTableModel {
    private final List<String> nombresColumnas = new ArrayList<>(); 
    private final GestorPersonas gp = GestorPersonas.crear();
    private List<Profesor> profesores = new ArrayList<>();
    String filtro;

    public ModeloTablaProfesor() {
        this.nombresColumnas.add("Apellidos");
        this.nombresColumnas.add("Nombres");
        this.nombresColumnas.add("DNI");  
        this.nombresColumnas.add("Cargo");
        gp.leerarchivo();
        for(Persona p: gp.getListapersonas())
            if(p instanceof Profesor){
                this.profesores.add((Profesor)p);
               
            }
        Collections.sort(profesores);
    }
    public ModeloTablaProfesor(String filtro) {
        this();
        this.filtro=filtro;
        gp.leerarchivo();
        this.profesores = gp.buscarProfesores(filtro);
        Collections.sort(profesores);

    }
    
    public Profesor obtenerProfesor(int fila) {
        return this.profesores.get(fila);
    }

    @Override
    public int getRowCount() {
        return this.profesores.size();
    }

    @Override
    public int getColumnCount() {
        return this.nombresColumnas.size();
    }

    /**
    * Obtiene el nombre de una columna
    * @param columna columna sobre la que se quiere obtener el nombre
    * @return String  - nombre de la columna especificada
    */                        
    @Override
    public String getColumnName(int columna) {
        return this.nombresColumnas.get(columna);
    }
    
    @Override
    public Object getValueAt(int fila, int columna) {
        Profesor i = this.profesores.get(fila);
        switch (columna) {
            case 0: return i.verApellidos();
            case 1: return i.verNombres();
            case 2: return i.verDNI();
            case 3: return i.verCargo();
            default: return i.verApellidos();
        }
    }
}
