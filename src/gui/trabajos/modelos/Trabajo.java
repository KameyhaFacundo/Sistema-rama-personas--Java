
package gui.trabajos.modelos;

import gui.areas.modelos.Area;
import gui.personas.modelos.Profesor;
import gui.seminarios.modelos.NotaAprobacion;
import gui.seminarios.modelos.Seminario;
//import gui.seminarios.modelos.GestorSeminarios;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;



public class Trabajo implements Comparable<Trabajo>{
    
    private String titulo;
    private String tema;
    private int duracion;
    private LocalDate fechadepresentacion;
    private LocalDate FechaAprobacion;
    private LocalDate fechadepresentacionfinal;
    private List <AlumnoEnTrabajo> listaalumnosentrabajo;
    private List <RolEnTrabajo> listarolentrabajo;
    private List <Area> listaareas;
    private List <Seminario> listaSeminario= new ArrayList<>();

    public Trabajo(String titulo, List<Area> listaareas, int duracion, LocalDate fechadepresentacion, List<AlumnoEnTrabajo> listaalumnosentrabajo, List<RolEnTrabajo> listarolentrabajo) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.fechadepresentacion = fechadepresentacion;
        this.listaalumnosentrabajo = listaalumnosentrabajo;
        this.listarolentrabajo = listarolentrabajo;
        this.listaareas = listaareas;
        
    }
    public Trabajo(String titulo, List<Area> listaareas, int duracion, LocalDate fechadepresentacion,LocalDate FechaAprobacion, List<AlumnoEnTrabajo> listaalumnosentrabajo, List<RolEnTrabajo> listarolentrabajo) {
        this.titulo = titulo;
        this.duracion = duracion;
        this.fechadepresentacion = fechadepresentacion;
        this.listaalumnosentrabajo = listaalumnosentrabajo;
        this.listarolentrabajo = listarolentrabajo;
        this.listaareas = listaareas;
        
    }
    
    
    public void agregarAlumnos(AlumnoEnTrabajo alumno){
    
        listaalumnosentrabajo.add(alumno);
    
    }
    public void agregarSeminario(Seminario uno){
    if(!listaSeminario.contains(uno)){
    
    listaSeminario.add(uno);
    }
    
    }
    
    public String agregarProfesor(RolEnTrabajo trab){
    String a;
                if(!listarolentrabajo.contains(trab)){
                   
                   listarolentrabajo.add(trab);
                   return a="se agrego"; 
                }else{
                    return a="No se agregó";
                }
    }
    
    
    public Trabajo(String titulo, String tema, int duracion, LocalDate fechadepresentacion, LocalDate fechadeaprobacion, LocalDate fechadepresentacionfinal) {
        this.titulo = titulo;
        this.tema = tema;
        this.duracion = duracion;
        this.fechadepresentacion = fechadepresentacion;
        this.FechaAprobacion = fechadeaprobacion;
        this.fechadepresentacionfinal = fechadepresentacionfinal;
        
    }

    public Trabajo(String titulo, String tema, int duracion, LocalDate fechadepresentacion, LocalDate fechadeaprobacion) {
        this.titulo = titulo;
        this.tema = tema;
        this.duracion = duracion;
        this.fechadepresentacion = fechadepresentacion;
        this.FechaAprobacion = fechadeaprobacion;
        
    }
    
    
    
    public void mostrar(){
//    int c=0;
//    System.out.println(".........................");
//    System.out.println("Trabajo: "+ this.titulo + "\nduracion: " + this.duracion+ "\nFecha de presentacion: "+ fechadepresentacion+"\nFecha de aprobacion: " + this.FechaAprobacion + "\nFecha de exposicion: " );
//    System.out.println("\nAlumnos:");
//    System.out.println("------------------------");
//     
////  recorre la lista listaalumnosentrabajo y muestra los alumnos con la funcion mostrar de la clase alumno
//    for (int i = 0; i < listaalumnosentrabajo.size(); i++) {
//                listaalumnosentrabajo.get(i).getAlumno().mostrar();
//
//    }
//    
//    System.out.println("\nTutor");
//    System.out.println("------------------------");
//
////  recorre la lista listarolentrabajo y si un elemento(profesor) tiene rol tutor lo muestra en pantalla
//    for (int i = 0; i < listarolentrabajo.size(); i++) {
//
//            if(listarolentrabajo.get(i).getRol()==Rol.TUTOR){
//    
//                listarolentrabajo.get(i).getProfesor().mostrar();
//
//            }
//
//    }  
////  recorre la lista y si hay un jurado entonces se muestra el titulo JURADO:
//    for (int i = 0; i < listarolentrabajo.size(); i++) {
//                if(listarolentrabajo.get(i).getRol()==Rol.JURADO){
//                    c++;
//                    if(c==1){
//                    System.out.println("\nJURADO:");
//                    System.out.println("------------------------");
//
//                    }
//                }
//
//    }
//
////  recorre listarolentrabajo y si un elemento(profesor) tiene el rol jurado lo muestra en pantalla
//    for (int i = 0; i < listarolentrabajo.size(); i++) {
//                if(listarolentrabajo.get(i).getRol()==Rol.JURADO){
//
//
//                    listarolentrabajo.get(i).getProfesor().mostrar();
//                }
//
//    }
//
//                if(listaSeminario!=null){         
//                    for (int i = 0; i < listaSeminario.size(); i++) {
//                    listaSeminario.get(i).mostrar();
//                            }
//                }
    }
    
