package ru.skypro.homework.dto;

import lombok.Data;

import java.util.Objects;
@Data
public class FullAdsDto {

    private String authorFirstName;
    private String authorLastName;
    private String description;
    private String email;
    private String phone;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;

    public FullAdsDto(){}

    public FullAdsDto(String authorFirstName, String authorLastName, String description, String email,
                      String phone, String image, int pk, int price, String title) {
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.description = description;
        this.email = email;
        this.phone = phone;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getDescription() {
        return description;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getImage() {
        return image;
    }

    public Integer getPk() {
        return pk;
    }

    public Integer getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
      public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FullAdsDto)) return false;
        FullAdsDto fullAds = (FullAdsDto) o;
        return  Objects.equals(authorFirstName, fullAds.authorFirstName) && Objects.equals(authorLastName, fullAds.authorLastName) && Objects.equals(description, fullAds.description) && Objects.equals(email, fullAds.email) && Objects.equals(phone, fullAds.phone) && Objects.equals(image, fullAds.image) && Objects.equals(pk, fullAds.pk) && Objects.equals(price, fullAds.price) && Objects.equals(title, fullAds.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash( authorFirstName, authorLastName, description, email, phone, image, pk, price, title);
    }

    @Override
    public String toString() {
        return "FullAds{" +
                ", authorFirstName='" + authorFirstName + '\'' +
                ", authorLastName='" + authorLastName + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                ", pk=" + pk +
                ", price=" + price +
                ", title='" + title + '\'' +
                '}';
    }
}
