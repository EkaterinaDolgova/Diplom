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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE_NEW;
import static io.swagger.v3.core.util.AnnotationsUtils.getExtensions;

@Service
public class ImageService {
    Logger logger = LoggerFactory.getLogger(ImageService.class);
   // @Value("${path.to.images.folder}")
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
   /* public void uploadImage(Long advert_id, MultipartFile imageFile) throws Exception {
        Advert advert = adsRepository.findById(advert_id).orElseThrow(() -> new AdsNotFoundException("Картинка не найдена"));
        Path filePath = Path.of(imagesDir, advert + "." + getExtensions(imageFile.getOriginalFilename()));
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
        Image image = findAdvertImage(advert_id);
        //avatar.setStudent(studentRepository.findById(studentId).orElseThrow(()->new StudentNotFoundException("Студент не найден")));
        image.setAdvert(advert);
        image.setFilePath(filePath.toString());
        image.setFileSize(imageFile.getSize());
        image.setMediaType(imageFile.getContentType());
        image.setData(imageFile.getBytes());
        adsRepository.save(advert);
        logger.info("Info uploadImage");
    }
    public Image findAdvertImage(long advert_id) {
        logger.info("Info findAdvertImage");
        return imageRepository.findByAdvertId(advert_id).orElse(new Image());
    }


    private String getExtensions(String fileName) {
        logger.info("Info getExtensions");
        return fileName.substring(fileName.lastIndexOf(".") + 1);

    }*/

}
