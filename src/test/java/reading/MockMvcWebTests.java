package reading;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.samePropertyValuesAs;


import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;




@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration // �� ���ؽ�Ʈ �׽�Ʈ Ȱ��ȭ
// ->SpringJUnit4ClassRunner�� ���ø����̼� ���ؽ�Ʈ�� (�⺻���� ������ ApplicationContext�� �ƴ϶�) 
// WebApplicationContext�� �����ϵ��� ������
public class MockMvcWebTests {
	
	@Autowired
	// WebApplicationContext ����

	private WebApplicationContext webContext; 
	
	private MockMvc mockMvc;
	
	// ��Ʈ�ѷ��� ���� �׽�Ʈ�ϴ� Mock MVC
	@Before
	public void setupMovkMvc() {
		// JUnit�� @Before �ֳ����̼��� �ٿ� �ٸ� �׽�Ʈ �޼��庸�� ���� �����ؾ� ���� ��Ÿ��
		// �� �޼���� ���Ե� WebApplicationContext�� webAppCContextSetup() �޼��忡 ������ ��
		// build() �޼��带 ȣ���Ͽ� MockMvc �ν��Ͻ��� �����ϰ�, �׽�Ʈ �޼��忡�� ����� �ν��Ͻ� ������ �Ҵ���
		mockMvc=MockMvcBuilders // MockMvc ����
				.webAppContextSetup(webContext)
				.build();
	}	
	
	@Test
	public void homePage() throws Exception {
		/*
		 * mockMvc.perform(MockMvcRequestBuilders.get("/"))
		 * .andExpect(MockMvcResultMatchers.status().isOk())
		 * .andExpect(MockMvcResultMatchers.view().name("readingList"))
		 * .andExpect(MockMvcResultMatchers.model().attributeExists("books"))
		 * .andExpect(MockMvcResultMatchers.model().attribute("books",
		 * Matchers.is(Matchers.empty())));
		 */
		
		mockMvc.perform(get("/")) // GET ��û ���� 
		.andExpect(status().isOk()) // HTTP 200 ���� �ڵ带 ������
		.andExpect(view().name("readingList")) // ���� ���� ���� �̸��� readingList�̱⸦ �����
		.andExpect(model().attributeExists("books")) // �𵨿� books �Ӽ��� �����ϰ�
		.andExpect(model().attribute("books",is(empty()))); // �� �Ӽ��� �� �÷������� ������

	}
	
	// �� å�� ����ϴ� �׽�Ʈ
	@Test
	public void postBook() throws Exception {
		// 1. å�� ����ϰ� ��û ����� ������
		mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED) // ����Ʈ Ÿ�� ����
				.param("title","BOOK TITLE") // MockMvcRequestsBilders.param() �޼���� ������ ���� �ùķ��̼� �ϴ� �ʵ� ����
				.param("author","BOOK AUTHOR")
				.param("isbn", "1234567890")
				.param("description", "DESCRIPTION"))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location","/")); // ��û�� �����ϸ� ������ /�� �����̷�Ʈ�Ǵ��� ����
		
		
		// 2. ù ��° �׽�Ʈ �޼��� ����->���� �������� ���ο� GET ��û�� �����Ͽ� ���� ������ å�� �𵨿� �ִ��� ������
		// Book ��ü ���� ( ���� �������� ��û�� �� ��ȯ�Ǵ� �� ���� ���ϴ� �� ���)
		Book expectedBook=new Book(); // ������ å ���� ����
		Reader r = new Reader();
		r.setUsername("craig");
		
		expectedBook.setId(1L);
		expectedBook.setReader(r);
		expectedBook.setTitle("BOOK TITLE");
		expectedBook.setAuthor("BOOK AUTHOR");
		expectedBook.setIsbn("1234567890");
		expectedBook.setDescription("DESCRIPTION");
		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("readingList"))
		.andExpect(model().attributeExists("books"))
		.andExpect(model().attribute("books", hasSize(1)))
		.andExpect(model().attribute("books", contains(samePropertyValuesAs(expectedBook))));
		
	}
}
