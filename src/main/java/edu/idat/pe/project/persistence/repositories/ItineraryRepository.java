package edu.idat.pe.project.persistence.repositories;

import edu.idat.pe.project.persistence.entities.ItineraryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<ItineraryEntity, Long> {
}
