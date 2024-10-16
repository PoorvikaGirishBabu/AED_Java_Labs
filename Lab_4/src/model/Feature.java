/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author archil
 */
public class Feature {
    private Product owner;
    private String name;
    private Object Value;
    
    public Feature(Product owner)
    {
        this.owner = owner;
    }
    
    public Feature(Product owner , String name, Object Value)
    {
        this.name = name;
        this.Value= Value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return Value;
    }

    public void setValue(Object Value) {
        this.Value = Value;
    }

    @Override
    public String toString()
    {
        return name;
    }
}


