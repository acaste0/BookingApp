package me.inc.bookingapp.config;

import me.inc.bookingapp.model.service.AccountServiceModel;
import me.inc.bookingapp.service.impl.AppAccountService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AppAccountService appAccountService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(AppAccountService appAccountService,
                                     PasswordEncoder passwordEncoder) {
        this.appAccountService = appAccountService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/", "/account/login", "/account/registration", "/account/submit/**").permitAll().
                antMatchers("/stay/**", "/train/**").permitAll().
                antMatchers("/admin/**").hasAnyRole("ADMIN").
                antMatchers("/**").authenticated().
                and().
                formLogin().
                loginPage("/account/login").
                usernameParameter("email").
                passwordParameter("password").
                defaultSuccessUrl("/").
                failureForwardUrl("/account/login-error").
                and().
                logout().
                logoutUrl("/account/logout").
                logoutSuccessUrl("/").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID");//bye! :-)
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.
                userDetailsService(appAccountService).
                passwordEncoder(passwordEncoder);
    }
}