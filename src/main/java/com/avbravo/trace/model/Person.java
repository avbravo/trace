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

    public Person(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
