package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ResponseWrapperAdsCommentDto
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-30T18:04:31.779Z[GMT]")

public class ResponseWrapperAdsCommentDto {
    @JsonProperty("count")
    private Integer count = null;
    @JsonProperty("results")
    @Valid
    private List<AdsCommentDto> results = null;

    public ResponseWrapperAdsCommentDto count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * Get count
     *
     * @return count
     **/
    @Schema(description = "")

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }


    public ResponseWrapperAdsCommentDto results(List<AdsCommentDto> results) {
        this.results = results;
        return this;
    }

    public ResponseWrapperAdsCommentDto addResultsItem(AdsCommentDto resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<AdsCommentDto>();
        }
        this.results.add(resultsItem);
        return this;
    }

    /**
     * Get results
     * @return results
     **/
    @Schema(description = "")
    @Valid
    public List<AdsCommentDto> getResults() {
        return results;
    }

    public void setResults(List<AdsCommentDto> results) {
        this.results = results;
    }

    public ResponseWrapperAdsCommentDto(Integer count, List<AdsCommentDto> results) {
        this.count = count;
        this.results = results;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseWrapperAdsCommentDto responseWrapperAdsComment = (ResponseWrapperAdsCommentDto) o;
        return Objects.equals(this.count, responseWrapperAdsComment.count) &&
                Objects.equals(this.results, responseWrapperAdsComment.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseWrapperAdsComment {\n");

        sb.append("    count: ").append(toIndentedString(count)).append("\n");
        sb.append("    results: ").append(toIndentedString(results)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(java.lang.Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}