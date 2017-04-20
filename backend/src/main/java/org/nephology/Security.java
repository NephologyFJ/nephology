package org.nephology;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


import static com.stormpath.spring.config.StormpathWebSecurityConfigurer.stormpath;

/**
 * Created by PLGrubskiM on 2017-03-01.
 */
@Configuration
public class Security extends WebSecurityConfigurerAdapter {

    private static final Logger log = LoggerFactory.getLogger(Security.class);

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(stormpath());
    }
}