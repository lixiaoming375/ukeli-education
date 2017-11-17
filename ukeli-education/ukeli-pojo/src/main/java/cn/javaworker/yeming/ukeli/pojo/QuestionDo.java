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
* 创建日期：2017-10-18 02:44:07
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
public class QuestionDo  extends BaseTableEntry implements Serializable {				//
	
	private Long userid;   //用户编号|1|1|1
	private String question;   //问题内容|1|1|1
	private Short vtype;   //问题类型|1|1|1|1 直接问题  2 考试问题  3 杯赛问题 4 知识点  5 试题 
	private Long relationid;   //关联编号|2|3|1
	private String isanswer;   //是否回答|1|1|1| 0 未回答  1 已经回答
	private String answer;   //教师回复|1|1|1
	private Date answer_time;   //教师回答时间|1|1|1
	
	private String ext_realname;			//关联到用户昵称
	private String ext_vtype;
	private Date ext_addtime;

	public QuestionDo(){}

	public QuestionDo(Long id,Long userid,String question,Short vtype,Long relationid,String isanswer,String answer,Date answer_time,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.userid=userid;
		this.question=question;
		this.vtype=vtype;
		this.relationid=relationid;
		this.isanswer=isanswer;
		this.answer=answer;
		this.answer_time=answer_time;
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
		return "t_question";
	}

	/**
	* @return the userid
	*/
	public Long getUserid(){
		return userid;
	}

	/**
	* @param userid the userid to set
	*/
	public void setUserid(Long userid){
		this.userid = userid;
	}
	/**
	* @return the question
	*/
	public String getQuestion(){
		return question;
	}

	/**
	* @param question the question to set
	*/
	public void setQuestion(String question){
		this.question = StringUtils.trim(question);
	}
	/**
	* @return the vtype
	*/
	public Short getVtype(){
		return vtype;
	}

	/**
	* @param vtype the vtype to set
	*/
	public void setVtype(Short vtype){
		this.vtype = vtype;
	}
	/**
	* @return the relationid
	*/
	public Long getRelationid(){
		return relationid;
	}

	/**
	* @param relationid the relationid to set
	*/
	public void setRelationid(Long relationid){
		this.relationid = relationid;
	}
	/**
	* @return the isanswer
	*/
	public String getIsanswer(){
		return isanswer;
	}

	/**
	* @param isanswer the isanswer to set
	*/
	public void setIsanswer(String isanswer){
		this.isanswer = StringUtils.trim(isanswer);
	}
	/**
	* @return the answer
	*/
	public String getAnswer(){
		return answer;
	}

	/**
	* @param answer the answer to set
	*/
	public void setAnswer(String answer){
		this.answer = StringUtils.trim(answer);
	}
	/**
	* @return the answer_time
	*/
	public Date getAnswer_time(){
		return answer_time;
	}

	/**
	* @param answer_time the answer_time to set
	*/
	public void setAnswer_time(Date answer_time){
		this.answer_time = answer_time;
	}
	
    /**
	 * @return the ext_realname
	 */
	public String getExt_realname() {
		return ext_realname;
	}

	/**
	 * @param ext_realname the ext_realname to set
	 */
	public void setExt_realname(String ext_realname) {
		this.ext_realname = ext_realname;
	}

	
	public String getExt_vtype() {
		return ext_vtype;
	}

	public void setExt_vtype(String ext_vtype) {
		this.ext_vtype = ext_vtype;
	}

	public Date getExt_addtime() {
		return ext_addtime;
	}

	public void setExt_addtime(Date ext_addtime) {
		this.ext_addtime = ext_addtime;
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