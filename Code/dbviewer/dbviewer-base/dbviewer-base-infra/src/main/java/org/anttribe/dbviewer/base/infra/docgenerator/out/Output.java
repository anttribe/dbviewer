package org.anttribe.dbviewer.base.infra.docgenerator.out;

import java.io.File;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * “输出”
 * 
 * @author zhaoyong
 * @date 2021年1月12日
 */
public class Output {

	/**
	 * 输出文件
	 */
	private File outputFile;

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public File getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}
}