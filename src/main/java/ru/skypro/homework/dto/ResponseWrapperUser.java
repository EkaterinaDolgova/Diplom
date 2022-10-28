package ru.skypro.homework.dto;

import java.util.List;

public class ResponseWrapperUser {
    private Integer count;
    private List<User> results;

    public ResponseWrapperUser(Integer count, List<User> results) {
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
