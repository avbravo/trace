/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.avbravo.trace.repository;

import com.avbravo.trace.model.cursos.Tutor;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author avbravo
 */
public interface TutorRepository {
    public List<Tutor> findAll();
    public Optional<Tutor> findById(String id);
    public List<Tutor> findByTutor(String tutor);
    public Tutor save(Tutor tutor);
    public void deleteById(String id);
}
