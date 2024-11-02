package com.gaw.dvdrental.config;

import com.gaw.dvdrental.config.auth.MyCustomAuthenticationSuccessHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

//    @Autowired
//    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/index").permitAll()
                        .requestMatchers("/register/**").permitAll()
                        .requestMatchers("/demo/**").permitAll()
                        .requestMatchers("/admin/**").hasAnyRole("ADMIN")
                        .requestMatchers("/user/**").hasAnyRole("USER","ADMIN")
                        .requestMatchers("/index").hasAnyRole("USER","ADMIN")
                        .anyRequest().authenticated()
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
//                                .defaultSuccessUrl("/home")
                                .successHandler(customAuthenticationSuccessHandler())
//                                .failureHandler(customAuthenticationFailureHandler())
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                                .invalidateHttpSession(true)
                ).sessionManagement(
                        httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
                                .invalidSessionUrl("/error")
                );
        return http.build();
    }

    @Bean
    public AuthenticationSuccessHandler customAuthenticationSuccessHandler(){
        return new MyCustomAuthenticationSuccessHandler();
    }

//    @Bean
//    public AuthenticationFailureHandler customAuthenticationFailureHandler(){
//        return new MyCustomAuthenticationFailureHandler();
//    }



//    @Bean
//    public AuthenticationManager authenticationManager(
//            UserDetailsService userDetailsService,
//            PasswordEncoder passwordEncoder) {
//        System.out.println("### authenticationManager");
//
////        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
////        authenticationProvider.setUserDetailsService(userDetailsService);
////        authenticationProvider.setPasswordEncoder(passwordEncoder);
//
//        AtlassianCrowdAuthenticationManager authenticationProvider = new AtlassianCrowdAuthenticationManager();
//
//        return new ProviderManager(authenticationProvider());
//
//    }

//    @Bean
//    public AuthenticationProvider authenticationProvider() {
//        System.out.println("### authenticationProvider");
//        return new AtlassianCrowdAuthenticationManager();
//    }

    /*
    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("### userDetailsService");
        UserDetails userDetails = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(userDetails);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    } */

    @Bean
    public static BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
////        System.out.println("configureGlobal");
////        System.out.println(userDetailsService.toString());
////        System.out.println(auth);
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }

}
