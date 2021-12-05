package com.example.transferhall.config;

import com.example.transferhall.models.enums.UserRoleEnum;
import com.example.transferhall.service.Impl.UserServiceSecurity;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserServiceSecurity userServiceSecurity;
    private final PasswordEncoder passwordEncoder;

    public AppSecurityConfig(UserServiceSecurity userServiceSecurity,
                             PasswordEncoder passwordEncoder) {
        this.userServiceSecurity = userServiceSecurity;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers("/login", "/", "/register", "/transfer-galerie/**",
                    "/custom-transfers/**", "/about-us", "/information/**", "/kontakt"
                    , "/support/**", "/i18n", "/test-gd").permitAll()
                //Testing Cloudinary
             .antMatchers("/cloudinary/add", "/cloudinary/all", "/cloudinary/delete").permitAll()
            .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
            .antMatchers("/admin/**").hasRole(UserRoleEnum.ADMIN.name())
            .antMatchers("/**").authenticated()
                .and()
                .csrf()
                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
          .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/user/profile")
                //TODO validation page
                .failureForwardUrl("/login")
          .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userServiceSecurity).passwordEncoder(passwordEncoder);
    }
}
