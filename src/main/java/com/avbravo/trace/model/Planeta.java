/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.model;

import com.avbravo.jmoordb.core.annotation.Column;
import com.avbravo.jmoordb.core.annotation.Entity;
import com.avbravo.jmoordb.core.annotation.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author avbravo
 */

@Getter
@Setter
@Builder
@Entity
public class Planeta {
    @Id
    private String idplaneta;
    @Column
    private String planeta;

    public Planeta() {
    }
    
    
    
}
