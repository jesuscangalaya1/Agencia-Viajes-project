package edu.idat.pe.project.service;

import org.springframework.web.multipart.MultipartFile;

public interface ItineraryService {

    void save(MultipartFile file);
}
