// ���ø����̼��� ��Ʈ��Ʈ�� Ŭ�������� �� ������ ���� Ŭ����
// <������ ��Ʈ��Ʈ����>
// ������ ��Ʈ ���ø����̼ǿ��� ������ ��Ʈ��Ʈ���� �� ���� ������ �����.
// 1. ���� �߿��� ������ ���� Ŭ����
// ������ ��Ʈ�� �ڵ� ������ ������ ������ ������ ����������, �ڵ� ������ Ȱ��ȭ�ϴ� �ּ����� ������ �ؾ���.
package reading;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication // ������Ʈ �˻��� �ڵ� ���� Ȱ��ȭ
//@SpringBootApplication : ������ �ֳ����̼� �� ���� ���� ��
//->@Configuration : �� �ֳ����̼��� ���� Ŭ������ �������� �ڹ� ��� ���� Ŭ������ ����.
//->@ComponentScan : ������Ʈ �˻� ����� Ȱ��ȭ�ؼ� �� ��Ʈ�ѷ� Ŭ������ �ٸ� ������Ʈ Ŭ�������� �ڵ����� �˻��Ͽ�
//                  ������ ���ø����̼� ���ؽ�Ʈ�� ������ ��Ͻ�Ŵ.
//->@EnableAutoConfiguration : ������ ���� �ڵ� ��ü ����

public class Reading1Application implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Reading1Application.class, args);
	}
	// ReadingListApplication.java�� ������ ���� ���� ����. ������ ��Ʈ �ڵ� ������ �����ϴ� �� �̻�����
	// �߰����� ������ ������ �ʿ��ϴٸ�, ������ @Configuration ���ͳ��̼��� ������ ���� Ŭ�������� �ۼ��ϴ� ����
	// ���� ����. �� ���� Ŭ�������� ������Ʈ �˻����� �ڵ����� �߰���.
	

	
	// 3-3. �α��ΰ� Reader �Ű������� �ؼ�
	
	//�α��� �������� �� ����
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// login ��θ� login ���ø����� ����
		registry.addViewController("/login").setViewName("login");
	}
	

	// Reader Ÿ���� ��Ʈ�ѷ� �Ű������� ó���� �� ����
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// Reader Ÿ���̤� ��ü�� ��Ʈ�ѷ� �Ű������� ���� �� ó���� ������ ����
		argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
	}
	
}
