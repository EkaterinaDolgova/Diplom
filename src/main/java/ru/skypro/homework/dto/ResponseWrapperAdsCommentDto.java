package ru.skypro.homework.dto;

import java.util.List;

public class ResponseWrapperAdsCommentDto {
    private Integer count;
    private List<AdsCommentDto> results;

    public ResponseWrapperAdsCommentDto(Integer count, List<AdsCommentDto> results) {
        this.count = count;
        this.results = results;
    }

    public Integer getCount() {
        return count;
    }

    public List<AdsCommentDto> getResults() {
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