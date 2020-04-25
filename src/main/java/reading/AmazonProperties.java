package reading;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("amazon") //amazon 접두어가 붙은 프로퍼티 주입
public class AmazonProperties {
	private String associateId;
	public AmazonProperties() {}
	
	public void setAssociateId(String associateId) { // associateId 세터 메소드
		this.associateId=associateId;
	}
	
	public String getAssociateId() {
		return associateId;
	}
}
