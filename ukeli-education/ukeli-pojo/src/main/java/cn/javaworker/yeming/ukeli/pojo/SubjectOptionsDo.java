/**
* <p> * Title: 商城管理信息系统 *</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2017* </p>
* <p> * Company: 苏州明翔信息科技有限公司 * </p>
* @author 叶明（开发）
* @version 0.1
*/
package cn.javaworker.yeming.ukeli.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.javaworker.yeming.pojo.BaseTableEntry;
/**
* 创建日期：2017-09-23 04:04:08
* 开发者：叶明(MSN:guming123416@hotmail.com,QQ:47043760)
* 修改者：
* 修改时间：
* 程序作用：
* 1、
* 2、
* 修改说明：
* 1、
* 2、
* 版本：@version 0.1
* @author 叶明
*/
@SuppressWarnings("serial")
@JsonIgnoreProperties({"adderid","adder","editer","editerid","addtime"})
public class SubjectOptionsDo  extends BaseTableEntry implements Serializable {				//题目选项
	
	private Long subjectid;   //试题编号|2|3|0
	private String optiionname;   //试题选项名称|1|1|1
	private String optioncontext;   //选项内容|1|1|1
	private Integer viewtimes;   //浏览次数|1|1|1

	public SubjectOptionsDo(){}

	public SubjectOptionsDo(Long id,Long subjectid,String optiionname,String optioncontext,Integer viewtimes,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.subjectid=subjectid;
		this.optiionname=optiionname;
		this.optioncontext=optioncontext;
		this.viewtimes=viewtimes;
		this.iorder=iorder;
		this.status=status;
		this.adderid=adderid;
		this.adder=adder;
		this.editer=editer;
		this.editerid=editerid;
		this.remark1=remark1;
		this.remark2=remark2;
		this.addtime=addtime;
		this.edittime=edittime;
		this.slock=slock;
	}

	/**
	 * 获取表名称
	 * @return
	 */
	public static String getTable() {
		return "t_subject_options";
	}

	/**
	* @return the subjectid
	*/
	public Long getSubjectid(){
		return subjectid;
	}

	/**
	* @param subjectid the subjectid to set
	*/
	public void setSubjectid(Long subjectid){
		this.subjectid = subjectid;
	}
	/**
	* @return the optiionname
	*/
	public String getOptiionname(){
		return optiionname;
	}

	/**
	* @param optiionname the optiionname to set
	*/
	public void setOptiionname(String optiionname){
		this.optiionname = StringUtils.trim(optiionname);
	}
	/**
	* @return the optioncontext
	*/
	public String getOptioncontext(){
		return optioncontext;
	}

	/**
	* @param optioncontext the optioncontext to set
	*/
	public void setOptioncontext(String optioncontext){
		this.optioncontext = StringUtils.trim(optioncontext);
	}
	/**
	* @return the viewtimes
	*/
	public Integer getViewtimes(){
		return viewtimes;
	}

	/**
	* @param viewtimes the viewtimes to set
	*/
	public void setViewtimes(Integer viewtimes){
		this.viewtimes = viewtimes;
	}
	
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