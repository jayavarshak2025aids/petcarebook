package com.pawcare.pawcare.repository;

import com.pawcare.pawcare.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}