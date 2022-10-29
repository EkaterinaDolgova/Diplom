package ru.skypro.homework.dto;

import java.util.List;

public class ResponseWrapperUserDto {
    private Integer count;
    private List<UserDto> results;

    public ResponseWrapperUserDto(Integer count, List<UserDto> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public List<UserDto> getResults() {
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
