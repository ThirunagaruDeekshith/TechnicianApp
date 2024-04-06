package com.technicanApp.technicanApp.DAO;

import com.technicanApp.technicanApp.entity.Access;
//import jakarta.persistence.Access;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessRepository extends JpaRepository<Access,Integer> {
}
