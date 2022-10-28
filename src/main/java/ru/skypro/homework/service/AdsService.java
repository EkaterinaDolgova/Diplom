package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.repository.AdsRepository;

@Service
public class AdsService {
    Logger logger = LoggerFactory.getLogger(AdsService.class);

    private final AdsRepository adsRepository;

    public AdsService(AdsRepository adsRepository) {
        this.adsRepository = adsRepository;
    }
    public String getAllAds() {
        logger.info("Info getAllAds");
        return "OK";

    }
}
