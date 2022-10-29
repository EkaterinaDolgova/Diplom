package ru.skypro.homework.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CreateAds
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")


public class CreateAds {
    @JsonProperty("description")
    private String description = null;

    @JsonProperty("image")
    private String image = null;

    @JsonProperty("pk")
    private Integer pk = null;

    @JsonProperty("price")
    private Integer price = null;

    @JsonProperty("title")
    private String title = null;

    public CreateAds description(String description) {
        this.description = description;
        return this;
    }
}