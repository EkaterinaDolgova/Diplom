package ru.skypro.homework.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

/**
 * Класс Пользователей
 */
@Entity
@Getter
@Setter
@Table(name = "users")
@AllArgsConstructor
public class Users {
    @Id
    @Column(columnDefinition = "bigserial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    private String role;
    private String username;
    private String password;
    private Boolean enabled;
    @OneToMany
    private Collection<Advert> advert;

    public Users() {
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}

