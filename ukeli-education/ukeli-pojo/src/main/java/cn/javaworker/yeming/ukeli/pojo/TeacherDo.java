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
* 创建日期：2017-09-12 02:28:25
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
public class TeacherDo  extends BaseTableEntry implements Serializable {				//
	
	private String typename;   //科目名称|1|1|1
	private String name;   //名称|1|1|1
	private String detail;   //备注|2|3|0
	private String imageurl;   //图片地址|1|1|1

	public TeacherDo(){}

	public TeacherDo(Long id,String typename,String name,String detail,String imageurl,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.typename=typename;
		this.name=name;
		this.detail=detail;
		this.imageurl=imageurl;
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
		return "t_teacher";
	}

	/**
	* @return the typename
	*/
	public String getTypename(){
		return typename;
	}

	/**
	* @param typename the typename to set
	*/
	public void setTypename(String typename){
		this.typename = StringUtils.trim(typename);
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
	* @return the imageurl
	*/
	public String getImageurl(){
		return imageurl;
	}

	/**
	* @param imageurl the imageurl to set
	*/
	public void setImageurl(String imageurl){
		this.imageurl = StringUtils.trim(imageurl);
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