package com.elv.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.elv.models.Student;
import com.elv.repo.StudentRepo;


/*@Configuration
public class SpringSecurityConfigurationClass extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)throws Exception{
		auth.inMemoryAuthentication().withUser("neeraj").password("123").roles("USER");
	}
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Basic Authentication
		http.csrf().disable()
		.authorizeRequests().anyRequest().authenticated().and().httpBasic();
		
		//With JWT
		//1 Ways (OK)
//		http.csrf().disable().authorizeRequests()
//        .antMatchers("/").permitAll()
//        .antMatchers(HttpMethod.POST, "/login").permitAll()
//        .anyRequest().authenticated()
//        .and()
//        // We filter the api/login requests
//        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
//                UsernamePasswordAuthenticationFilter.class)
//        // And filter other requests to check the presence of JWT in header
//        .addFilterBefore(new JWTAuthenticationFilter(),
//                UsernamePasswordAuthenticationFilter.class);
		
		//2 Ways(OK)
		http.csrf().disable().authorizeRequests()
        .antMatchers("/","/").permitAll()
        .anyRequest().authenticated()
        .and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
		
		http.csrf().disable().authorizeRequests()
        .antMatchers("/").permitAll()
        .antMatchers(HttpMethod.POST, "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        // We filter the api/login requests
        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                UsernamePasswordAuthenticationFilter.class)
        // And filter other requests to check the presence of JWT in header
        .addFilterBefore(new JWTAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
	}

}
*/

@Configuration
public class SpringSecurityConfigurationClass extends WebSecurityConfigurerAdapter {

	
	@Override
	  protected void configure(HttpSecurity http) throws Exception {
	   /* http.authorizeRequests().anyRequest().fullyAuthenticated().and().
	    httpBasic().and().
	    csrf().disable();*/
		 /*
		 http.csrf().disable().authorizeRequests()
	        .antMatchers("/","/").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        // We filter the api/login requests
	        .addFilterBefore(new JWTLoginFilter("/", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
	        // And filter other requests to check the presence of JWT in header
	        .addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);*/
			
		 
		
		
		 //Ok
		http.csrf().disable()
		     .authorizeRequests()
		    .antMatchers("/").permitAll()
	        .antMatchers(HttpMethod.POST, "/login").permitAll()
	        .anyRequest().authenticated()
	        .and()
	        // We filter the api/login requests
	        .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
	                UsernamePasswordAuthenticationFilter.class)
	        // And filter other requests to check the presence of JWT in header
	        .addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class); 
	/*	 
		 http
         .csrf().disable()
         .authorizeRequests()
         	 .antMatchers("/").permitAll()
             .antMatchers("/login").permitAll()
         .and()
         .authorizeRequests()
             .antMatchers("/logout").permitAll()
         .and()
         .authorizeRequests()
             .anyRequest().authenticated()
           .and()
         // We filter the api/signup requests
         .addFilterBefore(
             new JWTLogoutFilter("/logout", authenticationManager()),
             UsernamePasswordAuthenticationFilter.class)
         // We filter the api/login requests
         .addFilterBefore(
             new JWTLoginFilter("/login", authenticationManager()),
             UsernamePasswordAuthenticationFilter.class)
         // And filter other requests to check the presence of JWT in
         // header
         .addFilterBefore(new JWTAuthenticationFilter(),
	                UsernamePasswordAuthenticationFilter.class);
        */
	
	  }
	
}

@Configuration
class WebSecurityConfiguration extends GlobalAuthenticationConfigurerAdapter {

  @Autowired
  StudentRepo accountRepository;

  @Override
  public void init(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService());
  }
  
  

  @Bean
  UserDetailsService userDetailsService() {
    return new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student account = accountRepository.findBySname(username);
        if(account != null) {
        return new User(account.getSname(), account.getId()+"", true, true, true, true,
                AuthorityUtils.createAuthorityList("USER"));
        } else {
          throw new UsernameNotFoundException("could not find the user '"
                  + username + "'");
        }
      }
      
    };
  }
}