/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.repository;

import com.avbravo.trace.model.Country;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface CountryRepository {
    public List<Country> findAll();
    public Optional<Country> findById(String id);
    public List<Country> findByCountry(String contry);
    public Country save(Country country);
    public void deleteById(String id);
}
