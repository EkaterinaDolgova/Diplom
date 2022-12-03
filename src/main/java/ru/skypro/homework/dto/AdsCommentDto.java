package ru.skypro.homework.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import java.time.OffsetDateTime;

/**
 * AdsComment
 */
@Data
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")

public class AdsCommentDto {

    @JsonProperty("pk")
    @Column(name = "pk", nullable = false)
    private Long pk;
    @JsonProperty("author")
    private Integer author;

    @JsonProperty()
    private OffsetDateTime createdAt;

    @JsonProperty()
    private String text;

}