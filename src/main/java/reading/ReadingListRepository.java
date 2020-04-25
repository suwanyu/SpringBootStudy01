package reading;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

// DB�� Book ��ü�� ����ȭ�� �� �ִ� �������丮 ����
// ������ ������ JPA�� JpaRepository �������̽��� ����Ͽ� �������̽��� ����
/*JpaRepository: JpaRepository�� �޼ҵ� �̸� �ۼ� ����� �˴ٸ�, �ʿ��� �޼ҵ带 ������ ���� �߰��� �� �ִ�. 
ó���� �ڵ� ��ü �ʿ� ����. ����, �޼ҵ� ���𹮸� ������ �ȴ�. 
����, �̸� ������ �꿡 ���� ����� �޼ҵ� �̸��� ���̱⸸ �ϸ� �ȴ�. 
������ �̸��� ���̴� �͸����� �޼ҵ尡 �ڵ� ������ �Ǵ� ���̴�.
- "findBy" ���Ŀ� ��ƼƼ�� �Ӽ� �̸��� ���̴�. 
�� �Ӽ� �̸��� ù ���ڴ� �빮�ڷ� �Ѵ�. ���� ���, name �˻��Ѵٸ� "findByName"�̸�, mail���� ã�´ٸ� "findByMail"�� �ȴ�.

- "���� �˻�"�� ���� ���̴�. Like�� ���̸�, �μ��� ������ �ؽ�Ʈ�� �����ϴ� ��ƼƼ�� �˻��Ѵ�. 
���� NotLike�� ���� �μ��� �ؽ�Ʈ�� �������� �ʴ� ���� �˻��Ѵ�. "findByNameLike"�̶��, name���� �μ��� �ؽ�Ʈ�� ���� �˻��Ѵ�.

 * 
 * */

public interface ReadingListRepository extends JpaRepository<Book,Long>{
	// ReadingListRepository�� JpaRepository�� Ȯ���Ͽ� ����� ���Ӽ� ������ �����ϴ� �޼ҵ� 18���� ��ӹ���
	// JpaRepository �������̽��� Ÿ�� �Ű����� �� ���� ����. 
	// �������丮�� ����� ������ Ÿ��, Ŭ������ ID ������Ƽ Ÿ��
	
	// ������ ������ �̸����� ���� ����� �˻��ϴ� findByReader()�޼���
	List<Book> findByReader(Reader reader);
	
}
