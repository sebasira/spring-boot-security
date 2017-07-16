package com.sebasira.MediumTutorialSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

/**
 * Created by sebas on 16/07/17.
 */
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // password encoder reference implemented in WebMvcConfig.java
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // data source implemented out of the box by Spring Boot. We
    // only need to provide the database information in the application.properties file
    @Autowired
    private DataSource dataSource;

    // Reference to user and role queries stored in application.properties file
    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;


    /**
     * AuthenticationManagerBuilder provides a mechanism to get a user based on the
     * password encoder, data source, user query and role query.
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.
                jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(bCryptPasswordEncoder);
    }


    /**
     * Here we define the antMatchers to provide access based on the role(s) (lines 62 to 65)
     * the parameters for the login process (lines 69 to 70), the success login page(line 67),
     * the failure login page(line 67), and the logout page (line 72).
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/registration").permitAll()
                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .defaultSuccessUrl("/admin/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
    }


    /**
     * Due we have implemented Spring Security we need to let Spring knows that our
     * resources folder can be served skipping the antMatchers defined.
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}
