package ru.skypro.homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

/**
 * Класс Объявление
 */
@Entity
@Getter
@Setter
@Table(name = "advert")
//@AllArgsConstructor
public class Advert {
/*
    public enum authenticated {
        TRUE,
        FALSE
    }
*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer users;
    @ManyToOne
    @JoinColumn(name = "usersCollection_id")
    @JsonIgnore
    private Users usersCollection;

    @OneToOne
    @JoinColumn(name = "image_id")
    @JsonIgnore
    private Image imageI;
//    private String image;
    private Integer price;
    private String title;

    @OneToMany
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Collection<Comment> comment;

    public Advert(Long id, Integer users, Users usersCollection, /*String image,*/ Integer price, String title, Collection<Comment> comment) {
        this.id = id;
        this.users = users;
        this.usersCollection = usersCollection;
//        this.image = image;
        this.price = price;
        this.title = title;
        this.comment = comment;
    }
    public Collection<Comment> getComment() {
        return comment;
    }


    public void setUsers(Integer users) {
        this.users = users;
    }

    public Integer getUsers() {
        return users;
    }

    public void setComment(Collection<Comment> comment) {
        this.comment = comment;
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

    public Users getUsersCollection() {
        return usersCollection;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

/*
    public String getImage() {
        return image;
    }
*/

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
        return Objects.equals(id, advert.id) && Objects.equals(users, advert.users) /*&& Objects.equals(image, advert.image)*/ && Objects.equals(price, advert.price) && Objects.equals(title, advert.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, /*image,*/ price, title);
    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", Пользователь=" + users +
/*                ", Картинка='" + image + '\'' +*/
                ", Цена=" + price +
                ", Наименование='" + title + '\'' +
                '}';
    }


}
