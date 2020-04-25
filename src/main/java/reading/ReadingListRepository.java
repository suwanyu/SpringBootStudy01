package reading;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// DB에 Book 객체를 영속화할 수 있는 리포지토리 선언
// 스프링 데이터 JPA의 JpaRepository 인터페이스를 상속하여 인터페이스를 만듦
/*JpaRepository: JpaRepository는 메소드 이름 작성 방법만 알다면, 필요한 메소드를 빠르게 쓰고 추가할 수 있다. 
처리는 코드 일체 필요 없다. 단지, 메소드 선언문만 있으면 된다. 
단지, 미리 정해진 룰에 따라 제대로 메소드 이름을 붙이기만 하면 된다. 
적당히 이름을 붙이는 것만으로 메소드가 자동 생성이 되는 것이다.
- "findBy" 이후에 엔티티의 속성 이름을 붙이다. 
이 속성 이름은 첫 글자는 대문자로 한다. 예를 들어, name 검색한다면 "findByName"이며, mail에서 찾는다면 "findByMail"가 된다.

- "퍼지 검색"에 관한 것이다. Like를 붙이면, 인수에 지정된 텍스트를 포함하는 엔티티를 검색한다. 
또한 NotLike을 쓰면 인수의 텍스트를 포함하지 않는 것을 검색한다. "findByNameLike"이라면, name에서 인수의 텍스트를 퍼지 검색한다.

 * 
 * */

public interface ReadingListRepository extends JpaRepository<Book,Long>{
	// ReadingListRepository는 JpaRepository를 확장하여 공통된 영속성 연산을 수행하는 메소드 18개를 상속받음
	// JpaRepository 인터페이스는 타입 매개변수 두 개를 받음. 
	// 리포지토리가 사용할 도메인 타입, 클래스의 ID 프로퍼티 타입
	
	// 지정한 독자의 이름으로 독서 목록을 검색하는 findByReader()메서드
	List<Book> findByReader(Reader reader);
	
}
