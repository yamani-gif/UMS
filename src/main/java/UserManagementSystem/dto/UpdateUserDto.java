package UserManagementSystem.dto;





import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    @NotNull
    @NotBlank
    private int id;
    @NotNull
    private String userName;
    @NotNull
    private String mobileNumber;
    @Email
    private String email;
    @NotNull
    @NotBlank
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;
    @NotNull
    private String jobRole;
}
