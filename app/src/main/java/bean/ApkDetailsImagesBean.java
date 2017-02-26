package bean;

import java.io.Serializable;


public class ApkDetailsImagesBean implements Serializable{

	private String AI_FirstScreenshot;
	private String AI_SecondScreenshot;
	private String AI_ThirdScreenshot;
	private String AI_FifthScreenshot;

	public String getAI_FirstScreenshot() {
		return AI_FirstScreenshot;
	}

	public void setAI_FirstScreenshot(String aI_FirstScreenshot) {
		this.AI_FirstScreenshot = aI_FirstScreenshot;
	}

	public String getAI_SecondScreenshot() {
		return AI_SecondScreenshot;
	}

	public void setAI_SecondScreenshot(String aI_SecondScreenshot) {
		this.AI_SecondScreenshot = aI_SecondScreenshot;
	}

	public String getAI_ThirdScreenshot() {
		return AI_ThirdScreenshot;
	}

	public void setAI_ThirdScreenshot(String aI_ThirdScreenshot) {
		this.AI_ThirdScreenshot = aI_ThirdScreenshot;
	}

	public String getAI_FifthScreenshot() {
		return AI_FifthScreenshot;
	}

	public void setAI_FifthScreenshot(String aI_FifthScreenshot) {
		this.AI_FifthScreenshot = aI_FifthScreenshot;
	}
}
