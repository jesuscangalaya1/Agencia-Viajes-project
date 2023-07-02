package edu.idat.pe.project.service;

import edu.idat.pe.project.dto.response.ItineraryResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItineraryService {

    void save(MultipartFile file);

    List<ItineraryResponse> listItineraries();
}
