/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.repository;

import com.avbravo.trace.model.automovilismo.Auto;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface AutoRepository {
    public List<Auto> findAll();
    public Optional<Auto> findById(String id);
    public List<Auto> findByAuto(String contry);
    public Auto save(Auto country);
    public void deleteById(String id);
}
