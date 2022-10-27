package ru.skypro.homework.dto;

import javax.persistence.*;

@Entity
/**Класс Создание Объявление */
public class CreateAds {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String description;
    private String image;
    private Integer pk;
    private Integer price;
    private String title;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CreateAds(String description, String image, Integer pk, Integer price, String title) {
        this.description = description;
        this.image = image;
        this.pk = pk;
        this.price = price;
        this.title = title;
    }

    public CreateAds() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public String toString() {
        return "CreateAds{" +
                "description=" + description +
                ", image='" + image + '\'' +
                ", pk='" + pk + '\'' +
                ", price='" + price + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
