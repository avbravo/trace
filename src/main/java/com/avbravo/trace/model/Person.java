/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.model;

import com.avbravo.jmoordb.core.annotation.JsonObject;

/**
 *
 * @author avbravo
 */
@JsonObject
public class Person {

    private String username;
    private String email;
    private Integer edad;

    public Person() {
    }

    
    
    public Person(String username, String email, Integer edad) {
        this.username = username;
        this.email = email;
        this.edad = edad;
    }

 

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    
    
}
