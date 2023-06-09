
package gui.personas.modelos;

import gui.interfaces.IGestorPersonas;
import gui.trabajos.modelos.GestorTrabajos;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class GestorPersonas implements IGestorPersonas {
    
private List<Persona> listapersonas=new ArrayList();
private static GestorPersonas gestor;
public static final String ARCHIVO = "Personas.txt";

   
 public static GestorPersonas crear(){
        if (gestor == null)
            gestor = new GestorPersonas();
        return gestor;
    }

  
    
    
public String nuevoProfesor(String apellidos, String nombres, int dni, Cargo cargo){
if(apellidos==null || apellidos.isEmpty()){

return"El apellido es incorrecto";

}

if(nombres==null || nombres.isEmpty()){
return"El nombre es incorrecto";

}

if(dni<=0){

return "El DNI es incorrecto";

}

Persona nuevoprofesor=new Profesor(apellidos,nombres,dni,cargo);
                
    if(!listapersonas.contains(nuevoprofesor)){
        
        listapersonas.add(nuevoprofesor);
        return "Se agrego el nuevo profesor";
    }else{
                
        return "No se pudo agregar el nuevo profesor";
    }

}

public String nuevoAlumno(String apellidos, String nombres, int dni, String cx){
if(apellidos==null || apellidos.isEmpty()){

        return"El apellido es incorrecto";

}

if(nombres==null || nombres.isEmpty()){
        return"El nombre es incorrecto";

}

if(dni<=0){

        return "El DNI es incorrecto";

}

if( cx==null || cx.isEmpty()){

        return "el CX es incorrecto";
}

Persona nuevoalumno=new Alumno (apellidos,nombres,dni,cx);
                
    if(!listapersonas.contains(nuevoalumno)){
        
        listapersonas.add(nuevoalumno);
        return "Se agrego el nuevo alumno";
    }else{
                
        return "No se pudo agregar el nuevo alumno";
    }
}
        
public void mostrarPersonas(){

System.out.println("LISTA DE PERSONAS");
    for(Persona p: listapersonas){
       p.mostrar();}


}
public void mostrarProfesores(){

System.out.println("LISTA PROFESORES ");
        for(Persona p: listapersonas)
            if(p instanceof Profesor){
                p.mostrar();}

}

public void mostrarAlumnos(){
System.out.println("LISTA ALUMNOS ");
        for(Persona p: listapersonas)
            if(p instanceof Alumno){
                p.mostrar();}
}

public List<Profesor> buscarProfesores(String apellido){
    List<Profesor> profesoresbuscados =new ArrayList<>();

    if(!apellido.isEmpty()){
        for(Persona p: listapersonas)
            if(p instanceof Profesor){
                if(p.verApellidos().equalsIgnoreCase(apellido.toUpperCase())){
                 
                    profesoresbuscados.add((Profesor)p);
                }
            
            
            }
    
        
        return profesoresbuscados;

}
    return this.obtenerProfesores();
}



public Profesor dameProfesor(int documento){

        

    if(documento>0){
    
        for(Persona p : listapersonas){
            if(p instanceof Profesor){
               if(p.verDNI()==documento){
               
                   return (Profesor)p;
               
               }
    
            }
    
        }

    }
return null;
}

public List<Alumno> buscarAlumnos(String apellido){

    List<Alumno> alumnosbuscados = new ArrayList<>();
if(!apellido.isEmpty()){

        for(Persona p: listapersonas)
            if(p instanceof Alumno){

                if(p.verApellidos().equalsIgnoreCase(apellido.toUpperCase())){

                    alumnosbuscados.add((Alumno)p);
                }
            
            
            }
    
        
        return alumnosbuscados;

}
    return this.obtenerAlumnos();
}

public Alumno dameAlumno(String cx){

    
    if(!cx.isEmpty()|| cx!=null){
        for(Persona p : listapersonas){
            if(p instanceof Alumno){
               if(((Alumno) p).verCX().equals(cx)){
                   p.mostrar();
                   return (Alumno)p;
               
               }
    
            }
    
        }

    }
return null;
}

public String modificarProfesor(Profesor profesor, String apellidos, String nombres, Cargo cargo) {

    if(dameProfesor(profesor.verDNI())==null){
    
        return PROFESOR_INEXISTENTE;
    
    }
    
    Profesor profesormodificar=dameProfesor(profesor.verDNI());
    
    profesormodificar.asignarApellidos(apellidos);
    profesormodificar.asignarNombres(nombres);
    profesormodificar.asignarCargo(cargo);
    gestor.escribirArchivo();
return "Profesor modificado exitosamente";
}
   

public String modificarAlumno(Alumno alumno, String apellidos, String nombres, String cx){
    System.out.println(cx);
    if(dameAlumno(alumno.verCX())==null){
    
    return ALUMNO_INEXISTENTE;
    }
    
            Alumno alumnomodificado= dameAlumno(alumno.verCX());
            alumnomodificado.asignarApellidos(apellidos);
            alumnomodificado.asignarNombres(nombres);
            alumnomodificado.asignarCX(cx);

return "Alumno modificado correctamente";
}

//permite borrar un profesor/alumno.
//no se pueden borrar profesores / alumnos que se encuentren vinculados a un Trabajo.
// deberá informar el resultado de la operación.

public String borrarProfesor(Profesor profesor){

    GestorTrabajos gestor= GestorTrabajos.instanciar();
    
    if(gestor.verificarprofesor(profesor)){
        listapersonas.remove(profesor);
        return "Se borro con exito el profesor";
    }

return TRABAJO_CON_PROFESOR;
}
public String borrarAlumno(Alumno alumno){
GestorTrabajos gestor= GestorTrabajos.instanciar();
    
    if(gestor.verificaralumno(alumno)){
        listapersonas.remove(alumno);
        return "Se borro el alumno con exito";
    }

return TRABAJO_CON_ALUMNO;
}


 public void escribirArchivo(){
    
    try{   
        File f=new File(ARCHIVO);
        FileWriter fw=new FileWriter(f);
        BufferedWriter bufferw = new BufferedWriter(fw);
//recorre la lista y si es profesor escribe los valores de sus atributos
        for(Persona p: listapersonas){
            if(p instanceof Profesor){
            bufferw.write("1,"+p.verApellidos()+ ","+ p.verNombres()+","+p.verDNI()+","+((Profesor) p).verCargo());
            bufferw.newLine();
            //usamos un 1 al comienzo de la linea para saber que es un profesor
            }
        }
        for(Persona p: listapersonas){//recorre la lista y si es alumno escribe los valores de sus atributos
            if(p instanceof Alumno){
            bufferw.write("2,"+p.verApellidos()+ ","+ p.verNombres()+","+p.verDNI()+","+((Alumno) p).verCX());
            bufferw.newLine();
            //usamos un 2 al comienzo de la linea para saber que es un alumno
            }
        }
        bufferw.close();//cerramos buffer
        fw.close();//cerramos filewritter
        
    }catch(IOException e){

        System.out.println("HUBO UN PROBLEMA ESCRIBIENDO EL ARCHIVO");
    
    }
    }
//metodo para leer el archivo de texto de personas
   public void leerarchivo(){
    
    try{
    File f=new File(ARCHIVO);
    FileReader fr=new FileReader(f);
    BufferedReader br=new BufferedReader(fr);
    String cadena;
    while((cadena=br.readLine())!=null){
    
        String [] personas=cadena.split(",");
        //si el primer caracter es 1 entonces es un profesor
        if(personas[0].equals("1")){
            
                nuevoProfesor(personas[1],personas[2],Integer.parseInt(personas[3]),validarCargo(personas[4]) );
               
        }else{
        if(personas[0].equals("2"))//si el primer caracter es un 2 es un alumno
            
                nuevoAlumno(personas[1],personas[2],Integer.parseInt(personas[3]),personas[4]);
               
        }
        }
    
    
    
    br.close(); //cerramos bufferedReader
    fr.close();// cerramos FileReader
   }catch(IOException e){
    
    System.out.println("error al leer el archivo");
    }
    
    
   }

      public List<Persona> getListapersonas() {
        return listapersonas;
    }

    
    private List<Alumno> obtenerAlumnos(){
        List<Alumno> listadeAlumnos=new ArrayList();
        
        for(Persona p: listapersonas){
        if(p instanceof Alumno){
        
        listadeAlumnos.add((Alumno)p);
        
        }
        
        }
    return listadeAlumnos;
    }
    
    private List<Profesor> obtenerProfesores(){
        List<Profesor> listadeProfesores=new ArrayList();
        
        for(Persona p: listapersonas){
        if(p instanceof Profesor){
        
        listadeProfesores.add((Profesor)p);
        }
        }
        return listadeProfesores;
    }
    
    @Override
    public int ordenProfesor(Profesor profesor) {
        if (listapersonas.contains(profesor)){
            return (listapersonas.indexOf(profesor));
        }
        return -1;
    }

  @Override
    public int ordenAlumno(Alumno alumno) {
        if (listapersonas.contains(alumno)){
            return listapersonas.indexOf(alumno);
        }
        return -1;
    }    

    @Override
    public int verUltimoProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int verUltimoAlumno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*
Metodo que toma el valor String leido del archivo y pasa a su respectivo cargo.
*/
    private Cargo validarCargo(String valor){
    
    if(valor.equals("Titular")){
    
    return Cargo.TITULAR;
    
    }
    if(valor.equals("Aux. Docente Graduado")){
    
    return Cargo.ADG;
    
    }
    if(valor.equals("Asociado")){
    
    return Cargo.ASOCIADO;
    
    }
    
    if(valor.equals("Externo")){
    
    return Cargo.EXTERNO;
    
    }
    
    if(valor.equals("Jefe de Trabajos Prácticos")){
    
    return Cargo.JTP;
    
    }
    if(valor.equals("Adjunto")){
    
    return Cargo.ADJUNTO;
    
    }
    
    return null;
    }

    @Override
    public void cancelarProfesor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelarAlumno() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
          
}
