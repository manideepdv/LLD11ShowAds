package com.example.ecom.repositories;

import com.example.ecom.models.Advertisement;
import com.example.ecom.models.Preference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdvertisementRepository extends JpaRepository<Advertisement, Integer> {
    Optional<Advertisement> findByPreference(Preference preference);

    List<Advertisement> findAll();
}
