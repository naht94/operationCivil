package com.nhnacademy.operationcivil.config;

import com.nhnacademy.operationcivil.Service.CustomUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.InMemoryOAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = true)
@Configuration
public class SecurityConfig{
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/residents/**").permitAll()
                .requestMatchers("/household/**").permitAll()
                .requestMatchers("/members/**").permitAll()
                .anyRequest().hasAnyAuthority("ROLE_ADMIN","ROLE_USER")
                .and()
                .formLogin()
                .usernameParameter("id")
                .passwordParameter("pwd")
                .and()
                .oauth2Login(o -> o
                        .clientRegistrationRepository(clientRegistrationRepository())
                        .authorizedClientService(authorizedClientService()))
                .logout().and()
                .csrf().disable()
                .build();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails admin = User.withUsername("admin")
//                .password("{noop}admin")
//                .authorities("ROLE_ADMIN")
//                .build();
//
//        UserDetails member = User.withUsername("USER")
//                .password("{noop}user")
//                .authorities("ROLE_USER")
//                .build();
//
//        UserDetails guest = User.withUsername("guest")
//                .password("{noop}guest")
//                .authorities("ROLE_GUEST")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, member, guest);
//    }
    @Bean
    public AuthenticationProvider authenticationProvider(CustomUserDetailService customUserDetailService) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(customUserDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        return new InMemoryClientRegistrationRepository(github());
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService() {
        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
    }

    private ClientRegistration github() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github")
                .userNameAttributeName("name")
                .clientId("9dcc3769284b205e8f78")
                .clientSecret("a480714857a65c2a91583ad683912f583011677c")
                .build();
    }
}