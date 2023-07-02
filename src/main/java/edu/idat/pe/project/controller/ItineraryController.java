package edu.idat.pe.project.controller;

import edu.idat.pe.project.dto.response.FlightResponse;
import edu.idat.pe.project.dto.response.ItineraryResponse;
import edu.idat.pe.project.service.ItineraryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/itineraries")
@CrossOrigin("*")
@Slf4j
public class ItineraryController {

    private final ItineraryService itineraryService;

    @PostMapping(value = "/import-excel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> createItinerary(@RequestParam MultipartFile file) {
        try {
            log.info("Received request to import Excel file." + file);

            // Process the Excel file and save data to the database
            itineraryService.save(file);


            return ResponseEntity.ok(Map.of("message", "Importado exitosamente in database"));
        } catch (Exception e) {
            log.error("Error occurred while importing Excel file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("message", "An error occurred while processing the file"));
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItineraryResponse>> listItineraries(){
        return new ResponseEntity<>(itineraryService.listItineraries(), HttpStatus.OK);
    }


}
