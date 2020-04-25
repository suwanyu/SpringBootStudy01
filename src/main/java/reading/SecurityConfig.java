package reading;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Profile("production")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReaderRepository readerRepository;
    
    
    // '/' ���(ReadingListController �޼ҵ忡 ���εǾ� ����)���� READER ���� �ִ� ������ ����ڸ� ��û�� �� �ְ� ��
    // �̿��� ��� ��û ��ο��� ������ ���� ���� � ����ڵ� ��û�� �� ����
    // /login ��θ� �α��� �������� �α��� ���� ������(error �Ӽ� ����)�� ����
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
            .antMatchers("/").access("hasRole('READER')") // READER ���� �ʿ�
            .antMatchers("/**").permitAll()

         .and()

          .formLogin()
            .loginPage("/login") // �α��� �� ��� ����
            .failureUrl("/login?error=true");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(username->readerRepository.findById(username).orElseThrow(()->new UsernameNotFoundException(username)))
        .passwordEncoder(this.noOpPasswordEncoder());
    }

	@Bean
	public PasswordEncoder noOpPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}


}
