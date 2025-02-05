package com.example.demo.config;


//import com.example.demo.config;
import com.example.demo.filter.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.web.SecurityFilterChain;



@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    public SecurityConfig() {
    }


//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .antMatchers("/", "/**").permitAll() // Allow all access
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().disable(); // Disable login
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable()).authorizeHttpRequests((authorize) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)((AuthorizeHttpRequestsConfigurer.AuthorizedUrl)authorize.requestMatchers(new String[]{"/auth/signup", "/auth/login", "/properties/add"})).permitAll().requestMatchers(HttpMethod.GET, new String[]{"/properties/**"})).permitAll().requestMatchers(HttpMethod.PUT, new String[]{"/properties/**"})).permitAll().requestMatchers(HttpMethod.DELETE, new String[]{"/properties/**"})).permitAll().anyRequest()).authenticated());
        return (SecurityFilterChain)http.build();
    }
}

