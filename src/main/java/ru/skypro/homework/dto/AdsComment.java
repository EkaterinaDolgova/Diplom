package ru.skypro.homework.dto;

import javax.persistence.*;

@Entity
/**Класс Объявление Комментарий*/
public class AdsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer author;
    private String createdAt;
    private Integer pk;
    private String text;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AdsComment(Integer author, String createdAt, Integer pk, String text) {
        this.author = author;
        this.createdAt = createdAt;
        this.pk = pk;
        this.text = text;
    }

    public AdsComment() {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }
    @Override
    public String toString() {
        return "AdsComment{" +
                "author=" + author +
                ", createdAt='" + createdAt + '\'' +
                ", pk='" + pk + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
