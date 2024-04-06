package com.technicanApp.technicanApp.DAO;

import com.technicanApp.technicanApp.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Integer> {
}
