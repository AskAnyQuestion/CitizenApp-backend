package com.example.citizen.repository;

import com.example.citizen.model.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncidentRepository extends JpaRepository <Incident, Long> {
    /* Сохранение инцидента */
    @Override
    @SuppressWarnings("unchecked")
    Incident save(Incident user);
    /* Получить все инциденты */
    @Override
    List<Incident> findAll();

}
