// 애플리케이션의 부트스트랩 클래스이자 주 스프링 구성 클래스
// <스프링 부트스트래핑>
// 스프링 부트 애플리케이션에서 구성과 부트스트래핑 두 가지 역할을 담당함.
// 1. 가장 중요한 스프링 구성 클래스
// 스프링 부트의 자동 구성이 수많은 스프링 구성을 제거하지만, 자동 구성을 활성화하는 최소한의 구성은 해야함.
package reading;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication // 컴포넌트 검색과 자동 구성 활성화
//@SpringBootApplication : 유용한 애너테이션 세 개를 묶은 것
//->@Configuration : 이 애너테이션이 붙은 클래스를 스프링의 자바 기반 구성 클래스로 지정.
//->@ComponentScan : 컴포넌트 검색 기능을 활성화해서 웹 컨트롤러 클래스나 다른 컴포넌트 클래스들을 자동으로 검색하여
//                  스프링 애플리케이션 컨텍스트에 빈으로 등록시킴.
//->@EnableAutoConfiguration : 수많은 구성 코드 대체 가능

public class Reading1Application implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(Reading1Application.class, args);
	}
	// ReadingListApplication.java는 변경할 일이 거의 없음. 스프링 부트 자동 구성이 제공하는 것 이상으로
	// 추가적인 스프링 구성이 필요하다면, 별도의 @Configuration 애터네이션을 포함한 구성 클래스에서 작성하는 것이
	// 가장 좋음. 이 구성 클래스들은 컴포넌트 검색으로 자동으로 추가함.
	

	
	// 3-3. 로그인과 Reader 매개변수를 해석
	
	//로그인 페이지용 뷰 매핑
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		// login 경로를 login 템플릿으로 매핑
		registry.addViewController("/login").setViewName("login");
	}
	

	// Reader 타입의 컨트롤러 매개변수를 처리할 수 있음
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		// Reader 타입이ㅡ 객체가 컨트롤러 매개변수로 있을 때 처리할 리졸버 설정
		argumentResolvers.add(new ReaderHandlerMethodArgumentResolver());
	}
	
}
