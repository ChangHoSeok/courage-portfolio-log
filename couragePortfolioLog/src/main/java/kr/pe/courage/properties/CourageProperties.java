
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

	public static class Keys {
		private String object;
		private String name;
		private String email;

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
	}
}
