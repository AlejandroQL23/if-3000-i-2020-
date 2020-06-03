package edu.ucr.rp.appmanejodeinventarios.Logic;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class Object {
    
     private String name;// .catalogo
    private int propertiesQuantity;      // propertiesQuantity del arrayList
    private List<TextField> properties;  // guarda las properties
    private List<Label> attribute;


    public Object(String name, int propertiesQuantity, ArrayList<TextField> properties, ArrayList<Label> attribute) {
        this.name = name;
        this.propertiesQuantity = propertiesQuantity;
        this.properties = properties;
        this.attribute = attribute;
    }//end constructor

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPropertiesQuantity() {
        return propertiesQuantity;
    }

    public void setPropertiesQuantity(int propertiesQuantity) {
        this.propertiesQuantity = propertiesQuantity;
    }

    public List<TextField> getProperties() {
        return properties;
    }

    public void setPropiedades(ArrayList<TextField> propiedades) {
        this.properties = propiedades;
    }

    public List<Label> getAttribute() {
        return attribute;
    }

    public void setAtributo(ArrayList<Label> atributo) {
        this.attribute = atributo;
    }
    
}//end Object
