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
/**
 * @author tengxiao
 *
 */
/**
 * @author tengxiao
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties({ "adderid", "adder", "editer", "editerid", "addtime" })
public class UserExamDo extends BaseTableEntry implements Serializable { //

	private Long userid; // 用户编号|1|1|1
	private Long examid; // 考试编号|1|1|1
	private Integer usetime; // 使用时间|1|2|1| 单位秒
	private Integer score; // 得分情况|1|1|1
	private Integer vtime; // 考试次数|1|1|1|我参与此次试卷的次数
	private String ext_userName;
	private String ext_examName;

	public UserExamDo() {
	}

	public UserExamDo(Long id, Long userid, Long examid,Integer usetime, Integer score, Integer vtime, Long iorder,
			Short status, Long adderid, String adder, String editer, Long editerid, String remark1, String remark2,
			Date addtime, Date edittime, Short slock) {
		this.id = id;
		this.userid = userid;
		this.examid = examid;
		this.usetime = usetime;
		this.score = score;
		this.vtime = vtime;
		this.iorder = iorder;
		this.status = status;
		this.adderid = adderid;
		this.adder = adder;
		this.editer = editer;
		this.editerid = editerid;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.addtime = addtime;
		this.edittime = edittime;
		this.slock = slock;
	}

	/**
	 * 获取表名称
	 * 
	 * @return
	 */
	public static String getTable() {
		return "t_user_exam";
	}

	/**
	 * @return the userid
	 */
	public Long getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(Long userid) {
		this.userid = userid;
	}

	/**
	 * @return the examid
	 */
	public Long getExamid() {
		return examid;
	}

	/**
	 * @param examid
	 *            the examid to set
	 */
	public void setExamid(Long examid) {
		this.examid = examid;
	}

	/**
	 * @return the usetime
	 */
	public Integer getUsetime() {
		return usetime;
	}

	/**
	 * @param usetime
	 *            the usetime to set
	 */
	public void setUsetime(Integer usetime) {
		this.usetime = usetime;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the vtime
	 */
	public Integer getVtime() {
		return vtime;
	}

	/**
	 * @param vtime
	 *            the vtime to set
	 */
	public void setVtime(Integer vtime) {
		this.vtime = vtime;
	}

	public String getExt_userName() {
		return ext_userName;
	}

	public void setExt_userName(String ext_userName) {
		this.ext_userName = ext_userName;
	}

	public String getExt_examName() {
		return ext_examName;
	}

	public void setExt_examName(String ext_examName) {
		this.ext_examName = ext_examName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.javaworker.yeming.pojo.Entry#hashCode()
	 */
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.javaworker.yeming.pojo.Entry#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj, false);
	}
}