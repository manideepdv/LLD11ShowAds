package com.example.ecom.services;

import com.example.ecom.exceptions.UserNotFoundException;
import com.example.ecom.models.Advertisement;
import com.example.ecom.models.Preference;
import com.example.ecom.models.User;
import com.example.ecom.repositories.AdvertisementRepository;
import com.example.ecom.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdsServiceImpl implements AdsService {
    private final AdvertisementRepository advertisementRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdsServiceImpl(UserRepository userRepository, AdvertisementRepository advertisementRepository) {
        this.userRepository = userRepository;
        this.advertisementRepository = advertisementRepository;
    }

    @Override
    public Advertisement getAdvertisementForUser(int userId) throws UserNotFoundException {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("User Not Found");
        }
        User user = optionalUser.get();
        List<Preference> preferences = user.getPreferences();
        if (preferences.isEmpty()) {
            List<Advertisement> advertisementList = advertisementRepository.findAll();
            if (advertisementList.isEmpty()) {
                return null;
            }
            return advertisementList.get(0);
        }
        for (Preference preference : preferences) {
            Optional<Advertisement> optionalAdvertisement = advertisementRepository.findByPreference(preference);
            if (optionalAdvertisement.isPresent()) {
                return optionalAdvertisement.get();
            }
        }
        return null;
    }
}
