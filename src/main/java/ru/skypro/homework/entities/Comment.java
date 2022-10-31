package ru.skypro.homework.entities;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

@Entity
/**Класс Отзыв*/
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private Integer user;
    private OffsetDateTime createdAt;
    private String text;

    public Comment(Long id, Integer user, OffsetDateTime createdAt, String text) {
        this.id = id;
        this.user = user;
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

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
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
        return Objects.equals(id, comment.id) && Objects.equals(user, comment.user) && Objects.equals(createdAt, comment.createdAt) && Objects.equals(text, comment.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, createdAt, text);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", Пользователь=" + user +
                ", Дата и время=" + createdAt +
                ", Отзыв='" + text + '\'' +
                '}';
    }
}
