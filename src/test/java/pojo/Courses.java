package pojo;

import java.util.List;

public class Courses {

	private List<WebAutomation> WebAutomation;
	private List<Api> api;
	private List<Mobile>  mobile;

	public List<pojo.WebAutomation> getWebAutomation() {
		return WebAutomation;
	}

	public void setWebAutomation(List<pojo.WebAutomation> webAutomation) {
		this.WebAutomation = webAutomation;
	}

	public List<pojo.Api> getApi() {
		return api;
	}

	public void setApi(List<pojo.Api> api) {
		this.api = api;
	}

	public List<pojo.Mobile> getMobile() {
		return mobile;
	}

	public void setMobile(List<pojo.Mobile> mobile) {
		this.mobile = mobile;
	}

}
