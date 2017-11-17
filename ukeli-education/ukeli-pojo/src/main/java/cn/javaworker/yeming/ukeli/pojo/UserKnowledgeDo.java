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
* 创建日期：2017-10-24 10:41:59
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
public class UserKnowledgeDo  extends BaseTableEntry implements Serializable {				//
	
	private Long userid;   //用户编号|1|1|1
	private Long knowledgeid;   //知识点编号|1|1|1
	private String isdone;   //是否答题|1|1|1|0 未完成  1 已经完成
	private String isadd; //是否加入我的课程 0 未加入 1 已加入
    private String ext_username;
    private String ext_nickname;
    private String ext_nodename;
	public UserKnowledgeDo(){}

	public UserKnowledgeDo(Long id,Long userid,Long knowledgeid,String isdone,String isadd,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.userid=userid;
		this.knowledgeid=knowledgeid;
		this.isdone=isdone;
		this.isadd=isadd;
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
		return "t_user_knowledge";
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
	* @return the knowledgeid
	*/
	public Long getKnowledgeid(){
		return knowledgeid;
	}

	/**
	* @param knowledgeid the knowledgeid to set
	*/
	public void setKnowledgeid(Long knowledgeid){
		this.knowledgeid = knowledgeid;
	}
	/**
	* @return the isdone
	*/
	public String getIsdone(){
		return isdone;
	}
	
	public String getIsadd() {
		return isadd;
	}

	public void setIsadd(String isadd) {
		this.isadd = isadd;
	}

	/**
	* @param isdone the isdone to set
	*/
	public void setIsdone(String isdone){
		this.isdone = StringUtils.trim(isdone);
	}
	
	
	
    public String getExt_username() {
		return ext_username;
	}

	public void setExt_username(String ext_username) {
		this.ext_username = ext_username;
	}

	public String getExt_nickname() {
		return ext_nickname;
	}

	public void setExt_nickname(String ext_nickname) {
		this.ext_nickname = ext_nickname;
	}

	public String getExt_nodename() {
		return ext_nodename;
	}

	public void setExt_nodename(String ext_nodename) {
		this.ext_nodename = ext_nodename;
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