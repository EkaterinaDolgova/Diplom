package ru.skypro.homework.dto;

import ru.skypro.homework.entities.User;

import java.util.List;

public class ResponseWrapperUserDto {
    private Integer count;
    private List<User> results;

    public ResponseWrapperUserDto(Integer count, List<User> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public List<User> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ResponseWrapperUser{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
