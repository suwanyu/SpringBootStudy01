package reading;


import org.springframework.data.jpa.repository.JpaRepository;


// ������ ��ť��Ƽ�� JDBC ���, LDAP ���, �θ޸� ����� ����Ҹ� ������ �� ���� ���� �ɼ��� ����.
// -> JPA�� �̿��� �����ͺ��̽��� ������� ����ڸ� ����
public interface ReaderRepository extends JpaRepository<Reader, String> {
    
}

