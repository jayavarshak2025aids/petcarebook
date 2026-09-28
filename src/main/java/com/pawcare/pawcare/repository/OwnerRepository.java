package com.pawcare.pawcare.repository;

import com.pawcare.pawcare.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
}