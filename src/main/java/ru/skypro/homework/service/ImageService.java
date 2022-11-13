package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;

@Service
public class ImageService {
    Logger logger = LoggerFactory.getLogger(ImageService.class);
    @Value("images")
    private String imagesDir;
    private final ImageRepository imageRepository;
    private final AdsRepository adsRepository;

    public ImageService(ImageRepository imageRepository, AdsRepository adsRepository) {
        this.imageRepository = imageRepository;
        this.adsRepository = adsRepository;
    }
    /**
     * Загружаем картинки.
     *
     * @return загрузка картинок.
     */
   public void uploadImage(Advert ads, MultipartFile file) throws Exception {
       logger.info("Вызван метод uploadImage");
       Path pathFile = Path.of(imagesDir, ads.getTitle() + "." + getExtension(file.getOriginalFilename()));
       Files.createDirectories(pathFile.getParent());
       Files.deleteIfExists(pathFile);

       try (InputStream is = file.getInputStream();
            OutputStream out = Files.newOutputStream(pathFile, CREATE_NEW);
            BufferedInputStream bis = new BufferedInputStream(is, 1024);
            BufferedOutputStream bout = new BufferedOutputStream(out, 1024)) {
           bis.transferTo(bout);
       }
       Image image = new Image();
       image.setAdvert(ads);
       image.setFilePath(pathFile.toString());
       image.setFileSize(file.getSize());
       image.setMediaType(file.getContentType());
       image.setData(file.getBytes());
       imageRepository.save(image);
    }
    /**
     * Обновляем картинки.
     *
     * @return загрузка новой картинки.
     */
    public void updateImage (Long advertId, MultipartFile imageFile) throws IOException {
        Advert advert = adsRepository.findById(advertId).orElseThrow(() -> new AdsNotFoundException("Объявление не найдено"));
        Path filePath = Path.of(imagesDir, advert.getId() + "." + getExtension(imageFile.getOriginalFilename()));
        Files.createDirectories(filePath.getParent());
        Files.deleteIfExists(filePath);
        try (
                InputStream is = imageFile.getInputStream();
                OutputStream os = Files.newOutputStream(filePath, CREATE_NEW);
                BufferedInputStream bis = new BufferedInputStream(is, 1024);
                BufferedOutputStream bos = new BufferedOutputStream(os, 1024);
        ) {
            bis.transferTo(bos);
        }
        Image image = findAdvertImage(advertId);
        image.setAdvert(advert);
        image.setFilePath(filePath.toString());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        adsRepository.save(advert);
        logger.info("Info updateImage");
    }
    public Image findAdvertImage(long advert_id) {
        logger.info("Info findAdvertImage");
        return imageRepository.findByAdvertId(advert_id).orElse(new Image());
    }


    private String getExtension(String fileName) {
        logger.info("Info getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


}
