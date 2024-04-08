package hh.sof3.movieapp;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;
import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import hh.sof3.movieapp.web.UserDetailServiceImpl;

// import static method
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;




@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig  {
	
    @Autowired
    private UserDetailServiceImpl userDetailsService;


	// using lambdas 
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {


    http
      .authorizeHttpRequests( authorize -> authorize
        	.requestMatchers(antMatcher("/css/**")).permitAll()
            .requestMatchers(antMatcher("/index")).permitAll()
            .requestMatchers(antMatcher("/movielist")).permitAll()
            .requestMatchers(antMatcher("/movies")).permitAll()
            .requestMatchers(antMatcher("/watchlist")).permitAll()
            .requestMatchers(antMatcher("/watches")).permitAll()
            .requestMatchers(toH2Console()).permitAll()
        	.anyRequest().authenticated()
      )
    .csrf(csrf -> csrf
        .ignoringRequestMatchers(toH2Console())
    )
    .headers(headers -> headers
        .frameOptions(frameoptions -> frameoptions
                .disable())
    )
    .formLogin(formlogin -> formlogin
        .loginPage("/login")
        .defaultSuccessUrl("/index", true)
        .permitAll()
    )
    .logout(logout -> logout
        .permitAll()
    );

      return http.build();
    }

    /* 
    public SecurityFilterChain configure( WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/h2-console/**");
    );
    */

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(new
        BCryptPasswordEncoder());
    }



 

}
