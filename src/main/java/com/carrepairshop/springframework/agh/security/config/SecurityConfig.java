package com.carrepairshop.springframework.agh.security.config;

import com.carrepairshop.springframework.agh.employees.AbstractEmployee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        boolean security = false;
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
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
