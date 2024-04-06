
package com.technicanApp.technicanApp.entity;

import com.technicanApp.technicanApp.secure.LicenceNo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "Technician")
public class Technician {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @NotNull(message = " this is required")
    @Size(min = 3,message = "greater than 2 characters")
    @Column(name = "Technician_name")
    private String Technician_name;
    @Column(name = "Skill")
    private String Skill;

    @Column(name = "Phone_number")
    private String Phone_number;
    @Column(name = "Available")
    private String Available;
    @Column(name = "Available_Timings")
    private String Available_Timings;
    @Min(value = 0,message = "must be greater than or equal to zero")
    @Max(value = 7, message = "must be less than or equal to 7")
    @Column(name = "Workdays")
    private int workdays;
    @LicenceNo(value="TECH",message="must start with TECH")
    @Column(name = "Licence_no")
    private String licence_no;

    public Technician() {
    }


    public Technician(String technician_name, String skill, String phone_number, String available, String available_Timings, int workdays, String licence_no) {
        Technician_name = technician_name;
        Skill = skill;
        Phone_number = phone_number;
        Available = available;
        Available_Timings = available_Timings;
        this.workdays = workdays;
        this.licence_no=licence_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTechnician_name() {
        return Technician_name;
    }

    public void setTechnician_name(String technician_name) {
        Technician_name = technician_name;
    }

    public String getSkill() {
        return Skill;
    }

    public void setSkill(String skill) {
        Skill = skill;
    }

    public String getPhone_number() {
        return Phone_number;
    }

    public void setPhone_number(String phone_number) {
        Phone_number = phone_number;
    }

    public String getAvailable() {
        return Available;
    }

    public void setAvailable(String available) {
        Available = available;
    }

    public String getAvailable_Timings() {
        return Available_Timings;
    }

    public void setAvailable_Timings(String available_Timings) {
        Available_Timings = available_Timings;
    }

    public int getWorkdays() {
        return workdays;
    }

    public void setWorkdays(int workdays) {
        this.workdays = workdays;
    }

    public String getLicence_no() {
        return licence_no;
    }

    public void setLicence_no(String licence_no) {
        this.licence_no = licence_no;
    }

    @Override
    public String toString(){
//        return " List of technicians";
        return "Technician : { id : "+getId()+" Name: "+getTechnician_name()+", Skill : "+getSkill()+", Phone_number : "+getPhone_number()+" Available : "+getAvailable()+" Available_Timings : "+getAvailable_Timings() +" licence_no : "+licence_no+" Workdays5 : "+workdays+ " }";
    }
}
