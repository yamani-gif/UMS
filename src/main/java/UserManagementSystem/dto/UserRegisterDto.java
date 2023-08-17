package UserManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDto {

    
    private String userName;
    
    private String mobileNumber;
    
    private String email;
    
    //@Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;
    
    private String jobRole;
}