//    public String nuevoSeminario(LocalDate fechaExposicion, NotaAprobacion notaAprobacion,
//String observaciones)
// deberá solicitar al GestorSeminarios la validación de datos.
//deberá verificar que la fechaExposicion(del seminario) sea posterior a la fechaAprobacion del Trabajo.
//deberá crear y agregar el seminario a la lista siempre que no exista otro igual (dos seminarios son
//iguale si tienen la misma fecha de exposición).
// deberá informar el resultado de la operación.
 public String nuevoSeminario(LocalDate fechaExposicion, NotaAprobacion notaAprobacion,String observaciones){
 
 //GestorSeminarios gestor=null;
// gestor.instanciar();
//     
//     if(gestor.validarSeminario(fechaExposicion, notaAprobacion, observaciones).equals("INVALIDO")){
//     return "Datos de seminario no validos";
//     }
//     //controlar fecha;
//     
//     Seminario unSeminario=new Seminario(fechaExposicion, notaAprobacion, observaciones);
//     if(!listaSeminario.contains(unSeminario)){
// 
//        listaSeminario.add(unSeminario);
//        return"Se creo el seminario y se lo agrego a la lista";
//    }

   return "No se pudo crear el seminario";
 }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public LocalDate getFechadepresentacion() {
        return fechadepresentacion;
    }

    public void setFechadepresentacion(LocalDate fechadepresentacion) {
        this.fechadepresentacion = fechadepresentacion;
    }

    public LocalDate getFechaAprobacion() {
        return FechaAprobacion;
    }

    public void setFechaAprobacion(LocalDate fechaAprobacion) {
        this.FechaAprobacion = fechaAprobacion;
    }

    public LocalDate getFechadepresentacionfinal() {
        return fechadepresentacionfinal;
    }

    public void setFechadepresentacionfinal(LocalDate fechadepresentacionfinal) {
        this.fechadepresentacionfinal = fechadepresentacionfinal;
    }
    
    public List <Area> getListaareas() {
        return listaareas;
    }

    public void setListaareas(List <Area> listaareas) {
        this.listaareas = listaareas;
    }
    
    public List <AlumnoEnTrabajo> getListaalumnosentrabajo() {
        return listaalumnosentrabajo;
    }

    public void setListaalumnosentrabajo(List <AlumnoEnTrabajo> listaalumnosentrabajo) {
        this.listaalumnosentrabajo = listaalumnosentrabajo;
    }

    public List <RolEnTrabajo> getListarolentrabajo() {
        return listarolentrabajo;
    }

    public void setListarolentrabajo(List <RolEnTrabajo> listarolentrabajo) {
        this.listarolentrabajo = listarolentrabajo;
    }

    public List <Seminario> getListaSeminario() {
        return listaSeminario;
    }

    public void setListaSeminario(List <Seminario> listaSeminario) {
        this.listaSeminario = listaSeminario;
    }


    @Override
    public String toString() {
        return "Trabajo{" + "titulo=" + titulo + ", tema=" + tema + ", duracion=" + duracion + ", fechadepresentacion=" + fechadepresentacion + ", fechadeaprobacion=" + FechaAprobacion + ", fechadepresentacionfinal=" + fechadepresentacionfinal + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.titulo);
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
        final Trabajo other = (Trabajo) obj;
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Trabajo trab) {
        
        if(fechadepresentacion.compareTo(trab.fechadepresentacion)==0){        
        
            return trab.titulo.compareTo(titulo);
            }
        return fechadepresentacion.compareTo(trab.fechadepresentacion);

    }

    
    
    
    
}
