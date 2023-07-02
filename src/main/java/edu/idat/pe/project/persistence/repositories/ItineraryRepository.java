package edu.idat.pe.project.persistence.repositories;

import edu.idat.pe.project.persistence.entities.ItineraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItineraryRepository extends JpaRepository<ItineraryEntity, Long> {
    List<ItineraryEntity> findAllByDeletedFalse();

}
