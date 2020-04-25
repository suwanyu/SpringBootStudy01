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
    
    
    // '/' 경로(ReadingListController 메소드에 매핑되어 있음)에는 READER 롤이 있는 인증된 사용자만 요청할 수 있게 함
    // 이외의 모든 요청 경로에는 별도의 인증 없이 어떤 사용자든 요청할 수 있음
    // /login 경로를 로그인 페이지와 로그인 실패 페이지(error 속성 포함)로 설정
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests()
            .antMatchers("/").access("hasRole('READER')") // READER 권한 필요
            .antMatchers("/**").permitAll()

         .and()

          .formLogin()
            .loginPage("/login") // 로그인 폼 경로 설정
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
