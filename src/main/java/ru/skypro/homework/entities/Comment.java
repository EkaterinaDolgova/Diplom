package ru.skypro.homework.entities;
import  javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;


/**
 * Класс Отзыв
 */

@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "users")
    private Integer users;
    @Column(name = "createdat")
    private OffsetDateTime createdAt;
    @Column(name = "text")
    private String text;

    @ManyToOne
    @JoinColumn(name = "advert_id")
    private Advert advert;

    public void setAdvert(Advert advert) {
        this.advert = advert;
    }

    public Advert getAdvert(){
        return this.advert;
    }

    public Comment(Long id, Integer users, OffsetDateTime createdAt, String text) {
        this.id = id;
        this.users = users;
        this.createdAt = createdAt;
        this.text = text;
    }

    public Comment() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUsers() {
        return users;
    }

    public void setUsers(Integer users) {
        this.users = users;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(id, comment.id) && Objects.equals(users, comment.users) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, users, createdAt, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Пользователь=" + users +
                ", Дата и время=" + createdAt +
                ", Отзыв='" + text + '\'' +
                '}';
    }
}
