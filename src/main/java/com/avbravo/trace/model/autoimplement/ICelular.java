/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.avbravo.trace.model.autoimplement;

import com.avbravo.jmoordb.core.annotation.AutoImplement;
import com.avbravo.jmoordb.core.annotation.Mandatory;
import java.time.LocalDate;

/**
 *
 * @author avbravo
 */
@AutoImplement(as = "Celular", builder = true) 
public interface ICelular {
     @Mandatory
    String getFirstName();

    @Mandatory
    String getLastName();

    LocalDate getDateOfBirth();

    String getPlaceOfBirth();

    String getPhone();

    String getAddress();
}
