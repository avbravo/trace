/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.interfaces;

import com.avbravo.jmoordb.core.annotation.Mandatory;
import com.avbravo.jmoordb.core.annotation.Repository;
import com.avbravo.jmoordb.core.annotation.RepositoryBasic;
import com.avbravo.trace.model.Person;
import java.util.List;

/**
 *
 * @author avbravo
 */
@Repository(entity = Person.class,jakarta = false)
public interface PersonRepository {  
      @Mandatory
    String getFirstName();
//public List<Person> findAll();

    //public List<Country> findAll();
//    public Optional<Country> findById(String id);
//    public List<Country> findByCountry(String contry);
//    public Country save(Country country);
//    public void deleteById(String id);
}
