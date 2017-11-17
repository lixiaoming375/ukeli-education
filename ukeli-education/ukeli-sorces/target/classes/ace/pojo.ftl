<#include "copyright.ftl"/>

package ${spackage}.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import cn.javaworker.yeming.pojo.BaseTableEntry;
<#include "version.ftl"/>

@SuppressWarnings("serial")
@JsonIgnoreProperties({"adderid","adder","editer","editerid","addtime"})
public class ${pojo}  extends BaseTableEntry implements Serializable {				//<#if desc??>${desc}</#if>
	
	<#list columnList as column>
	private ${column.javaType} ${column.name?lower_case};   //<#if column.desp??>${column.desp}</#if>
	</#list>

	public ${pojo}(){}

	public ${pojo}(<#list allcolumnList as column>${column.javaType} ${column.name?lower_case}<#if column_index < (allcolumnList?size-1) >,</#if></#list>){
		<#list allcolumnList as column>
		this.${column.name?lower_case}=${column.name?lower_case};
		</#list>
	}

	/**
	 * 获取表名称
	 * @return
	 */
	public static String getTable() {
		return "${tableName}";
	}

	<#list columnList as column>
	/**
	* @return the ${column.name?lower_case}
	*/
	public ${column.javaType} get${column.name?cap_first}(){
		return ${column.name?lower_case};
	}

	/**
	* @param ${column.name?lower_case} the ${column.name?lower_case} to set
	*/
	public void set${column.name?cap_first}(${column.javaType} ${column.name?lower_case}){
		<#if column.javaType=="String">
		this.${column.name?lower_case} = StringUtils.trim(${column.name?lower_case});
		<#else>
		this.${column.name?lower_case} = ${column.name?lower_case};
		</#if>
	}
	</#list>
	
    /* (non-Javadoc)
    * @see cn.javaworker.yeming.pojo.Entry#hashCode()
    */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	/* (non-Javadoc)
	* @see cn.javaworker.yeming.pojo.Entry#equals(java.lang.Object)
	*/
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
}