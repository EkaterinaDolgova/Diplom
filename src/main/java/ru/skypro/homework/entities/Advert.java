package ru.skypro.homework.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Класс Объявление
 */
@Entity
@Getter
@Setter
@Table(name = "advert")
@AllArgsConstructor
@DynamicInsert
public class Advert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "users")
    @JsonIgnore
    private Users users;

  //  @JsonIgnore
   // @OneToMany(mappedBy = "advert", fetch = FetchType.EAGER)

    @OneToMany(mappedBy = "advert", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Image> images;

    private Integer price;
    private String title;
    @OneToMany
    @JoinColumn(name = "comment_id")
    @JsonIgnore
    private Collection<Comment> comment;

    public Advert() {

    }

    @Override
    public String toString() {
        return "Advert{" +
                "id=" + id +
                ", Цена=" + price +
                ", Наименование='" + title + '\'' +
                '}';
    }

    public Image getLastImage() {
        return ((images == null) || (images.size()) == 0) ? null : images.get(images.size() - 1);
    }


}
