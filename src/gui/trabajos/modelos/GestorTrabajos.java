
package gui.trabajos.modelos;

import gui.areas.modelos.Area;
import gui.interfaces.IGestorTrabajos;
import gui.personas.modelos.Alumno;
import gui.personas.modelos.Profesor;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class GestorTrabajos implements IGestorTrabajos {
    private static GestorTrabajos gestor;
    private List<Trabajo> listatrabajos = new ArrayList();
    private GestorTrabajos (){
    
    }
    
    public static GestorTrabajos instanciar(){
    
        if(gestor ==null){
        
            gestor= new GestorTrabajos();
        
        }
    
    return gestor;
    }
    
    public String nuevoTrabajo(String titulo, ArrayList<Area> listaAreas, int duracion, LocalDate fechaPresentacion, LocalDate fechaAprobacion, ArrayList<AlumnoEnTrabajo> aet, ArrayList<RolEnTrabajo> ret) { 

        Trabajo trabajo;
        
        
    if ((titulo == null) || (titulo.isEmpty())){
    return null;
    }
    if ((listaAreas == null) || (listaAreas.isEmpty())){
    return null;
    }            
    if (duracion<=0){
        return null;
    }   
    if (fechaPresentacion == null){
        return null;
    }    
                                    
    if(fechaAprobacion!=null){

        if(fechaPresentacion.compareTo(fechaAprobacion)>0){

            return null;
        }

    }

//    if(aet==null || (aet.isEmpty())){
//    System.out.println("NO5");
//    return null;
//    }else{
//        int tamanio=aet.size();
//        int c=0;
//   
//        for(int i=tamanio;i>=0;i--){
//            do{
//                if(aet.get(c).equals(aet.get(i))){
//    
//                return null;
//                }
//            c++;
//            }while(c<i);
//    
//        c=0;
//        }//
    if(obtenerTutor(ret)==null){

    return null;
    }

    if(!validarTutorYCotutor(obtenerTutor(ret),obtenerCotutor(ret))){

    return null;

    }
    if(fechaAprobacion!=null){
            if(!validarJurado(obtenerJurado(ret))){

            return null;

            }
    

    for(int i=0;i<obtenerJurado(ret).size();i++){

        if(obtenerTutor(ret).equals(obtenerJurado(ret).get(i))|| obtenerCotutor(ret).equals(obtenerJurado(ret).get(i))){

        return null;
        }
    }
    }
//
    Trabajo nuevotrabajo=new Trabajo(titulo,listaAreas,duracion,fechaPresentacion,fechaAprobacion,aet,ret);
//
    if(!listatrabajos.contains(nuevotrabajo)){
    listatrabajos.add(nuevotrabajo);
    }  


    return "se creo el trabajo";
  
    }
   

   public Trabajo dameTrabajo(String titulo){
   
       for(int i=0;i<listatrabajos.size();i++){
        if(listatrabajos.get(i).getTitulo()==titulo){
                
            return listatrabajos.get(i);
        }
       }
   return null;
   }
   

   
   public ArrayList<Trabajo> buscarTrabajos(String filtro){
   
       ArrayList<Trabajo> listadetrabajosbuscados=new ArrayList();
   
   for(int i=0;i<listatrabajos.size();i++){
        if(listatrabajos.get(i).getTitulo()==filtro){
                
            listadetrabajosbuscados.add(listatrabajos.get(i));
            
        }
       }
   
   
   return listadetrabajosbuscados;
   }
   
       public void mostrarTrabajos(){
       System.out.println(listatrabajos.get(0).getTitulo());
              System.out.println(listatrabajos.get(0).getDuracion());
       System.out.println(listatrabajos.get(0).getListaalumnosentrabajo().get(0));
       System.out.println(listatrabajos.get(0).getListarolentrabajo().get(0));


       
       
       }

   
   
   
    private static Profesor obtenerTutor(ArrayList<RolEnTrabajo> listaRolEnTrabajo) {        
        
        Profesor profesor;

        
        for (int i = 0; i < listaRolEnTrabajo.size(); i++) {

            if(listaRolEnTrabajo.get(i).getRol()==Rol.TUTOR){
    
                profesor=listaRolEnTrabajo.get(i).getProfesor();
                return profesor;
            }

    }  
        
        
        return null;
    }
    
    
    private static Profesor obtenerCotutor(ArrayList<RolEnTrabajo> listaRolEnTrabajo) {        

        Profesor profesor;
  
        for (int i = 0; i < listaRolEnTrabajo.size(); i++) {

            if(listaRolEnTrabajo.get(i).getRol()==Rol.COTUTOR){
    
                  profesor=listaRolEnTrabajo.get(i).getProfesor();
                  return profesor;
            }

    } 
        return null;
    }

    
    private static ArrayList<Profesor> obtenerJurado(ArrayList<RolEnTrabajo> listaRolEnTrabajo) {
        int c=0;
        
        ArrayList<Profesor> jurado =new ArrayList<>();
     

    for (int i = 0; i < listaRolEnTrabajo.size(); i++) {
                if(listaRolEnTrabajo.get(i).getRol()==Rol.JURADO){

                    jurado.add(listaRolEnTrabajo.get(i).getProfesor());
                    
                }

    }
        return null;
    }        
    
    
    private static boolean validarTutorYCotutor(Profesor tutor, Profesor cotutor) {
       
        if(cotutor==null){
            
           
        return true;
        
        }
        if(!tutor.equals(cotutor)){
            
            return true;
            
        }
        
        
        return false;
    }    
    
   
    private static boolean validarJurado(ArrayList<Profesor> jurado) {
        
        for(int i=0;i<jurado.size();i++){
        
            if(!jurado.get(0).equals(jurado.get(1))&& !jurado.get(0).equals(jurado.get(2))&& !jurado.get(1).equals(jurado.get(2))){
            
                return true;
            
            }
            
            
        
        }
        
        
        return false;
    }    
    
    public Boolean validarArea(Area area){
    
    for(int i=0;i<listatrabajos.size();i++){
        
        for(int c=0;i<listatrabajos.get(i).getListaareas().size();c++){    
            if(listatrabajos.get(i).getListaareas().contains(area)){
                
                return true;
    
            }
        }
    }
    
    return false;
    
    } 
    
    public String borrarTrabajo(Trabajo trabajo){
    
        if(trabajo.getListaSeminario()==null){
    
        listatrabajos.remove(trabajo);
    
        return "Se borro el trabajo";
    
        } 
        
    return "No se puede eliminar el trabajo por que tiene un seminario presentado";
    }
    
    public boolean verificarprofesor(Profesor profesor){
    
        for(int i=0;i<listatrabajos.size();i++){
            for(int c=0;c<listatrabajos.get(i).getListarolentrabajo().size();c++){
                if(listatrabajos.get(i).getListarolentrabajo().get(i).getProfesor().equals(profesor)){
                    System.out.println(listatrabajos.get(i).getListarolentrabajo().get(i).getProfesor());

                 return false;
                }
            }
        }
        
            return true;
    }
    
    public boolean verificaralumno(Alumno alumno){
    
        for(int i=0;i<listatrabajos.size();i++){
            for(int c=0;c<listatrabajos.get(i).getListaalumnosentrabajo().size();c++){
                if(listatrabajos.get(i).getListaalumnosentrabajo().get(c).getAlumno().equals(alumno)){
    
                 return false;
                }
            }
        }
        
            return true;
    }
 
    public String finalizarTrabajo(Trabajo trabajo, LocalDate fechaExposicion){
    
       int pos=listatrabajos.indexOf(trabajo);
       //se le asigna fecha de exposicion al trabajo
       listatrabajos.get(pos).setFechadepresentacion(fechaExposicion);
       //recorre la lista alumnoentrabajo y a cada alumno de la lista se le asigna la fecha hasta igual a la fecha de exposicion.
       for(int i=0;i<listatrabajos.get(pos).getListaalumnosentrabajo().size();i++){
      
           listatrabajos.get(pos).getListaalumnosentrabajo().get(i).setFechaHasta(fechaExposicion);

       }
      //recorre la lista rolentrabajo y a cada profesor de la lista se le asigna la fecha hasta igual a la fecha de exposicion.

         for(int i=0;i<listatrabajos.get(pos).getListarolentrabajo().size();i++){
      
           listatrabajos.get(pos).getListarolentrabajo().get(i).setFechaHasta(fechaExposicion);

        }
    
    return "trabajo finalizado";
    }
    
    public String finalizarAlumno(Trabajo trabajo, Alumno alumno, LocalDate fechaHasta, String razon){
       
        int a=listatrabajos.indexOf(trabajo);
        int b=listatrabajos.get(a).getListaalumnosentrabajo().indexOf(alumno);
        
        if(a>=0 && b>=0){
        listatrabajos.get(a).getListaalumnosentrabajo().get(b).setFechaHasta(fechaHasta);
        listatrabajos.get(a).getListaalumnosentrabajo().get(b).setRazon(razon);
        return "Se finalizo el alumno";
        }
        
    
    return "No se pudo finalizar el alumno";
    }
    
    public String finalizarprofesor(Trabajo trabajo, Profesor profesor, LocalDate fechaHasta, String razon){
       
        int a=listatrabajos.indexOf(trabajo);
        int b=listatrabajos.get(a).getListarolentrabajo().indexOf(profesor);
        
        if(a>=0 && b>=0){
        listatrabajos.get(a).getListarolentrabajo().get(b).setFechaHasta(fechaHasta);
        listatrabajos.get(a).getListarolentrabajo().get(b).setRazon(razon);
        return "Se finalizo el profesor";
        }
        
    
    return "No se pudo finalizar el profesor";
    }
    

// reemplaza un Profesor que cumple un Rol en un Trabajo, por otro Profesor.
// deberá realizar las validaciones de datos que sean necesarias
// deberá asignar las fecha de finalización y razón al Profesor que reemplaza.
//considerar que el nuevo profesor tiene el mismo rol del profesor que reemplaza, y comienza su tarea
//en la fecha en que finaliza el profesor que se reemplaza. No puede coincidir con los Profesores que se
//encuentran en otros roles asociados a este mismo Trabajo.

    public String reemplazarProfesor(Trabajo trabajo, Profesor profesorReemplazado, LocalDate fechaHasta, String razon, Profesor nuevoProfesor){
    
        GestorTrabajos gestor= GestorTrabajos.instanciar();
        //posicion del trabajo a modificar en la lista de trabajos del gestor trabajo.
        int posicion= gestor.listatrabajos.indexOf(trabajo);
        //posicion del profesor en la lista rol en trabajo 
        for(int i=0; i<gestor.listatrabajos.get(posicion).getListarolentrabajo().size();i++){
        if(gestor.listatrabajos.get(posicion).getListarolentrabajo().get(i).getProfesor().equals(profesorReemplazado)){
            int posicionprofesor=gestor.listatrabajos.get(posicion).getListarolentrabajo().indexOf(i);
            
            gestor.listatrabajos.get(posicion).getListarolentrabajo().get(i).setProfesor(nuevoProfesor);
            
            
            return "Se modifico al profesor: "+ nuevoProfesor.verApellidos()+ ","+ nuevoProfesor.verNombres();
            
            
                }
        
        
        
        }
       
        


        
        
    return "no se pudo modificar el profesor: "+ nuevoProfesor.verApellidos()+ ","+ nuevoProfesor.verNombres();
    }

    @Override
    public String nuevoTrabajo(String titulo, int duracion, LocalDate fechaPresentacion, LocalDate fechaAprobacion, List<Area> areas, List<RolEnTrabajo> profesores, List<AlumnoEnTrabajo> aet) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hayTrabajosConEsteArea(Area area) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hayTrabajosConEsteProfesor(Profesor profesor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean hayTrabajosConEsteAlumno(Alumno alumno) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int verUltimoTrabajo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
}
    
    


