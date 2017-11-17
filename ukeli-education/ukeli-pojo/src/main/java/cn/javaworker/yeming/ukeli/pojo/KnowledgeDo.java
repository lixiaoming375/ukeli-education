package cn.javaworker.yeming.ukeli.pojo;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cn.javaworker.yeming.pojo.BaseTableEntry;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"adderid","adder","editer","editerid","addtime"})
public class KnowledgeDo  extends BaseTableEntry implements Serializable {

	private Long typeid; //知识点类型编号
	private Long gradeid; //年级id
	private String nodename; //知识点名称
	private String remark; //知识点介绍
	private int  totalexam; //总试题数量
	private String videopath;
	private String videodetail;
	
	private String ext_grade;//所属年级
	private String ext_knowtype;//知识点类型
	private String ext_videourl;//视频url
	
	public KnowledgeDo(){}
	
	public KnowledgeDo(Long id,Long typeid,Long gradeid,String nodename,String remark,int totalexam,String videopath,String videodetail,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.typeid=typeid;
		this.gradeid=gradeid;
		this.nodename=nodename;
		this.remark=remark;
		this.totalexam=totalexam;
		this.videopath=videopath;
		this.videodetail=videodetail;
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
		return "t_knowledge";
	}
	
	
	 public Long getTypeid() {
		return typeid;
	}

	public void setTypeid(Long typeid) {
		this.typeid = typeid;
	}

	public Long getGradeid() {
		return gradeid;
	}

	public void setGradeid(Long gradeid) {
		this.gradeid = gradeid;
	}

	public String getNodename() {
		return nodename;
	}

	public void setNodename(String nodename) {
		this.nodename = nodename;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getTotalexam() {
		return totalexam;
	}

	public void setTotalexam(int totalexam) {
		this.totalexam = totalexam;
	}
	
	

	public String getVideopath() {
		return videopath;
	}

	public void setVideopath(String videopath) {
		this.videopath = videopath;
	}

	public String getVideodetail() {
		return videodetail;
	}

	public void setVideodetail(String videodetail) {
		this.videodetail = videodetail;
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

		public String getExt_grade() {
			return ext_grade;
		}

		public void setExt_grade(String ext_grade) {
			this.ext_grade = ext_grade;
		}

		public String getExt_videourl() {
			return ext_videourl;
		}

		public void setExt_videourl(String ext_videourl) {
			this.ext_videourl = ext_videourl;
		}

		public String getExt_knowtype() {
			return ext_knowtype;
		}

		public void setExt_knowtype(String ext_knowtype) {
			this.ext_knowtype = ext_knowtype;
		}
		
	
		
}
