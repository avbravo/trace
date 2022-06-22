/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.interfaces;

import com.avbravo.jmoordb.core.annotation.Mandatory;
import com.avbravo.jmoordb.core.annotation.Repository;
import java.time.LocalDate;

/**
 *
 * @author avbravo
 */
@Repository(entity = "Province", jakarta = false)
public interface ProvinceRepository {
   @Mandatory
    String getFirstName();

    @Mandatory
    String getLastName();

    LocalDate getDateOfBirth();

    String getPlaceOfBirth();

    String getPhone();

    String getAddress();
}
