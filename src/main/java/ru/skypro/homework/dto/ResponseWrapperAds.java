package ru.skypro.homework.dto;

import ru.skypro.homework.dto.Ads;

import java.util.List;

public class ResponseWrapperAds {
    private Integer count;
    private List<Ads> results;

    public ResponseWrapperAds(Integer count, List<Ads> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public List<Ads> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ResponseWrapperAds{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
