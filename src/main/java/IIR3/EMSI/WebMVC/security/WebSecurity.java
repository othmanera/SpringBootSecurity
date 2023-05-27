package IIR3.EMSI.WebMVC.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurity {
	@Autowired
	PasswordEncoder PassEncod;
	
	@Bean
	public SecurityFilterChain Secure(HttpSecurity https) throws Exception{
		
		https.formLogin().defaultSuccessUrl("/list");
		https.authorizeHttpRequests().requestMatchers("/delete","/saveProduit").hasRole("admin");
		https.authorizeHttpRequests().anyRequest().authenticated();
		return https.build();
	}

	//@Bean
	public InMemoryUserDetailsManager addUser() {
		String pwd=PassEncod.encode("123");
		System.out.println(pwd);
		UserDetails admin=User.withUsername("Admin").password(pwd).roles("admin").build();
		UserDetails user1=User.withUsername("user1").password(pwd).roles("user").build();
		InMemoryUserDetailsManager IM=new InMemoryUserDetailsManager(admin,user1);
		return IM;
	}
	
	@Bean
	public JdbcUserDetailsManager userDetails(DataSource datasource) {
		String pwd=PassEncod.encode("123");
		UserDetails admin=User.withUsername("othmane").password(pwd).roles("admin").build();
		UserDetails user1=User.withUsername("saloua").password(pwd).roles("user").build();
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(datasource);
		users.createUser(admin);
		users.createUser(user1);
		return users;
		
		
	}

}
