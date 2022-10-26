package com.jejujerry.guestbook.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        //사용자계정은 user1
        auth.inMemoryAuthentication().withUser("jejujerry")
                .password("$2a$10$17iZ96MDJdUAau3datBXGu.56nIwyug.dLkY5UIsCiIIegYHZCw.y")
                .roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4construction").permitAll();
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4receiving").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4construction").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4agriculture").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4landlocked").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4house").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4pubhouse").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4flatswithshop").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4rentshop").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4renthouse").hasRole("USER");
//        http.authorizeHttpRequests().antMatchers("/guestbook/map4renthousemy").hasRole("USER");

        http.authorizeHttpRequests().antMatchers("/**/*").hasRole("USER");

        http.formLogin();
        http.csrf().disable();
        http.logout();



    }


}
