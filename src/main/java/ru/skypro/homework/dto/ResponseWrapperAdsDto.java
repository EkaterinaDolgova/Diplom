package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.mapstruct.Mapper;
import org.springframework.validation.annotation.Validated;
import ru.skypro.homework.dto.AdsDto;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

/**
 * ResponseWrapperAds
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")


public class ResponseWrapperAdsDto {
    @JsonProperty("count")
    private Integer count = null;

    @JsonProperty("results")
    @Valid
    private List<AdsDto> results = null;

    public ResponseWrapperAdsDto(Integer count, List<AdsDto> results) {
        this.count = count;
        this.results = results;
    }

    public ResponseWrapperAdsDto count(Integer count) {
        this.count = count;
        return this;
    }

    /**
     * Get count
     * @return count
     **/
    @Schema(description = "")


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ResponseWrapperAdsDto responseWrapperAdsDto = (ResponseWrapperAdsDto) o;
        return Objects.equals(this.count, responseWrapperAdsDto.count) &&
                Objects.equals(this.results, responseWrapperAdsDto.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseWrapperAds {\n");

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
