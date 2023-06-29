package edu.idat.pe.project.service.impl;

import edu.idat.pe.project.dto.request.ItineraryRequest;
import edu.idat.pe.project.persistence.entities.ItineraryEntity;
import edu.idat.pe.project.persistence.entities.LocationEntity;
import edu.idat.pe.project.persistence.entities.OriginEntity;
import edu.idat.pe.project.persistence.repositories.ItineraryRepository;
import edu.idat.pe.project.persistence.repositories.LocationRepository;
import edu.idat.pe.project.persistence.repositories.OriginRepository;
import edu.idat.pe.project.reports.imports.HelperImportExcel;
import edu.idat.pe.project.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItineraryServiceImpl implements ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final LocationRepository locationRepository;
    private final OriginRepository originRepository;

    @Transactional
    @Override
    public void save(MultipartFile file) {
        try {
            List<ItineraryRequest> itineraries = HelperImportExcel.convertExcelToListOfItinerary(file.getInputStream());

            List<ItineraryEntity> itineraryEntities = new ArrayList<>();

            for (ItineraryRequest itineraryRequest : itineraries) {
                ItineraryEntity itineraryEntity = new ItineraryEntity();
                itineraryEntity.setDepartureDate(itineraryRequest.getDepartureDate());
                itineraryEntity.setArrivalDate(itineraryRequest.getArrivalDate());
                itineraryEntity.setHour(itineraryRequest.getHour());

                // Obtener el objeto LocationEntity correspondiente al locationId
                Optional<LocationEntity> locationEntityOptional = locationRepository.findById(itineraryRequest.getLocationId());
                if (locationEntityOptional.isEmpty()) {
                    // Manejar la situación si no se encuentra la ubicación por el ID
                    throw new IllegalArgumentException("LocationEntity not found for id: " + itineraryRequest.getLocationId());
                }
                itineraryEntity.setLocation(locationEntityOptional.get());

                // Obtener el objeto OriginEntity correspondiente al originId
                Optional<OriginEntity> originEntityOptional = originRepository.findById(itineraryRequest.getOriginId());
                if (originEntityOptional.isEmpty()) {
                    // Manejar la situación si no se encuentra el origen por el ID
                    throw new IllegalArgumentException("OriginEntity not found for id: " + itineraryRequest.getOriginId());
                }
                itineraryEntity.setOrigin(originEntityOptional.get());

                itineraryEntities.add(itineraryEntity);
            }

            itineraryRepository.saveAll(itineraryEntities);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }




}