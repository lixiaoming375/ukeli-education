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
public class VideoDo  extends BaseTableEntry implements Serializable {				//视频
	
	private String videoname;   //视频名称|1|1|1
	private String videourl;   //视频地址
	private String videotype;   //视频类型|1|1|1|1 MP4  2 m3u8
	private String detail;   //视频描述
	private Integer videotime;   //视频时长|1|1|1|单位为妙

	public VideoDo(){}

	public VideoDo(Long id,String videoname,String videourl,String videotype,String detail,Integer videotime,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.videoname=videoname;
		this.videourl=videourl;
		this.videotype=videotype;
		this.detail=detail;
		this.videotime=videotime;
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
		return "t_video";
	}

	/**
	* @return the videoname
	*/
	public String getVideoname(){
		return videoname;
	}

	/**
	* @param videoname the videoname to set
	*/
	public void setVideoname(String videoname){
		this.videoname = StringUtils.trim(videoname);
	}
	/**
	* @return the videourl
	*/
	public String getVideourl(){
		return videourl;
	}

	/**
	* @param videourl the videourl to set
	*/
	public void setVideourl(String videourl){
		this.videourl = StringUtils.trim(videourl);
	}
	/**
	* @return the videotype
	*/
	public String getVideotype(){
		return videotype;
	}

	/**
	* @param videotype the videotype to set
	*/
	public void setVideotype(String videotype){
		this.videotype = StringUtils.trim(videotype);
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
	* @return the videotime
	*/
	public Integer getVideotime(){
		return videotime;
	}

	/**
	* @param videotime the videotime to set
	*/
	public void setVideotime(Integer videotime){
		this.videotime = videotime;
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