package net.javaguides.springboot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @Schema(
            description = "UserDto First Name"
    )
    // User first name should not be null or empty
    @NotEmpty(message = "User first name should not be null or empty")
    private String firstName;
    @Schema(
            description = "UserDto Second Name"
    )
    // User last name should not be null or empty
    @NotEmpty(message = "User last name should not be null or empty")
    private String lastName;

    // User email should not be null or empty
    // Email address should be valid
    @Schema(
            description = "UserDto Email Address"
    )
    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email address should be valid")
    private String email;

    public void setId(Long userId) {
    }
}
