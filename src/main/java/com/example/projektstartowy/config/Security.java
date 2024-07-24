package com.example.projektstartowy.config;


import com.example.projektstartowy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractAuthenticationFilterConfigurer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class Security {

    // Nadpisanie domyslnego zabezpieczenia Security

    @Autowired
    private UserService userService;
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return  http.csrf(AbstractHttpConfigurer::disable).authorizeHttpRequests(registry->
    {
        registry.requestMatchers("/home","/register/**").permitAll(); // zezwól na wejscie kazdemu na pole rejestracji
        registry.requestMatchers("/admin/**").hasRole("ADMIN"); // zezwól na wejscie do panelu admina tylko adminom
        registry.requestMatchers("/user/**").hasRole("USER"); // zezwól na wejscie do panelu usera dla userów
        registry.anyRequest().authenticated(); // wszystko poza musi byc autoryzowane
    }).formLogin(AbstractAuthenticationFilterConfigurer::permitAll).build();
}
@Bean
public UserService  userDetailsService() {
    return  userService;
}

@Bean
public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
}
@Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

}
