package ru.skypro.homework.dto;

import lombok.Data;

@Data
public class RegisterReq {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phone;
    private Role role;

    public RegisterReq(String password, String firstName, String lastName, String phone, Role role) {
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    @Override
    public String toString() {
        return "RegisterReq{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
