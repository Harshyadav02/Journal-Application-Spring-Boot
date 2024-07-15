package com.harsh.journalApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Autowired
    private  UserDetailsServiceImpl userDetailsServiceImpl;

    // @Bean
    // public PasswordEncoder passwordEncoder() {
    //     return new BCryptPasswordEncoder();
    // }
   
    
    // @Autowired
    // @Lazy
    // protected void configure(@Lazy AuthenticationManagerBuilder auth) throws Exception {
    //     auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    // }
    
   
    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                .requestMatchers("/user/**","/journal/**" ).authenticated()
                .requestMatchers("/admin/**" ).hasRole("ADMIN")
                .anyRequest().permitAll()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .userDetailsService(userDetailsServiceImpl);
        return http.build();
                
        
    }

}   
