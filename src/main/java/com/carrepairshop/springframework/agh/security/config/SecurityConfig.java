package com.carrepairshop.springframework.agh.security.config;

import com.carrepairshop.springframework.agh.employees.AbstractEmployee;
import com.carrepairshop.springframework.agh.handler.CustomAccessDeniedHandler;
import com.carrepairshop.springframework.agh.handler.UrlAuthenticationSuccessHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SimpleSavedRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger log = LogManager.getLogger(SecurityConfig.class);

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        class UsersRowMapper implements RowMapper<AbstractEmployee> {
            @Override
            public AbstractEmployee mapRow(ResultSet resultSet, int i) throws SQLException {
                AbstractEmployee abstractEmployee = new AbstractEmployee();
                abstractEmployee.setLogin(resultSet.getString("login"));
                abstractEmployee.setPassword(resultSet.getString("password"));
                abstractEmployee.setSetRole(resultSet.getString("setRole"));
                return abstractEmployee;
            }
        }

        log.info("searching users in table user");
        final String sql = "SELECT login, password, setRole FROM USERS " +
                "JOIN ROLE ON USERS.setRole=ROLE.role";
        List<AbstractEmployee> abstractEmployees = new ArrayList<>
                (jdbcTemplate.query(sql, new UsersRowMapper()));

        for (AbstractEmployee a : abstractEmployees) {
            String login = a.getLogin();
            String password = a.getPassword();
            String role = a.getSetRole().toUpperCase();

            auth.inMemoryAuthentication().passwordEncoder(passwordEncoder)
                    .withUser(login).password(passwordEncoder.encode(password)).roles(role);
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * Set security true/false for disable/enable security authentication
         * Mention that disabling security should be used only with dev/test purposes!
         **/
        boolean security = true;
        if (!security)
            http.authorizeRequests().antMatchers("/").permitAll();
        else {
            http
                    .formLogin().and()
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/api/**").hasAnyRole("MANAGER", "MECHANIC", "ACCOUNTANT", "LOGISTICIAN")
                    .anyRequest().authenticated();
        }
    }

    @Bean
    public RequestCache refererRequestCache() {
        return new HttpSessionRequestCache() {
            @Override
            public void saveRequest(HttpServletRequest request, HttpServletResponse response) {
                String referrer = request.getHeader("referer");
                if (referrer != null) {
                    request.getSession().setAttribute("SPRING_SECURITY_SAVED_REQUEST", new SimpleSavedRequest(referrer));
                }
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public AccessDeniedHandler accessDeniedHandler(){
        return new CustomAccessDeniedHandler();
    }

    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new UrlAuthenticationSuccessHandler();
    }
}
