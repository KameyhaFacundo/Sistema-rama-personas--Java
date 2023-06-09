/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.personas.modelos;

import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author
 */
public class ModeloComboCargo extends DefaultComboBoxModel{

    public ModeloComboCargo() {         
        for (Cargo unCargo : Cargo.values())
        //values(): devuelve un array con los valores de la enumeracion
        //addElement() añade una opcion al combo
            this.addElement(unCargo);        
    }

    /**
     * Devuelve el Cargo del Profesor seleccionado
     * @return Cargo  - tipo de Cargo seleccionado
     */
    public Cargo obtenerTipo() { 
        return (Cargo)this.getSelectedItem();
    }
        
    /**
     * Devuelve el Cargo del Profesor seleccionado
     * @param unTipo tipo de Cargo
     */
    public void seleccionarTipo(Cargo unTipo) {
        this.setSelectedItem(unTipo);
    }
    
}
