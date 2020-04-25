package reading;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("amazon") //amazon ���ξ ���� ������Ƽ ����
public class AmazonProperties {
	private String associateId;
	public AmazonProperties() {}
	
	public void setAssociateId(String associateId) { // associateId ���� �޼ҵ�
		this.associateId=associateId;
	}
	
	public String getAssociateId() {
		return associateId;
	}
}
