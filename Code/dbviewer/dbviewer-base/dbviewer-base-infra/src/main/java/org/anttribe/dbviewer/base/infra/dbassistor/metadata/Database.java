package org.anttribe.dbviewer.base.infra.dbassistor.metadata;

import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 关系型数据库“产品”
 * 
 * @author zhaoyong
 * @date 2020-12-13
 */
public class Database {

	/**
	 * 数据库产品名
	 */
	private String productName;

	/**
	 * 数据库产品版本号
	 */
	private String productVersion;

	/**
	 * 数据库列表(可访问的)
	 */
	private List<DbSchema> schemas;

	public Database() {
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductVersion() {
		return productVersion;
	}

	public void setProductVersion(String productVersion) {
		this.productVersion = productVersion;
	}

	public List<DbSchema> getSchemas() {
		return schemas;
	}

	public void setSchemas(List<DbSchema> schemas) {
		this.schemas = schemas;
	}

}