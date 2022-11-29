package ru.skypro.homework.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Getter
@Setter
@Table(name = "image")
@AllArgsConstructor

@DynamicInsert
public class Image {
    @Id
    @Column(columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_ads")
    private Advert advert;

    private byte[] image;

    public Image() {
    }

    @Override
    public String toString() {
        return "Image{" +
                "id=" + id +
                ", ads=" + advert +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}