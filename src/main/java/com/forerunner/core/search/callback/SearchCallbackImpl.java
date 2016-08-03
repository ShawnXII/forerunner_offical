package com.forerunner.core.search.callback;

import javax.persistence.Query;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.forerunner.core.search.SearchOperator;
import com.forerunner.core.search.Searchable;
import com.forerunner.core.search.filter.AndCondition;
import com.forerunner.core.search.filter.Condition;
import com.forerunner.core.search.filter.OrCondition;
import com.forerunner.core.search.filter.SearchFilter;

public class SearchCallbackImpl implements SearchCallback {

	private static final String paramPrefix = "param_";

	private String alias;
	private String aliasWithDot;
	/**
	 * 
	 */
	private static final long serialVersionUID = 8577992351130705037L;

	public SearchCallbackImpl() {
		this("x");
	}

	public SearchCallbackImpl(String alias) {
		this.alias = alias;
		if (!"".equals(alias) && alias != null) {
			this.aliasWithDot = alias + ".";
		} else {
			this.aliasWithDot = "";
		}
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getAliasWithDot() {
		return aliasWithDot;
	}

	public void setAliasWithDot(String aliasWithDot) {
		this.aliasWithDot = aliasWithDot;
	}

	public static String getParamprefix() {
		return paramPrefix;
	}

	@Override
	public void prepareQL(StringBuilder ql, Searchable search) {
		String tmp = ql.toString();
		if (!search.hasSearchFilter()) {
			return;
		}
		int paramIndex = 1;
		for (SearchFilter searchFilter : search.getSearchFilters()) {
			if (searchFilter instanceof Condition) {
				Condition condition = (Condition) searchFilter;
				if (condition.getOperator() == SearchOperator.custom) {
					continue;
				}
			}
			ql.append(" and  ");
			paramIndex = genCondition(ql, paramIndex, searchFilter);
		}
		ql.delete(tmp.length(), tmp.length() + 4);
		ql.insert(tmp.length() + 1, "where");
	}

	private int genCondition(StringBuilder ql, int paramIndex, SearchFilter searchFilter) {
		boolean needAppendBracket = searchFilter instanceof OrCondition || searchFilter instanceof AndCondition;
		if (needAppendBracket) {
			ql.append("(");
		}
		if (searchFilter instanceof Condition) {
			Condition condition = (Condition) searchFilter;
			// 自定义条件
			String entityProperty = condition.getEntityProperty();
			String operatorStr = condition.getOperatorStr();
			// 实体名称
			ql.append(getAliasWithDot());
			ql.append(entityProperty);
			// 操作符
			// 1、如果是自定义查询符号，则使用SearchPropertyMappings中定义的默认的操作符
			ql.append(" ");
			ql.append(operatorStr);
			if (!condition.isUnaryFilter()) {
				ql.append(" :");
				ql.append(paramPrefix);
				ql.append(paramIndex++);
				return paramIndex;
			}
		} else if (searchFilter instanceof OrCondition) {
			boolean isFirst = true;
			for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
				if (!isFirst) {
					ql.append(" or ");
				}
				paramIndex = genCondition(ql, paramIndex, orSearchFilter);
				isFirst = false;
			}
		} else if (searchFilter instanceof AndCondition) {
			boolean isFirst = true;
			for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
				if (!isFirst) {
					ql.append(" and ");
				}
				paramIndex = genCondition(ql, paramIndex, andSearchFilter);
				isFirst = false;
			}
		}
		if (needAppendBracket) {
			ql.append(")");
		}
		return paramIndex;
	}

	@Override
	public void prepareOrder(StringBuilder ql, Searchable search) {
		if (search.hashSort()) {
			ql.append(" order by ");
			for (Sort.Order order : search.getSort()) {
				ql.append(String.format("%s%s %s, ", getAliasWithDot(), order.getProperty(),
						order.getDirection().toString()));
			}
			ql.delete(ql.length() - 2, ql.length());
		}
	}

	@Override
	public void setValues(Query query, Searchable search) {
		int paramIndex = 1;
		for (SearchFilter searchFilter : search.getSearchFilters()) {
			paramIndex = setValues(query, searchFilter, paramIndex);
		}
	}

	private int setValues(Query query, SearchFilter searchFilter, int paramIndex) {
		if (searchFilter instanceof Condition) {

			Condition condition = (Condition) searchFilter;
			if (condition.getOperator() == SearchOperator.custom) {
				return paramIndex;
			}
			if (condition.isUnaryFilter()) {
				return paramIndex;
			}
			query.setParameter(paramPrefix + paramIndex++, formtValue(condition, condition.getValue()));

		} else if (searchFilter instanceof OrCondition) {

			for (SearchFilter orSearchFilter : ((OrCondition) searchFilter).getOrFilters()) {
				paramIndex = setValues(query, orSearchFilter, paramIndex);
			}

		} else if (searchFilter instanceof AndCondition) {
			for (SearchFilter andSearchFilter : ((AndCondition) searchFilter).getAndFilters()) {
				paramIndex = setValues(query, andSearchFilter, paramIndex);
			}
		}
		return paramIndex;
	}

	private Object formtValue(Condition condition, Object value) {
		String operator = condition.getOperator();
		if (operator.equals(SearchOperator.like) || operator.equals(SearchOperator.notLike)) {
			return "%" + value + "%";
		}
		if (operator.equals(SearchOperator.prefixLike) || operator.equals(SearchOperator.prefixNotLike)) {
			return value + "%";
		}

		if (operator.equals(SearchOperator.suffixLike) || operator.equals(SearchOperator.suffixNotLike)) {
			return "%" + value;
		}
		return value;
	}

	@Override
	public void setPageable(Query query, Searchable search) {
		if (search.hasPageable()) {
			Pageable pageable = search.getPage();
			query.setFirstResult(pageable.getOffset());
			query.setMaxResults(pageable.getPageSize());
		}
	}

}
