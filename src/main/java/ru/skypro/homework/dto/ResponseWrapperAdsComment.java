package ru.skypro.homework.dto;

import java.util.List;

public class ResponseWrapperAdsComment {
    private Integer count;
    private List<AdsComment> results;

    public ResponseWrapperAdsComment(Integer count, List<AdsComment> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public List<AdsComment> getResults() {
        return results;
    }

    @Override
    public String toString() {
        return "ResponseWrapperAdsComment{" +
                "count=" + count +
                ", results=" + results +
                '}';
    }
}
