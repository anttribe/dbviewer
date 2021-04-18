package org.anttribe.dbviewer.base.infra.support.model;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author zhaoyong
 * @date 2020-11-29
 */
public abstract class Model implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 585592095097992573L;

	/**
	 * id，唯一标识一条数据
	 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
	}

}