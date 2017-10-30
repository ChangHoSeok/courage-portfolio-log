
package kr.pe.courage.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * kr.pe.courage.properties
 * CourageProperties.java
 * </pre>
 *
 * @Author : ChangHo Seok
 * @Date : 2017. 9. 30.
 * @Version : 1.0
 * @see
 * 
 *      <pre>
 * << 개정이력 >>
 * 1. 수정일 : 2017. 9. 30., 수정자 : ChangHo Seok, 수정내용 : 최초등록
 *      </pre>
 */
@Component
@ConfigurationProperties("courage")
public class CourageProperties {
	private Web web = new Web();
	private Storage file = new Storage();
	private Resource resource = new Resource();
	private Session session = new Session();
	private Link link = new Link();

	public Web getWeb() {
		return web;
	}

	public void setWeb(Web web) {
		this.web = web;
	}

	public Storage getFile() {
		return file;
	}

	public void setFile(Storage file) {
		this.file = file;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public Link getLink() {
		return link;
	}

	public void setLink(Link link) {
		this.link = link;
	}

	public static class Web {
		private String title;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}
	}

	public static class Storage {
		private String savePath;
		private String whiteList;

		public String getSavePath() {
			return savePath;
		}

		public void setSavePath(String savePath) {
			this.savePath = savePath;
		}

		public String getWhiteList() {
			return whiteList;
		}

		public void setWhiteList(String whiteList) {
			this.whiteList = whiteList;
		}
	}

	public static class Resource {
		private String jsExt;
		private String cssExt;

		public String getJsExt() {
			return jsExt;
		}

		public void setJsExt(String jsExt) {
			this.jsExt = jsExt;
		}

		public String getCssExt() {
			return cssExt;
		}

		public void setCssExt(String cssExt) {
			this.cssExt = cssExt;
		}
	}

	public static class Session {
		private Keys keys;

		public Keys getKeys() {
			return keys;
		}

		public void setKeys(Keys keys) {
			this.keys = keys;
		}
	}
	
	public static class Link {
		private Gravatar gravatar;

		public Gravatar getGravatar() {
			return gravatar;
		}

		public void setGravatar(Gravatar gravatar) {
			this.gravatar = gravatar;
		}
	}

	public static class Keys {
		private String object;
		private String name;
		private String email;
		private String gravatarURL;

		public String getObject() {
			return object;
		}

		public void setObject(String object) {
			this.object = object;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
		
		public String getGravatarURL() {
			return gravatarURL;
		}

		public void setGravatarURL(String gravatarURL) {
			this.gravatarURL = gravatarURL;
		}
	}

	public static class Gravatar {
		private String url;
		private String defaultImg;
		private String size;

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getDefaultImg() {
			return defaultImg;
		}

		public void setDefaultImg(String defaultImg) {
			this.defaultImg = defaultImg;
		}

		public String getSize() {
			return size;
		}

		public void setSize(String size) {
			this.size = size;
		}
	}
}
