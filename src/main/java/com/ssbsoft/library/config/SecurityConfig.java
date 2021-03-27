package com.ssbsoft.library.config;

import com.ssbsoft.library.common.Constants;
import com.ssbsoft.library.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.context.request.RequestContextListener;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Configuration
@EnableWebSecurity
@ComponentScan("com.ssbsoft.library")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomAuthenticationProvider customAuthProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        System.out.println("........9...AuthenticationManagerBuilder....");
        auth.authenticationProvider(customAuthProvider);
    }

    @Bean
    @Order(0)
    public RequestContextListener requestContextListener() {
        return new RequestContextListener();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("........10...HttpSecurity....");
        http.authorizeRequests()
                .antMatchers( "/user/register").permitAll()
                .antMatchers("/email/resetPassword").permitAll()
                .antMatchers("/email/reset").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/app/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .loginProcessingUrl("/session")
                .usernameParameter("name")
                .passwordParameter("password")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error=true")
                .successHandler(getAuthenticationSuccessHandler())
                .failureHandler(getAuthenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(getLogoutSuccessHandler())
                .and().headers().frameOptions().sameOrigin()
                .and()
                .csrf().disable();
    }
    private AuthenticationSuccessHandler getAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            HttpSession session = request.getSession();
            User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            session.setAttribute("currentUser", authUser);
            //set our response to OK status
            response.setStatus(HttpServletResponse.SC_OK);
            request.getSession().setMaxInactiveInterval(60 * 60 * 24);
            response.setStatus(HttpStatus.OK.value());
            request.getSession().setAttribute("loginmsg", Constants.LOGINSUCCESS);
            response.sendRedirect("home");
        };
    }

    private AuthenticationFailureHandler getAuthenticationFailureHandler() {
        return (request, response, exception) -> {
            request.getSession().setAttribute("errormsg", Constants.INVAlID_USERNAME_PASSWORD);
            response.sendRedirect("login");
        };
    }

    private LogoutSuccessHandler getLogoutSuccessHandler() {
        return (request, response, authentication) -> {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            request.getSession().setAttribute("message", Constants.LOGOUTMSG);
            response.sendRedirect("/login?logout=success");
        };
    }

}

