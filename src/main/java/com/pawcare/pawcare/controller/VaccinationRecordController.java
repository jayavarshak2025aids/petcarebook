package com.pawcare.pawcare.controller;

import com.pawcare.pawcare.entity.VaccinationRecord;
import com.pawcare.pawcare.service.VaccinationRecordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccinations")
public class VaccinationRecordController {

    private final VaccinationRecordService vaccinationRecordService;

    public VaccinationRecordController(
            VaccinationRecordService vaccinationRecordService) {
        this.vaccinationRecordService = vaccinationRecordService;
    }

    @PostMapping
    public VaccinationRecord createVaccination(
            @RequestBody VaccinationRecord record) {
        return vaccinationRecordService.saveVaccination(record);
    }

    @GetMapping
    public List<VaccinationRecord> getAllVaccinations() {
        return vaccinationRecordService.getAllVaccinations();
    }

    @GetMapping("/{id}")
    public VaccinationRecord getVaccinationById(
            @PathVariable Long id) {
        return vaccinationRecordService.getVaccinationById(id);
    }

    @GetMapping("/due")
    public List<VaccinationRecord> getDueVaccinations() {
        return vaccinationRecordService.getDueVaccinations();
    }

    @GetMapping("/pet/{petId}")
    public List<VaccinationRecord> getVaccinationsByPetId(
            @PathVariable Long petId) {
        return vaccinationRecordService.getVaccinationsByPetId(petId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccination(
            @PathVariable Long id) {
        vaccinationRecordService.deleteVaccination(id);
        return ResponseEntity.ok("Vaccination record deleted successfully");
    }
}