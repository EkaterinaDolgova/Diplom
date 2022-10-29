import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CreateUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")


public class CreateUserDto {
    @JsonProperty("email")
    private String email = null;

    @JsonProperty("firstName")
    private String firstName = null;

    @JsonProperty("lastName")
    private String lastName = null;

    @JsonProperty("password")
    private String password = null;

    @JsonProperty("phone")
    private String phone = null;

    public CreateUserDto email(String email) {
        this.email = email;
        return this;
    }
}