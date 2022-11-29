package ru.skypro.homework.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
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
import java.util.Objects;

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


    /**
     * UserDto
     */
    @Validated
    @javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")


    public static class UserDto {
        @JsonProperty("email")
        private String email = null;

        @JsonProperty("firstName")
        private String firstName = null;

        @JsonProperty("id")
        private Long id = null;

        @JsonProperty("lastName")
        private String lastName = null;

        @JsonProperty("phone")
        private String phone = null;

        @JsonProperty("role")
        private String role= null;

        @JsonProperty("username")
        private String username= null;

        @JsonProperty("password")
        private String password= null;

        @JsonProperty("enabled")
        private Boolean enabled= null;

        public UserDto email(String email) {
            this.email = email;
            return this;
        }

        /**
         * Get email
         *
         * @return email
         **/
        @Schema(description = "")

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public UserDto firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        /**
         * Get firstName
         *
         * @return firstName
         **/
        @Schema(description = "")

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public UserDto id(Long id) {
            this.id = id;
            return this;
        }

        /**
         * Get id
         *
         * @return id
         **/
        @Schema(description = "")

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public UserDto lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        /**
         * Get lastName
         *
         * @return lastName
         **/
        @Schema(description = "")

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public UserDto phone(String phone) {
            this.phone = phone;
            return this;
        }

        /**
         * Get phone
         *
         * @return phone
         **/
        @Schema(description = "")

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            UserDto user = (UserDto) o;
            return Objects.equals(this.email, user.email) &&
                    Objects.equals(this.firstName, user.firstName) &&
                    Objects.equals(this.id, user.id) &&
                    Objects.equals(this.lastName, user.lastName) &&
                    Objects.equals(this.phone, user.phone);
        }
        /**
         * Get username
         *
         * @return username
         **/
        @Schema(description = "")

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        /**
         * Get role
         *
         * @return role
         **/
        @Schema(description = "")

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        /**
         * Get password
         *
         * @return password
         **/
        @Schema(description = "")

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        /**
         * Get enabled
         *
         * @return enabled
         **/
        @Schema(description = "")

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
        @Override
        public int hashCode() {
            return Objects.hash(email, firstName, id, lastName, phone);
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("class User {\n");
            sb.append("    email: ").append(toIndentedString(email)).append("\n");
            sb.append("    firstName: ").append(toIndentedString(firstName)).append("\n");
            sb.append("    id: ").append(toIndentedString(id)).append("\n");
            sb.append("    lastName: ").append(toIndentedString(lastName)).append("\n");
            sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
            sb.append("}");
            return sb.toString();
        }

        /**
         * Convert the given object to string with each line indented by 4 spaces
         * (except the first line).
         */
        private String toIndentedString(Object o) {
            if (o == null) {
                return "null";
            }
            return o.toString().replace("\n", "\n    ");
        }
    }
}
