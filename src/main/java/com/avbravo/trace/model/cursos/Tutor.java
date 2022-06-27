/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.model.cursos;

import com.avbravo.jmoordb.core.annotation.Id;

/**
 *
 * @author avbravo
 */
public class Tutor {
    @Id
    private String idtutor;
    private String tutor;

    public Tutor() {
    }

    public String getIdtutor() {
        return idtutor;
    }

    public void setIdtutor(String idtutor) {
        this.idtutor = idtutor;
    }

    public String getTutor() {
        return tutor;
    }

    public void setTutor(String tutor) {
        this.tutor = tutor;
    }
    
    
}
