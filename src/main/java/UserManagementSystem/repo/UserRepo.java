package UserManagementSystem.repo;

import UserManagementSystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface UserRepo extends JpaRepository <User, Integer> {

    
    List<User> findByEmailOrMobileNumber(String email, String mobileNumber);

}
