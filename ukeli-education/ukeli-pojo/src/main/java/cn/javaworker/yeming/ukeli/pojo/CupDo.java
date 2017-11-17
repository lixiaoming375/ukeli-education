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
* 创建日期：2017-09-08 02:05:56
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
public class CupDo  extends BaseTableEntry implements Serializable {				//
	
	private String name;   //杯赛名称
	private Long gradeid;   //年级id|外键
	private Long cuptypeid;
	private Integer times;   //考试时长
	private Integer score;   //分数
	private Integer totalsubject;   //总试题数量|2|3|0
	private String remark;   //杯赛介绍
	private String ext_grade;
	private String ext_cuptype;

	public CupDo(){}

	public CupDo(Long id,String name,Long gradeid,Long cuptypeid,Integer times,Integer score,Integer totalsubject,String remark,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.name=name;
		this.gradeid=gradeid;
		this.cuptypeid=cuptypeid;
		this.times=times;
		this.score=score;
		this.totalsubject=totalsubject;
		this.remark=remark;
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
		return "t_cup";
	}

	/**
	* @return the name
	*/
	public String getName(){
		return name;
	}

	/**
	* @param name the name to set
	*/
	public void setName(String name){
		this.name = StringUtils.trim(name);
	}
	/**
	* @return the gradeid
	*/
	public Long getGradeid(){
		return gradeid;
	}

	/**
	* @param gradeid the gradeid to set
	*/
	public void setGradeid(Long gradeid){
		this.gradeid = gradeid;
	}
	/**
	* @return the times
	*/
	public Integer getTimes(){
		return times;
	}

	/**
	* @param times the times to set
	*/
	public void setTimes(Integer times){
		this.times = times;
	}
	/**
	* @return the score
	*/
	public Integer getScore(){
		return score;
	}

	/**
	* @param score the score to set
	*/
	public void setScore(Integer score){
		this.score = score;
	}
	/**
	* @return the totalsubject
	*/
	public Integer getTotalsubject(){
		return totalsubject;
	}

	/**
	* @param totalsubject the totalsubject to set
	*/
	public void setTotalsubject(Integer totalsubject){
		this.totalsubject = totalsubject;
	}
	/**
	* @return the remark
	*/
	public String getRemark(){
		return remark;
	}

	/**
	* @param remark the remark to set
	*/
	public void setRemark(String remark){
		this.remark = StringUtils.trim(remark);
	}
	
	
    public String getExt_grade() {
		return ext_grade;
	}

	public void setExt_grade(String ext_grade) {
		this.ext_grade = ext_grade;
	}
	
	

	public Long getCuptypeid() {
		return cuptypeid;
	}

	public void setCuptypeid(Long cuptypeid) {
		this.cuptypeid = cuptypeid;
	}

	public String getExt_cuptype() {
		return ext_cuptype;
	}

	public void setExt_cuptype(String ext_cuptype) {
		this.ext_cuptype = ext_cuptype;
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