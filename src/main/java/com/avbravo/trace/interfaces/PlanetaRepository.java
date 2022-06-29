/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.interfaces;

import com.avbravo.jmoordb.core.annotation.Mandatory;
import com.avbravo.jmoordb.core.annotation.Repository;

/**
 *
 * @author avbravo
 */

@Repository(entity = Planeta.class, jakarta = false)
public interface PlanetaRepository {
       @Mandatory
    String getFirstName();
   // @Query("select * from Planeta");
//    public List<Planeta> findAll();
    
}
