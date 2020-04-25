package reading;
// 웹 인터페이스 만들기
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// 컴포넌트 검색으로 ReadingListController를 발견하여 자동으로 스프링 애플리케이션 컨텍스트에 빈으로 등록.
@Controller
// 요청을 처리하는 모든 메서드를 기본 URL 경로인 /로 매핑.
@RequestMapping("/")
public class ReadingListController {
	
	// 3-5 더이상 사용하지 않는 정적 final 변수 reader 삭제.
	/* private static final String reader = "craig"; */
	
	
	private ReadingListRepository readingListRepository;
	private AmazonProperties amazonProperties;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository,
			AmazonProperties amazonProperties) { // AmazonProperties 주입
		this.readingListRepository=readingListRepository;
		this.amazonProperties=amazonProperties;
	}
	

	
	// 인스턴스 변수에 지정한 독자의 Book 리스트를 컨트롤러의 생성자로 주입된 리포지토리에서 조회하는 메소드.
	@RequestMapping(method=RequestMethod.GET)	// '/'로 들어오는 HTTP GET 요청을 처리함
	// 3-5 Reader 타입의 매개변수 추가
	public String readerBooks(Reader reader, Model model) {
		// 인스턴스 변수에 지정한 독자의 Book List
		
		// 지정한 독자의 이름으로 독서 목록을 검색하는 findByReader()메서드 (reader는 craig로 정의)
		List<Book> readingList = readingListRepository.findByReader(reader);
		
		// 'books' 키로 Book 목록을 모델에 추가하고,
		if (readingList != null) {
			model.addAttribute("books",readingList);
			model.addAttribute("reader",reader);
			model.addAttribute("amazonID",amazonProperties.getAssociateId());
		}
		// 모델을 렌더링할 뷰의 논리적 이름으로 'readingList'를 반환
		return "readingList";
	}
	
	
	// 요청 바디에 있는 데이터를 Book 객체에 바인드하여 '/'로 들어오는 HTTP POST 요청을 처리함
	// Book 객체의 reader 프로퍼티를 독자의 이름으로 설정한 후 리포지토리의 save() 메서드를 이용하여 Book 객체 저장
	// 마지막으로, '/'로 리다이렉트("Redirect:/")하도록 지정하면서 반환
	@RequestMapping(method=RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}
	
	
	
	
}
