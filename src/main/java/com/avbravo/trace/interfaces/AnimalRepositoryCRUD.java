/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.interfaces;

import com.avbravo.jmoordb.core.annotation.RepositoryCRUD;
import com.avbravo.trace.model.Animal;
import java.util.List;

/**
 *
 * @author avbravo
 */
@RepositoryCRUD(entity = Animal.class)
public interface AnimalRepositoryCRUD {
//    public List<Animal> findAll();
}
