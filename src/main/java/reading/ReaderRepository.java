package reading;


import org.springframework.data.jpa.repository.JpaRepository;


// 스프릥 시큐리티는 JDBC 기반, LDAP 기반, 인메모리 사용자 저장소를 포함한 몇 가지 인증 옵션을 제공.
// -> JPA를 이용한 데이터베이스를 기반으로 사용자를 인증
public interface ReaderRepository extends JpaRepository<Reader, String> {
    
}

