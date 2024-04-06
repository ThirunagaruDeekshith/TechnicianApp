package com.technicanApp.technicanApp.secure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import javax.sql.DataSource;
@Configuration
public class SecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails sai = User.builder().username("Sai").password("{noop}Sai123").roles("User").build();
//        UserDetails somesh = User.builder().username("Somesh").password("{noop}Somesh123").roles("Technician","User").build();
//        UserDetails suma = User.builder().username("Suma").password("{noop}Suma123").roles("Technician","ADMIN").build();
//        return new InMemoryUserDetailsManager(sai,somesh,suma);
//    }
   @Bean
    public UserDetailsManager userDetailsManager (DataSource dataSource){

        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        jdbcUserDetailsManager.setUsersByUsernameQuery("select Customer_name,pswd,active from customers where Customer_name=?");
        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select Customer_name,authority from access_db where Customer_name=?");
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(configurer->
                configurer.requestMatchers(HttpMethod.GET,"/Technicians/List").hasRole("User")
                        .requestMatchers(HttpMethod.GET,"/showFormAdd").hasRole("User")
                        .requestMatchers(HttpMethod.GET,"/Technicians/showFormForUpdate").hasRole("Technician")
                        .requestMatchers(HttpMethod.POST,"/Technicians/save").hasRole("User")
//                          .requestMatchers(HttpMethod.PUT,"/api/technicians").hasRole("Technician")
                        .requestMatchers(HttpMethod.GET,"/Technicians/delete").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/Technicians/CreateUser").anonymous()
                        .requestMatchers(HttpMethod.POST,"/Technicians/processUserForm**").anonymous()
                        .anyRequest().authenticated()
                ).formLogin(form->form.loginPage("/Technicians/showMyLoginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll()
                )
                .logout(logout->logout.permitAll()
                ).exceptionHandling(configurer->configurer.accessDeniedPage("/Technicians/access-denied"));
        httpSecurity.httpBasic(Customizer.withDefaults());
        httpSecurity.csrf(csrf->csrf.disable());
        return httpSecurity.build();
    }
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
//       http.authorizeHttpRequests(configurer->configurer.anyRequest().authenticated()
//       ).formLogin(form->form.loginPage("/Technicians/showMyLoginPage")
//               .loginProcessingUrl("/authenticateTheUser")
//               .permitAll()
//       )
//               .logout(logout->logout.permitAll());
//
//       return http.build();
//    }

}