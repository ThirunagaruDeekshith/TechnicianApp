package com.technicanApp.technicanApp.TechnicianService;
//
//import com.spring_course.spring_learning.DAO.DAOimpl;
//import com.spring_course.spring_learning.DAO.TechnicianDAO;
import com.technicanApp.technicanApp.DAO.TechnicianRepository;
import com.technicanApp.technicanApp.entity.TechnicianNotFoundException;
import com.technicanApp.technicanApp.entity.Technician;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechService implements technician_Interface {
    private TechnicianRepository technicianRepository;
    public TechService(TechnicianRepository technicianRepository){
        this.technicianRepository=technicianRepository;
    }
    @Override
    public List<Technician> findAll() {
        return technicianRepository.findAll();
    }

    @Override
    public Technician findbyId(int id) {
        Optional<Technician> Result = technicianRepository.findById(id);
        Technician technician = null;
        if(Result.isPresent()){
            technician = Result.get();
        }
        else {
            throw new TechnicianNotFoundException("not found "+id);
        }
        return technician;
    }

    @Override
    public Technician save(Technician technician) {
        return technicianRepository.save(technician);
    }

    @Override
    public void deleteById(int Id) {
        technicianRepository.deleteById(Id);
    }
}
