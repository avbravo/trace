/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.model.automovilismo;

import com.avbravo.jmoordb.core.annotation.Column;
import com.avbravo.jmoordb.core.annotation.Entity;
import com.avbravo.jmoordb.core.annotation.Id;

/**
 *
 * @author avbravo
 */
@Entity
public class Auto {
    @Id
    @Column
    private String idauto;
    @Column
    private String marca;

    public Auto() {
    }

    public String getIdauto() {
        return idauto;
    }

    public void setIdauto(String idauto) {
        this.idauto = idauto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
}
