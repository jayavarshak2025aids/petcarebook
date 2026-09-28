package com.pawcare.pawcare.service;

import com.pawcare.pawcare.entity.VaccineType;
import com.pawcare.pawcare.repository.VaccineTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineTypeService {

    private final VaccineTypeRepository vaccineTypeRepository;

    public VaccineTypeService(VaccineTypeRepository vaccineTypeRepository) {
        this.vaccineTypeRepository = vaccineTypeRepository;
    }

    public VaccineType saveVaccineType(VaccineType vaccineType) {
        return vaccineTypeRepository.save(vaccineType);
    }

    public List<VaccineType> getAllVaccineTypes() {
        return vaccineTypeRepository.findAll();
    }

    public VaccineType getVaccineTypeById(Long id) {
        return vaccineTypeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vaccine type not found"));
    }

    public void deleteVaccineType(Long id) {
        vaccineTypeRepository.deleteById(id);
    }
}