
package kr.pe.courage.setup.domain;

import kr.pe.courage.common.CommonVO;

/**
 * <pre>
 * kr.pe.courage.setup.domain
 * SetupVO.java
 * </pre>
 *
 * @Author : ChangHo.Seok
 * @Date : 2017. 11. 7.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * PortfoLog 설정 Value Object
 * 
 * << 개정이력 >>
 * 1. 일자 : 2017. 11. 7., 수정자 : ChangHo.Seok, 내용 : 최초등록
 *      </pre>
 */
public class SetupVO extends CommonVO {
	private int pbSno;
	private String title;
	private String subTitle;
	private String mainPhotoUrl;
	private String desc;
	private String aboutMe;
	private String location;
	private String aroundTheWeb;
	private String github;
	private String facebook;
	private String twitter;
	private String googlePlus;
	private String linkedIn;
	private String copylight;

	public int getPbSno() {
		return pbSno;
	}

	public void setPbSno(int pbSno) {
		this.pbSno = pbSno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public String getMainPhotoUrl() {
		return mainPhotoUrl;
	}

	public void setMainPhotoUrl(String mainPhotoUrl) {
		this.mainPhotoUrl = mainPhotoUrl;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getAboutMe() {
		return aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAroundTheWeb() {
		return aroundTheWeb;
	}

	public void setAroundTheWeb(String aroundTheWeb) {
		this.aroundTheWeb = aroundTheWeb;
	}

	public String getGithub() {
		return github;
	}

	public void setGithub(String github) {
		this.github = github;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getGooglePlus() {
		return googlePlus;
	}

	public void setGooglePlus(String googlePlus) {
		this.googlePlus = googlePlus;
	}

	public String getLinkedIn() {
		return linkedIn;
	}

	public void setLinkedIn(String linkedIn) {
		this.linkedIn = linkedIn;
	}

	public String getCopylight() {
		return copylight;
	}

	public void setCopylight(String copylight) {
		this.copylight = copylight;
	}
}
