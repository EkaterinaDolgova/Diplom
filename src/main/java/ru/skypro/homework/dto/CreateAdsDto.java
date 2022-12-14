package ru.skypro.homework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CreateAds
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")


public class CreateAdsDto {
    @JsonProperty()
    private String description;

    @JsonProperty()
    private Integer price;

    @JsonProperty()
    private String title;

    public CreateAdsDto(String description, Integer price, String title) {
        this.description = description;
        this.price = price;
        this.title = title;
    }

    public CreateAdsDto() {
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }
}