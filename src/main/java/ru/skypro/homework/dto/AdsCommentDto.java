package ru.skypro.homework.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

import javax.persistence.Column;
import java.time.OffsetDateTime;

/**
 * AdsComment
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")

public class AdsCommentDto {

    @JsonProperty("pk")
    @Column(name = "pk", nullable = false)
    private Long pk;
    @JsonProperty("author")
    private Integer author;

    @JsonProperty("createdAt")
    private OffsetDateTime createdAt;

    @JsonProperty("text")
    private String text;

    public AdsCommentDto author(Integer author) {
        this.author = author;
        return this;
    }
}