/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.repository;

import com.avbravo.trace.model.automovilismo.Conductor;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface ConductorRepository {
    public List<Conductor> findAll();
    public Optional<Conductor> findById(String id);
    public List<Conductor> findByConductor(String contry);
    public Conductor save(Conductor country);
    public void deleteById(String id);
}
