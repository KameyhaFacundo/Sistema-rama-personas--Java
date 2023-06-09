/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.personas.modelos.Profesor;
import java.time.LocalDate;
import java.util.Objects;


public class RolEnTrabajo {
    
    private Profesor profesor;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private String razon;
    private Rol rol;

    public RolEnTrabajo(LocalDate fechaDesde,Profesor profesor, Rol rol) {
        
        this.fechaDesde = fechaDesde;
        this.profesor = profesor;
        this.rol = rol;
    }
    
    
    public RolEnTrabajo(Profesor profesor) {
        this.profesor = profesor;
    }
    
    public RolEnTrabajo(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    

    public LocalDate getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(LocalDate fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public LocalDate getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(LocalDate fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public String getRazon() {
        return razon;
    }

    public void setRazon(String razon) {
        this.razon = razon;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.profesor);
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
        final RolEnTrabajo other = (RolEnTrabajo) obj;
        if (!Objects.equals(this.profesor, other.profesor)) {
            return false;
        }
        return true;
    }
    
    
    
}
