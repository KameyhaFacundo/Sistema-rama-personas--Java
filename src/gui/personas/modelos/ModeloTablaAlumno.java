/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author MB
 */
public class ModeloTablaAlumno extends AbstractTableModel {
  
    private final List<String> nombresColumnas = new ArrayList<>(); 
    private final GestorPersonas gp = GestorPersonas.crear();//crear();
    private List<Alumno> alumnos = new ArrayList<>();
    String filtro;

    public ModeloTablaAlumno() {
        this.nombresColumnas.add("Apellidos");
        this.nombresColumnas.add("Nombres");
        this.nombresColumnas.add("DNI");   
        this.nombresColumnas.add("CX");
        gp.leerarchivo();
        for(Persona p: gp.getListapersonas())
            if(p instanceof Alumno){
                this.alumnos.add((Alumno)p);
               
            }
       Collections.sort(alumnos);
      
    }
    
    public ModeloTablaAlumno(String filtro) {
        this();
        this.filtro=filtro;
        gp.leerarchivo();

        this.alumnos = gp.buscarAlumnos(filtro);

        Collections.sort(alumnos);

    }
    
    
    public Alumno obtenerAlumno(int fila) {
        return this.alumnos.get(fila);
    }

    @Override
    public int getRowCount() {
        return this.alumnos.size();
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
        Alumno i = this.alumnos.get(fila);
        switch (columna) {
            case 0: return i.verApellidos();
            case 1: return i.verNombres();
            case 2: return i.verDNI();
            case 3: return i.verCX();
            default: return i.verCX();
        }
    }
}
