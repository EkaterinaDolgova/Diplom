package ru.skypro.homework.entities;

import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "image")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Advert getAdvert() {
        return advert;
    }

    public void setAdvert(Advert ads) {
        this.advert = advert;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "AdsImageEntity{" +
                "id=" + id +
                ", ads=" + advert +
                ", image=" + Arrays.toString(image) +
                '}';
    }

}