package ru.skypro.homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Objects;

/**
 * Класс Отзыв
 */
@Entity
@Getter
@Setter
@Table(name = "comment")
@AllArgsConstructor
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
    @JsonIgnore
    private Advert advert;

    public Comment() {
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
