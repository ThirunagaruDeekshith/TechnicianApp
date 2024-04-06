package com.technicanApp.technicanApp.TechnicianService;

import com.technicanApp.technicanApp.entity.Technician;

import java.util.List;

public interface technician_Interface {

    List<Technician> findAll();
    Technician findbyId(int id);
    Technician save(Technician technician);
    void deleteById(int Id);
}
