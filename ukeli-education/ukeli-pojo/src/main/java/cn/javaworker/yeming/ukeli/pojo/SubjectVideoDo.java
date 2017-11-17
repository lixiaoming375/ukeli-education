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

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.javaworker.yeming.pojo.BaseTableEntry;
/**
* 创建日期：2017-08-23 06:57:30
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
public class SubjectVideoDo  extends BaseTableEntry implements Serializable {				//
	
	private Long subjectid;   //试题编号|1|1|1
	private Long videoid;   //视频编号|1|1|1

	public SubjectVideoDo(){}

	public SubjectVideoDo(Long id,Long subjectid,Long videoid){
		this.id=id;
		this.subjectid=subjectid;
		this.videoid=videoid;
	}

	/**
	 * 获取表名称
	 * @return
	 */
	public static String getTable() {
		return "t_subject_video";
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
	* @return the videoid
	*/
	public Long getVideoid(){
		return videoid;
	}

	/**
	* @param videoid the videoid to set
	*/
	public void setVideoid(Long videoid){
		this.videoid = videoid;
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