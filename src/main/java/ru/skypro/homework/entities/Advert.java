package ru.skypro.homework.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
/**Класс Объявление*/
public class Advert {

   /* public enum authenticated {
        TRUE,
        FALSE
    }*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer user;
    private String image;
    private Integer price;
    private String title;

    public Advert(Integer user, String image, Integer price, String title) {
        this.user = user;
        this.image = image;
        this.price = price;
        this.title = title;
    }
    public Advert() {

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advert advert = (Advert) o;
        return Objects.equals(id, advert.id) && Objects.equals(user, advert.user) && Objects.equals(image, advert.image) && Objects.equals(price, advert.price) && Objects.equals(title, advert.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, image, price, title);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", Пользователь=" + user +
                ", Картинка='" + image + '\'' +
                ", Цена=" + price +
                ", Наименование='" + title + '\'' +
                '}';
    }
}
