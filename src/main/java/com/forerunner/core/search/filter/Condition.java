package com.forerunner.core.search.filter;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.forerunner.core.search.SearchOperator;
import com.forerunner.core.search.exception.InvlidSearchOperatorException;
import com.forerunner.core.search.exception.SearchException;
/**
 * 查询过滤条件
 * @author Administrator
 *
 */
public final class Condition implements SearchFilter {

	/**
	 * 
	 */
	private static final long serialVersionUID = -791222922701101767L;
	/**
	 * 查询参数分隔符
	 */
	public static final String SEPARATOR = "_";

	private String key;

	private String searchProperty;

	private String operator;

	private Object value;

	public Condition() {
		super();
	}

	private Condition(final String searchProperty, final String operator, final Object value) {
		this.searchProperty = searchProperty;
		this.operator = operator;
		this.value = value;
		this.key = this.searchProperty + SEPARATOR + this.operator;
	}

	/**
	 * 根据查询key和值生成Condition
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Condition newCondition(final String key, final Object value) throws SearchException {
		Assert.notNull(key, "Condition key must not null");
		String[] searchs = StringUtils.split(key, SEPARATOR);
		if (searchs.length == 0) {
			throw new SearchException("Condition key format must be : property or property_op");
		}
		String searchProperty = searchs[0];
		String operator = null;
		if (searchs.length == 1) {
			operator = SearchOperator.custom;
		} else {
			try {
				operator = SearchOperator.valueOf(searchs[1]);
			} catch (Exception e) {
				throw new InvlidSearchOperatorException(searchProperty, searchs[1]);
			}
		}
		boolean allowBlankValue = SearchOperator.isAllowBlankValue(operator);
		boolean isValueBlank = (value == null);
		isValueBlank = isValueBlank || (value instanceof String && StringUtils.isBlank((String) value));
		isValueBlank = isValueBlank || (value instanceof List && ((List) value).size() == 0);
		// 过滤掉空值，即不参与查询
		if (!allowBlankValue && isValueBlank) {
			return null;
		}
		Condition searchFilter = newCondition(searchProperty, operator, value);
		return searchFilter;
	}

	/**
	 * 根据查询属性、操作符和值生成Condition
	 * 
	 * @param searchProperty
	 * @param operator
	 * @param value
	 * @return
	 */
	public static Condition newCondition(final String searchProperty, final String operator, final Object value) {
		return new Condition(searchProperty, operator, value);
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getSearchProperty() {
		return searchProperty;
	}

	public void setSearchProperty(String searchProperty) {
		this.searchProperty = searchProperty;
	}

	public String getOperator() throws InvlidSearchOperatorException {
		return operator;
	}

	/**
	 * 获取自定义查询使用的操作符<br>
	 * 获取顺序:1、首先获取前台传的2、返回空
	 * 
	 * @return
	 */
	public String getOperatorStr() {
		if (operator != null) {
			return operator;
		}
		return "";
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * 得到实体属性名
	 * 
	 * @return
	 */
	public String getEntityProperty() {
		return searchProperty;
	}
	/**
	 * 是否是一元运算符 如 is null  is not null
	 * @return
	 */
	public boolean isUnaryFilter() {
		String operatorStr = getOperator();
		 return StringUtils.isNotEmpty(operatorStr) && operatorStr.startsWith("is");
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condition other = (Condition) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Condition [key=" + key + ", searchProperty=" + searchProperty + ", operator=" + operator + ", value="
				+ value + "]";
	}
}
