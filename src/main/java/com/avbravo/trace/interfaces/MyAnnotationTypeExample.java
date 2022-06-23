/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.interfaces;


import com.avbravo.jmoordb.core.annotation.Mandatory;
import com.avbravo.jmoordb.core.annotation.app.MyAnnotationType;
import com.avbravo.trace.model.Person;
import java.time.LocalDate;

/**
 *
 * @author avbravo
 */
@MyAnnotationType(entity=Person.class)
public interface MyAnnotationTypeExample {
        @Mandatory
    String getFirstName();

    @Mandatory
    String getLastName();

    LocalDate getDateOfBirth();

    String getPlaceOfBirth();
}
