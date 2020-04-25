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
@WebAppConfiguration // 웹 컨텍스트 테스트 활성화
// ->SpringJUnit4ClassRunner가 애플리케이션 컨텍스트로 (기본값인 비웹용 ApplicationContext가 아니라) 
// WebApplicationContext를 생성하도록 선언함
public class MockMvcWebTests {
	
	@Autowired
	// WebApplicationContext 주입

	private WebApplicationContext webContext; 
	
	private MockMvc mockMvc;
	
	// 컨트롤러를 통합 테스트하는 Mock MVC
	@Before
	public void setupMovkMvc() {
		// JUnit의 @Before 애너테이션을 붙여 다른 테스트 메서드보다 먼저 실행해야 함을 나타냄
		// 이 메서드는 주입된 WebApplicationContext를 webAppCContextSetup() 메서드에 전달한 후
		// build() 메서드를 호출하여 MockMvc 인스턴스를 생성하고, 테스트 메서드에서 사용할 인스턴스 변수에 할당함
		mockMvc=MockMvcBuilders // MockMvc 설정
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
		
		mockMvc.perform(get("/")) // GET 요청 수행 
		.andExpect(status().isOk()) // HTTP 200 응답 코드를 검증함
		.andExpect(view().name("readingList")) // 응답 뷰의 논리적 이름이 readingList이기를 기대함
		.andExpect(model().attributeExists("books")) // 모델에 books 속성을 포함하고
		.andExpect(model().attribute("books",is(empty()))); // 이 속성은 빈 컬렉션임을 검증함

	}
	
	// 새 책을 등록하는 테스트
	@Test
	public void postBook() throws Exception {
		// 1. 책을 등록하고 요청 결과를 검증함
		mockMvc.perform(post("/")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED) // 콘텐트 타입 설정
				.param("title","BOOK TITLE") // MockMvcRequestsBilders.param() 메서드로 전송할 폼을 시뮬레이션 하는 필드 설정
				.param("author","BOOK AUTHOR")
				.param("isbn", "1234567890")
				.param("description", "DESCRIPTION"))
				.andExpect(status().is3xxRedirection())
				.andExpect(header().string("Location","/")); // 요청을 실행하면 응답이 /로 리다이렉트되는지 검증
		
		
		// 2. 첫 번째 테스트 메서드 성공->메인 페이지에 새로운 GET 요청을 실행하여 새로 생성된 책이 모델에 있는지 검증함
		// Book 객체 생성 ( 메인 페이지를 요청한 후 반환되는 모델 값과 비교하는 데 사용)
		Book expectedBook=new Book(); // 생성할 책 정보 설정
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
