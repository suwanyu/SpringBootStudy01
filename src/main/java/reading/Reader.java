package reading;

import java.util.Arrays;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// JPA ��ƼƼ�� ����
@Entity
public class Reader  implements UserDetails{
	public Reader() {}
	
    private static final long serialVersionUID = 1L;
	
	// username�ʵ带 ��ƼƼ�� ID�� ǥ��. ���� username�ʵ尡 Reader�� �����ϰ� �ĺ�
	@Id //Reader �ʵ�
	private String username;
	
	private String fullname;
	private String password;
	
	//UserDetails �޼ҵ�
	
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

	
	
	
	// Reader�� UserDetails �������̽����� ���� �޼ҵ带 ������. ���� ������ ��ť��Ƽ���� ����ڸ� ǥ���ϴ� ��ü�� Reader ��� ����
	public Collection<? extends GrantedAuthority> getAuthorities(){ //READER���� �ο�
		return Arrays.asList(new SimpleGrantedAuthority("ROLE_READER"));
	}
	
	public boolean isAccountNonExpired() { // ������ ������� �ʾҴٴ� ���� ��ȯ
		return true;
	}
	
	public boolean isAccountNonLocked() { // ������ ��� ���� �ʴٴ� ���� ��ȯ
		return true;
	}

	public boolean isCredentialsNonExpired() { // �ڰ��� ��ȿ�ϴٴ� ���� ��ȯ
		return true;
	}
	
	public boolean isEnabled() { // ������ Ȱ��ȭ�Ǿ� �ִٴ� ���� ��ȯ
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
