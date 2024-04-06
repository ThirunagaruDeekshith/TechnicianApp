package com.technicanApp.technicanApp.DAO;

import com.technicanApp.technicanApp.entity.Technician;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician,Integer> {
}
