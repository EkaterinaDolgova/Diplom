package ru.skypro.homework.dto;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


import java.util.Objects;

/**
 * Ads
 */
@Getter
@Setter
//@Builder
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")


public class AdsDto {
    @JsonProperty("author")
    private Integer author;

    @JsonProperty()
    private String image;

    @JsonProperty("pk")
    private Integer pk;

    @JsonProperty()
    private Integer price;

    @JsonProperty()
    private String title;

    public AdsDto author(Integer author) {
        this.author = author;
        return this;
    }

    @Schema(description = "")

    public Integer getAuthor() {
        return author;
    }

    public void setAuthor(Integer author) {
        this.author = author;
    }

    public AdsDto image(String image) {
        this.image = image;
        return this;
    }

    /**
     * Get image
     * @return image
     **/
    @Schema(description = "")

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AdsDto pk(Integer pk) {
        this.pk = pk;
        return this;
    }

    /**
     * Get pk
     * @return pk
     **/
    @Schema(description = "")

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public AdsDto price(Integer price) {
        this.price = price;
        return this;
    }

    /**
     * Get price
     * @return price
     **/
    @Schema(description = "")

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public AdsDto title(String title) {
        this.title = title;
        return this;
    }

    /**
     * Get title
     * @return title
     **/
    @Schema(description = "")

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public boolean equals(java.lang.Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AdsDto ads = (AdsDto) o;
        return Objects.equals(this.author, ads.author) &&
                Objects.equals(this.image, ads.image) &&
                Objects.equals(this.pk, ads.pk) &&
                Objects.equals(this.price, ads.price) &&
                Objects.equals(this.title, ads.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, image, pk, price, title);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class Ads {\n");

        sb.append("    author: ").append(toIndentedString(author)).append("\n");
        sb.append("    image: ").append(toIndentedString(image)).append("\n");
        sb.append("    pk: ").append(toIndentedString(pk)).append("\n");
        sb.append("    price: ").append(toIndentedString(price)).append("\n");
        sb.append("    title: ").append(toIndentedString(title)).append("\n");
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