package br.com.eliasnepo.proposta.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_propostas:read")
                                .antMatchers(HttpMethod.POST, "/propostas").hasAuthority("SCOPE_propostas:write")
                                .antMatchers(HttpMethod.POST, "/biometrias/**").hasAuthority("SCOPE_biometrias:write")
                                .antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_actuator:read")
                                .antMatchers("/h2-console/**").permitAll()
                                .anyRequest().authenticated()
                )
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
    }

    @Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	        .antMatchers("/h2-console/**");
	}
}