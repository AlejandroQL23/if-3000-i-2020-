
package com.leoc.sockets.multithread.server;

import java.util.List;


public class Mercancia {
    
    
    private String name;// .catalogo
    private int propertiesQuantity;      // propertiesQuantity del arrayList
    private List<String> properties;  // guarda las properties
    private List<String> attribute;

    public Mercancia(String name, int propertiesQuantity, List<String> properties, List<String> attribute) {
        this.name = name;
        this.propertiesQuantity = propertiesQuantity;
        this.properties = properties;
        this.attribute = attribute;
    }

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

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    public List<String> getAttribute() {
        return attribute;
    }

    public void setAttribute(List<String> attribute) {
        this.attribute = attribute;
    }
    
}
