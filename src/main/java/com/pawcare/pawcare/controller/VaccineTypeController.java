package com.pawcare.pawcare.controller;

import com.pawcare.pawcare.entity.VaccineType;
import com.pawcare.pawcare.service.VaccineTypeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vaccine-types")
public class VaccineTypeController {

    private final VaccineTypeService vaccineTypeService;

    public VaccineTypeController(VaccineTypeService vaccineTypeService) {
        this.vaccineTypeService = vaccineTypeService;
    }

    @PostMapping
    public VaccineType createVaccineType(@RequestBody VaccineType vaccineType) {
        return vaccineTypeService.saveVaccineType(vaccineType);
    }

    @GetMapping
    public List<VaccineType> getAllVaccineTypes() {
        return vaccineTypeService.getAllVaccineTypes();
    }

    @GetMapping("/{id}")
    public VaccineType getVaccineTypeById(@PathVariable Long id) {
        return vaccineTypeService.getVaccineTypeById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVaccineType(@PathVariable Long id) {
        vaccineTypeService.deleteVaccineType(id);
        return ResponseEntity.ok("Vaccine type deleted successfully");
    }
}