package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Ads;
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

    public String addAds() {
        logger.info("Info addAds");
        return "OK";

    }

    public <object> String getAdsMe(Ads.authenticated authenticated, String authority, object credentials, object details, object principal) {
        logger.info("Info getAdsMe");
        return "OK";

    }
    public String getAdsComments(String ad_pk) {
        logger.info("Info getAdsComments");
        return "OK";
    }

    public String addAdsComments(String ad_pk) {
        logger.info("Info addAdsComments");
        return "OK";
    }

    public String deleteAdsComment(String ad_pk, Integer id) {
        logger.info("Info deleteAdsComment");
        return "OK";
    }

    public String getAdsComment(String ad_pk, Integer id) {
        logger.info("Info getAdsComment");
        return "OK";
    }

    public String updateAdsComment(String ad_pk, Integer id) {
        logger.info("Info updateAdsComment");
        return "OK";
    }

    public String removeAds(Integer id) {
        logger.info("Info removeAds");
        return "OK";
    }

    public String getAds(Integer id) {
        logger.info("Info getAds");
        return "OK";
    }

    public String updateAds(Integer id) {
        logger.info("Info updateAds");
        return "OK";
    }
}
