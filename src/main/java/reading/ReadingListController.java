package reading;
// �� �������̽� �����
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// ������Ʈ �˻����� ReadingListController�� �߰��Ͽ� �ڵ����� ������ ���ø����̼� ���ؽ�Ʈ�� ������ ���.
@Controller
// ��û�� ó���ϴ� ��� �޼��带 �⺻ URL ����� /�� ����.
@RequestMapping("/")
public class ReadingListController {
	
	// 3-5 ���̻� ������� �ʴ� ���� final ���� reader ����.
	/* private static final String reader = "craig"; */
	
	
	private ReadingListRepository readingListRepository;
	private AmazonProperties amazonProperties;
	
	@Autowired
	public ReadingListController(ReadingListRepository readingListRepository,
			AmazonProperties amazonProperties) { // AmazonProperties ����
		this.readingListRepository=readingListRepository;
		this.amazonProperties=amazonProperties;
	}
	

	
	// �ν��Ͻ� ������ ������ ������ Book ����Ʈ�� ��Ʈ�ѷ��� �����ڷ� ���Ե� �������丮���� ��ȸ�ϴ� �޼ҵ�.
	@RequestMapping(method=RequestMethod.GET)	// '/'�� ������ HTTP GET ��û�� ó����
	// 3-5 Reader Ÿ���� �Ű����� �߰�
	public String readerBooks(Reader reader, Model model) {
		// �ν��Ͻ� ������ ������ ������ Book List
		
		// ������ ������ �̸����� ���� ����� �˻��ϴ� findByReader()�޼��� (reader�� craig�� ����)
		List<Book> readingList = readingListRepository.findByReader(reader);
		
		// 'books' Ű�� Book ����� �𵨿� �߰��ϰ�,
		if (readingList != null) {
			model.addAttribute("books",readingList);
			model.addAttribute("reader",reader);
			model.addAttribute("amazonID",amazonProperties.getAssociateId());
		}
		// ���� �������� ���� ���� �̸����� 'readingList'�� ��ȯ
		return "readingList";
	}
	
	
	// ��û �ٵ� �ִ� �����͸� Book ��ü�� ���ε��Ͽ� '/'�� ������ HTTP POST ��û�� ó����
	// Book ��ü�� reader ������Ƽ�� ������ �̸����� ������ �� �������丮�� save() �޼��带 �̿��Ͽ� Book ��ü ����
	// ����������, '/'�� �����̷�Ʈ("Redirect:/")�ϵ��� �����ϸ鼭 ��ȯ
	@RequestMapping(method=RequestMethod.POST)
	public String addToReadingList(Reader reader, Book book) {
		book.setReader(reader);
		readingListRepository.save(book);
		return "redirect:/";
	}
	
	
	
	
}
