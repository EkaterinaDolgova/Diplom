package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import ru.skypro.homework.entities.Users;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * ResponseWrapperUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-30T18:04:31.779Z[GMT]")


public class ResponseWrapperUserDto {
    @JsonProperty("count")
    private Integer count = null;
    @JsonProperty("results")
    @Valid
    private List<UserDto> results = null;

    public ResponseWrapperUserDto(Integer count, List<UserDto> results) {
        this.count = count;
        this.results = results;
    }

    public ResponseWrapperUserDto count(Integer count) {
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

    public ResponseWrapperUserDto results(List<UserDto> results) {
        this.results = results;
        return this;
    }

    public ResponseWrapperUserDto addResultsItem(UserDto resultsItem) {
        if (this.results == null) {
            this.results = new ArrayList<UserDto>();
        }
        this.results.add(resultsItem);
        return this;
    }

    /**
     * Get results
     *
     * @return results
     **/
    @Schema(description = "")
    @Valid
    public List<UserDto> getResults() {
        return results;
    }

    public void setResults(List<UserDto> results) {
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
        ResponseWrapperUserDto responseWrapperUser = (ResponseWrapperUserDto) o;
        return Objects.equals(this.count, responseWrapperUser.count) &&
                Objects.equals(this.results, responseWrapperUser.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, results);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ResponseWrapperUser {\n");

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
