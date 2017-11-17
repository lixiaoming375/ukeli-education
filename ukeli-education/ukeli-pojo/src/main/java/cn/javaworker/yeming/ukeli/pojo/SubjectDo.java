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
* 创建日期：2017-09-04 11:03:02
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
public class SubjectDo  extends BaseTableEntry implements Serializable {				//题目
	
	private int stype;   //试题类型|1|1|1| 1 单选 2 多选  3 填空
	private String name;   //题目名称
	private String content;   //题目内容
	private String answer;   //答案
	private String analysis;   //试题解析|1|1|1
	private String imgurl;   //题目图片
	private Integer viewtimes;   //浏览次数|1|1|1
	private Long studytimes;   //学习次数|1|1|1
	private String detail;   //备注信息|1|1|1
	private String videopath;   //视频路径
	private String  ext_stype; //true试题类型
	private String  ext_isbind;//是否绑定
	private int ext_serial;//序号
	private int ext_score;//绑定时绑定的分数
	private String ext_vtype;
	private String ext_isdone;

	public SubjectDo(){}

	public SubjectDo(Long id,int stype,String name,String content,String answer,String analysis,String imgurl,Integer viewtimes,Long studytimes,String detail,String videopath,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.stype=stype;
		this.name=name;
		this.content=content;
		this.answer=answer;
		this.analysis=analysis;
		this.imgurl=imgurl;
		this.viewtimes=viewtimes;
		this.studytimes=studytimes;
		this.detail=detail;
		this.videopath=videopath;
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
		return "t_subject";
	}


	public int getStype() {
		return stype;
	}

	public void setStype(int stype) {
		this.stype = stype;
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
	* @return the content
	*/
	public String getContent(){
		return content;
	}

	/**
	* @param content the content to set
	*/
	public void setContent(String content){
		this.content = StringUtils.trim(content);
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
	* @return the analysis
	*/
	public String getAnalysis(){
		return analysis;
	}

	/**
	* @param analysis the analysis to set
	*/
	public void setAnalysis(String analysis){
		this.analysis = StringUtils.trim(analysis);
	}
	/**
	* @return the imgurl
	*/
	public String getImgurl(){
		return imgurl;
	}

	/**
	* @param imgurl the imgurl to set
	*/
	public void setImgurl(String imgurl){
		this.imgurl = imgurl;
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
	/**
	* @return the studytimes
	*/
	public Long getStudytimes(){
		return studytimes;
	}

	/**
	* @param studytimes the studytimes to set
	*/
	public void setStudytimes(Long studytimes){
		this.studytimes = studytimes;
	}
	/**
	* @return the detail
	*/
	public String getDetail(){
		return detail;
	}

	/**
	* @param detail the detail to set
	*/
	public void setDetail(String detail){
		this.detail = StringUtils.trim(detail);
	}
	/**
	* @return the videopath
	*/
	public String getVideopath(){
		return videopath;
	}

	/**
	* @param videopath the videopath to set
	*/
	public void setVideopath(String videopath){
		this.videopath = StringUtils.trim(videopath);
	}
	
	
    public String getExt_stype() {
		return ext_stype;
	}

	public void setExt_stype(String ext_stype) {
		this.ext_stype = ext_stype;
	}

	
	
	public String getExt_isbind() {
		return ext_isbind;
	}

	public void setExt_isbind(String ext_isbind) {
		this.ext_isbind = ext_isbind;
	}
	

	public int getExt_serial() {
		return ext_serial;
	}

	public void setExt_serial(int ext_serial) {
		this.ext_serial = ext_serial;
	}

	public String getExt_vtype() {
		return ext_vtype;
	}

	public void setExt_vtype(String ext_vtype) {
		this.ext_vtype = ext_vtype;
	}
	
	public int getExt_score() {
		return ext_score;
	}

	public void setExt_score(int ext_score) {
		this.ext_score = ext_score;
	}

	public String getExt_isdone() {
		return ext_isdone;
	}

	public void setExt_isdone(String ext_isdone) {
		this.ext_isdone = ext_isdone;
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