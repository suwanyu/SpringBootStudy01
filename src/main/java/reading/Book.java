package reading;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // JPA ��ƼƼ
public class Book {
	
	private Reader reader;
	
	@Id //��ƼƼ�� ���ϼ� �ĺ�
	@GeneratedValue(strategy=GenerationType.AUTO) // �ڵ����� ���� ����
	private Long id;
	private String isbn;
	private String title;
	private String author;
	private String description;
	
	public Book() {};
	
	public Book(Long id, Reader reader, String isbn, String title, String author, String description) {
		super();
		this.id = id;
		this.reader = reader;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.description = description;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Reader getReader() {
		return reader;
	}
	public void setReader(Reader reader) {
		this.reader = reader;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", reader=" + reader + ", isbn=" + isbn + ", title=" + title + ", author=" + author
				+ ", description=" + description + "]";
	}
	
	
}
