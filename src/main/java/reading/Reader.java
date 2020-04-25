package reading;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// JPA 엔티티로 만듦
@Entity
public class Reader  implements UserDetails{
	public Reader() {}
	
    private static final long serialVersionUID = 1L;
	
	// username필드를 엔티티의 ID로 표시. 이제 username필드가 Reader를 유일하게 식별
	@Id //Reader 필드
	private String username;
	
	private String fullname;
	private String password;
	
	//UserDetails 메소드
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
	
	// Reader가 UserDetails 인터페이스에서 여러 메소드를 구현함. 따라서 스프링 시큐리티에서 사용자를 표현하는 객체로 Reader 사용 가능
	public Collection<? extends GrantedAuthority> getAuthorities(){ //READER권한 부여
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
	}
	
	public boolean isAccountNonExpired() { // 계정이 만료되지 않았다는 것을 반환
		return true;
	}
	
	public boolean isAccountNonLocked() { // 계정이 잠겨 있지 않다는 것을 반환
		return true;
	}

	public boolean isCredentialsNonExpired() { // 자격이 유효하다는 것을 반환
		return true;
	}
	
	public boolean isEnabled() { // 계정이 활성화되어 있다는 것을 반환
		return true;
	}

	public Reader(String username, String fullname, String password) {
		super();
		this.username = username;
		this.fullname = fullname;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Reader [username=" + username + ", fullname=" + fullname + ", password=" + password + "]";
	}




}
