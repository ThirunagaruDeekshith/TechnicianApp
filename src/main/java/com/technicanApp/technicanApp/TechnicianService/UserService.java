package com.technicanApp.technicanApp.TechnicianService;

import com.technicanApp.technicanApp.DAO.AccessRepository;
import com.technicanApp.technicanApp.DAO.UserRepository;
import com.technicanApp.technicanApp.entity.Access;
import com.technicanApp.technicanApp.entity.Users;
import org.springframework.stereotype.Service;

@Service
public class UserService implements User_Interface{
    private UserRepository userRepository;
    private AccessRepository accessRepository;

    public UserService(UserRepository userRepository, AccessRepository accessRepository) {
        this.userRepository = userRepository;
        this.accessRepository = accessRepository;
    }

    @Override
    public Users save(Users user, Access access) {
        userRepository.save(user);
        access.setCustomer_name(user.getUsername());
        access.setAuthority("ROLE_User");
        accessRepository.save(access);
        return user;
    }
}
