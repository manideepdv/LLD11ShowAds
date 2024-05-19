package com.example.ecom.repositories;

import com.example.ecom.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PreferencesRepository extends JpaRepository<Preference, Integer> {
    @Override
    Optional<Preference> findById(Integer integer);
}
