package com.pawcare.pawcare.repository;

import com.pawcare.pawcare.entity.VaccineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VaccineTypeRepository extends JpaRepository<VaccineType, Long> {
}