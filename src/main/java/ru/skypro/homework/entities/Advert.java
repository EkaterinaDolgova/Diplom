package ru.skypro.homework.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "advert")
/**Класс Объявление*/
public class Advert {


    public enum authenticated {
        TRUE,
        FALSE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   // @Column(name = "id", nullable = false)
    private Long id;
//    @Column(name = "users")
    private Integer users;
  //  @Column(name = "image", length = 200)
    private String image;
  //  @Column(name = "price")
    private Integer price;
 //   @Column(name = "title", length = 200)
    private String title;

    public Advert(Integer users, String image, Integer price, String title) {
        this.users = users;
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

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
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
        return Objects.equals(id, advert.id) && Objects.equals(users, advert.users) && Objects.equals(image, advert.image) && Objects.equals(price, advert.price) && Objects.equals(title, advert.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, image, price, title);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", Пользователь=" + users +
                ", Картинка='" + image + '\'' +
                ", Цена=" + price +
                ", Наименование='" + title + '\'' +
                '}';
    }
}
