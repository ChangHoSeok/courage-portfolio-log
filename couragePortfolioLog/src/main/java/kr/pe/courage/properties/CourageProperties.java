
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
}
