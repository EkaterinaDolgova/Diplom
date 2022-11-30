import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.validation.annotation.Validated;

/**
 * CreateUser
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2022-10-29T09:53:39.743Z[GMT]")


public class CreateUserDto {
    @JsonProperty()
    private String email;

    @JsonProperty()
    private String firstName;

    @JsonProperty()
    private String lastName;

    @JsonProperty()
    private String password;

    @JsonProperty()
    private String phone;

    public CreateUserDto email(String email) {
        this.email = email;
        return this;
    }
}