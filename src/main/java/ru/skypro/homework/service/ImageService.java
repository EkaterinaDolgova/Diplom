package ru.skypro.homework.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.ImageUtility;
import ru.skypro.homework.entities.Advert;
import ru.skypro.homework.entities.Image;
import ru.skypro.homework.exception.AdsNotFoundException;
import ru.skypro.homework.repository.AdsRepository;
import ru.skypro.homework.repository.ImageRepository;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import static com.datical.liquibase.ext.init.InitProjectUtil.getExtension;


@Service
public class ImageService {
    Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final ImageRepository imageRepository;
    private final AdsRepository adsRepository;

    public ImageService(ImageRepository imageRepository, AdsRepository adsRepository) {
        this.imageRepository = imageRepository;
        this.adsRepository = adsRepository;
    }
    /**
     * Загружаем картинки.
     * @return загрузка картинок.
     */

    public String uploadImage(Advert advert, MultipartFile file) {
            byte[] imageContent;
            try {
                imageContent = getImageContent(file);
            } catch (IOException e) {
                throw new AdsNotFoundException("Failed to extract image contents");
            }
            Image image = new Image();
            image.setAdvert(advert);
            image.setImage(imageContent);

            return imageRepository.save(image).getId().toString();
        }

        /**
         * Возвращает содержимое изображения.
         * @return содержимое изображения.
         */
        private byte[] getImageContent(MultipartFile file) throws IOException {
            String contentType = file.getContentType();
            String fileNameOriginal = file.getOriginalFilename();
            String ext = getExtension(fileNameOriginal);

            byte[] imageByte = file.getBytes();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ByteArrayInputStream bais = new ByteArrayInputStream(imageByte);

            BufferedImage imgIn = ImageIO.read(bais);
            if (imgIn == null) {
                return null;
            }

            double height = imgIn.getHeight() / (imgIn.getWidth() / 250d);
            BufferedImage imgOut = new BufferedImage(250, (int) height, imgIn.getType());
            Graphics2D graphics = imgOut.createGraphics();
            graphics.drawImage((BufferedImage) imgIn, 0, 0, 250, (int) height, null);
            graphics.dispose();

            ImageIO.write(imgOut, ext, baos);

            return baos.toByteArray();
        }

    /**
     * Создает новое изображение для объявления.
     *
     */
    public String createImage(Advert advert, MultipartFile file) {
        byte[] imageContent;
        try {
            imageContent = ImageUtility.getImageContent(file);
        } catch (IOException e) {
            throw new AdsNotFoundException("Failed to extract image contents");
        }

        Image image = imageRepository.findById(advert.getId()).get();
        image.setImage(imageContent);

        return imageRepository.save(image).getId().toString();
    }


}
