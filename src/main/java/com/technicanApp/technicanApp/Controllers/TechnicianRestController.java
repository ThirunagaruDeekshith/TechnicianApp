package com.technicanApp.technicanApp.Controllers;

//import com.spring_course.spring_learning.DAO.TechnicianDAO;
import com.technicanApp.technicanApp.entity.Access;
import com.technicanApp.technicanApp.entity.TechnicianNotFoundException;
import com.technicanApp.technicanApp.entity.Technician;
import com.technicanApp.technicanApp.entity.Users;
import com.technicanApp.technicanApp.TechnicianService.TechService;
import com.technicanApp.technicanApp.TechnicianService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/Technicians")
public class TechnicianRestController {
    private TechService techService;
    private UserService userService;
    public TechnicianRestController(TechService techService, UserService userService){
        this.techService = techService;
        this.userService=userService;
    }
    @GetMapping("/List")
    public String findAll(Model model){
        List<Technician> technicianList=techService.findAll();
        model.addAttribute("Technicians",technicianList);
        return "list-technicians";

    }
    @GetMapping("/showFormAdd")
    public String showFormAdd(Model model){
        model.addAttribute("Technicians",new Technician());
        return "Technician-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("id") int id,Model model){
        Technician technician=techService.findbyId(id);
        model.addAttribute("Technicians",technician);
        return "Technician-form";
    }
    @PostMapping("/save")
    public String processTechnicianForm(@Valid @ModelAttribute("Technicians") Technician technician, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "Technician-Form";
        }
        else {
            System.out.println("Technician "+technician.getTechnician_name());
            techService.save(technician);
            return "redirect:/Technicians/List";
        }

    }
    @GetMapping("/")
    public String showHome(){
        return "home";
    }
    @GetMapping("/showMyLoginPage")
    public String showLoginPage(){
        return "home";
    }
    @GetMapping("/access-denied")
    public String accessdenied(){
        return "accessDeniedPage";
    }

    //User creation
    @GetMapping("/CreateUser")
    public String createUser(Model model){
        model.addAttribute("Users",new Users());
        model.addAttribute("Access",new Access());
        return "UserCreation";
    }
    @PostMapping("/processUserForm")
    public String processUserForm(@Valid @ModelAttribute("Users") Users user, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "UserCreation";
        }
        else {
            System.out.println("User "+user.getUsername());
            Access access=new Access(user.getUsername(),"ROLE_User");
            user.setEnabled(1);
            userService.save(user,access);
            return "redirect:/Technicians/List";
        }

    }
//    @PutMapping("/showFormForUpdate")
//    public String saveTechnician(@ModelAttribute("Technicians") Technician technician){
//        Technician dbTechnician = techService.save(technician);
//        return "redirect:/Technicians/List";
//    }


//    @PutMapping("/technicians")
//    public Technician updateTechnician(@RequestBody Technician technician){
//        Technician dbTechnician = techService.save(technician);
//        return dbTechnician;
//    }
    @GetMapping("/delete")
    public String deletebyId(@RequestParam("id") int technician_id){
        Technician technician_new = techService.findbyId(technician_id);
        if(technician_new==null){
            throw new TechnicianNotFoundException("not found technician :"+technician_id);
        }
        techService.deleteById(technician_id);
        return "redirect:/Technicians/List";
    }
    @InitBinder
    public  void initBinder(WebDataBinder dataBinder){
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class,stringTrimmerEditor);
    }

}
