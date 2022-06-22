/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.interfaces;

import com.avbravo.jmoordb.core.annotation.Mandatory;
import com.avbravo.jmoordb.core.annotation.Repository;
import com.avbravo.trace.model.Province;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Repository(entity = Province.class, jakarta = false)
public interface ProvinceRepository {
   @Mandatory
    String getFirstName();

    @Mandatory
    String getLastName();

    LocalDate getDateOfBirth();

    String getPlaceOfBirth();

        public List<Province> findAll();
}
