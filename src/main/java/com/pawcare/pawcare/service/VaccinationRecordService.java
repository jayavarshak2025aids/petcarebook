package com.pawcare.pawcare.service;

import com.pawcare.pawcare.entity.Pet;
import com.pawcare.pawcare.entity.VaccinationRecord;
import com.pawcare.pawcare.entity.VaccineType;
import com.pawcare.pawcare.repository.PetRepository;
import com.pawcare.pawcare.repository.VaccinationRecordRepository;
import com.pawcare.pawcare.repository.VaccineTypeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VaccinationRecordService {

    private final VaccinationRecordRepository vaccinationRecordRepository;
    private final PetRepository petRepository;
    private final VaccineTypeRepository vaccineTypeRepository;

    public VaccinationRecordService(
            VaccinationRecordRepository vaccinationRecordRepository,
            PetRepository petRepository,
            VaccineTypeRepository vaccineTypeRepository) {

        this.vaccinationRecordRepository = vaccinationRecordRepository;
        this.petRepository = petRepository;
        this.vaccineTypeRepository = vaccineTypeRepository;
    }

    public VaccinationRecord saveVaccination(VaccinationRecord record) {

        if (record.getGivenDate() == null) {
            throw new RuntimeException("Given date is required");
        }

        if (record.getGivenDate().isAfter(LocalDate.now())) {
            throw new RuntimeException("Vaccination date cannot be in the future");
        }

        if (record.getPet() == null || record.getPet().getId() == null) {
            throw new RuntimeException("Pet is required");
        }

        if (record.getVaccineType() == null
                || record.getVaccineType().getId() == null) {
            throw new RuntimeException("Vaccine type is required");
        }

        Pet pet = petRepository.findById(record.getPet().getId())
                .orElseThrow(() -> new RuntimeException("Pet not found"));

        VaccineType vaccineType = vaccineTypeRepository
                .findById(record.getVaccineType().getId())
                .orElseThrow(() -> new RuntimeException("Vaccine type not found"));

        record.setPet(pet);
        record.setVaccineType(vaccineType);

        LocalDate nextDueDate = record.getGivenDate()
                .plusDays(vaccineType.getIntervalDays());

        record.setNextDueDate(nextDueDate);

        return vaccinationRecordRepository.save(record);
    }

    public List<VaccinationRecord> getAllVaccinations() {
        return vaccinationRecordRepository.findAll();
    }

    public List<VaccinationRecord> getVaccinationsByPetId(Long petId) {
        return vaccinationRecordRepository.findByPetId(petId);
    }

    public VaccinationRecord getVaccinationById(Long id) {
        return vaccinationRecordRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Vaccination record not found"));
    }

    public void deleteVaccination(Long id) {
        vaccinationRecordRepository.deleteById(id);
    }

    public List<VaccinationRecord> getDueVaccinations() {

        LocalDate today = LocalDate.now();
        LocalDate sevenDaysLater = today.plusDays(7);

        return vaccinationRecordRepository.findAll()
                .stream()
                .filter(record ->
                        record.getNextDueDate() != null
                                && !record.getNextDueDate().isBefore(today)
                                && !record.getNextDueDate().isAfter(sevenDaysLater)
                )
                .toList();
    }
}