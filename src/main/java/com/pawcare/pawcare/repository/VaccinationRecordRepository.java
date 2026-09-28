package com.pawcare.pawcare.repository;

import com.pawcare.pawcare.entity.VaccinationRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VaccinationRecordRepository
        extends JpaRepository<VaccinationRecord, Long> {

    List<VaccinationRecord> findByPetId(Long petId);
}