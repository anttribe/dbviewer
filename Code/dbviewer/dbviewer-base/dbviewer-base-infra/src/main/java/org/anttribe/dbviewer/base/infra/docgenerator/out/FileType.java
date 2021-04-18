package org.anttribe.dbviewer.base.infra.docgenerator.out;

/**
 * 文件类型
 * 
 * @author zhaoyong
 * @date 2021年2月17日
 */
public enum FileType {

	HTML(".html", ""), TXT(".txt", "");

	/**
	 * 文件后缀
	 */
	private String fileSuffix;

	/**
	 * 描述信息
	 */
	private String description;

	private FileType(String fileSuffix) {
		this(fileSuffix, "");
	}

	private FileType(String fileSuffix, String description) {
		this.fileSuffix = fileSuffix;
		this.description = description;
	}

	public String getFileSuffix() {
		return fileSuffix;
	}

	public void setFileSuffix(String fileSuffix) {
		this.fileSuffix = fileSuffix;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}