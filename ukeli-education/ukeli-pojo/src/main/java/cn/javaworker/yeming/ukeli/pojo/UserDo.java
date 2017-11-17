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
public class UserDo  extends BaseTableEntry implements Serializable {				//用户信息
	
	private String username;   //用户名|1|1|1
	private String password;   //密码|1|1|1
	private String nickname;   //昵称|1|1|1|用户昵称
	private String realname;   //真实姓名|1|1|1
	private String bidthday;   //出生年月|1|1|1
	private String sex;   //性别|1|1|1| 1男 2 女 其他为未知
	private String telephone;   //联系电话|1|1|1
	private String mobile;   //手机号码|1|1|1
	private String email;   //电子邮件|1|1|1
	private String address;   //地址信息|1|1|1
	private String postcode;   //邮编|2|1|1
	private String firstletter;   //首拼字母|2|3|0
	private String spell;   //拼音|2|3|0
	private String allspell;   //全拼|2|3|0
	private String detail;   //备注|2|1|7
	private String wechat;   //微信号|2|3|0
	private String qq;   //qq号码|2|3|0
	private String headpic;   //头像地址|2|3|0
	private String vcode;   //推广编号|2|3|0
	private String inventcode;   //邀请码|2|3|0
	private Integer logintimes;   //登陆次数|2|3|0
	private Date lastlogintime;   //最后登录时间|2|3|0
	private String lastloginip;   //最后登录IP|2|3|0
	private String platform;   //注册平台|2|3|0|1iphone 2 android 3 wechat 4 web 5 ipad 6 androidpad 9 其他
	private String isvip;   //是否vip会员|1|3|0| 0 不是  1 是
	private Date vipstarttime;   //vip开始时间|2|3|0
	private Date vipendtime;   //vip结束时间|1|1|1
	private Date ext_addtime;

	public UserDo(){}

	public UserDo(Long id,String username,String password,String nickname,String realname,String bidthday,String sex,String telephone,String mobile,String email,String address,String postcode,String firstletter,String spell,String allspell,String detail,String wechat,String qq,String headpic,String vcode,String inventcode,Integer logintimes,Date lastlogintime,String lastloginip,String platform,String isvip,Date vipstarttime,Date vipendtime,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.username=username;
		this.password=password;
		this.nickname=nickname;
		this.realname=realname;
		this.bidthday=bidthday;
		this.sex=sex;
		this.telephone=telephone;
		this.mobile=mobile;
		this.email=email;
		this.address=address;
		this.postcode=postcode;
		this.firstletter=firstletter;
		this.spell=spell;
		this.allspell=allspell;
		this.detail=detail;
		this.wechat=wechat;
		this.qq=qq;
		this.headpic=headpic;
		this.vcode=vcode;
		this.inventcode=inventcode;
		this.logintimes=logintimes;
		this.lastlogintime=lastlogintime;
		this.lastloginip=lastloginip;
		this.platform=platform;
		this.isvip=isvip;
		this.vipstarttime=vipstarttime;
		this.vipendtime=vipendtime;
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
		return "t_user";
	}

	/**
	* @return the username
	*/
	public String getUsername(){
		return username;
	}

	/**
	* @param username the username to set
	*/
	public void setUsername(String username){
		this.username = StringUtils.trim(username);
	}
	/**
	* @return the password
	*/
	public String getPassword(){
		return password;
	}

	/**
	* @param password the password to set
	*/
	public void setPassword(String password){
		this.password = StringUtils.trim(password);
	}
	/**
	* @return the nickname
	*/
	public String getNickname(){
		return nickname;
	}

	/**
	* @param nickname the nickname to set
	*/
	public void setNickname(String nickname){
		this.nickname = StringUtils.trim(nickname);
	}
	/**
	* @return the realname
	*/
	public String getRealname(){
		return realname;
	}

	/**
	* @param realname the realname to set
	*/
	public void setRealname(String realname){
		this.realname = StringUtils.trim(realname);
	}
	/**
	* @return the bidthday
	*/
	public String getBidthday(){
		return bidthday;
	}

	/**
	* @param bidthday the bidthday to set
	*/
	public void setBidthday(String bidthday){
		this.bidthday = bidthday;
	}
	/**
	* @return the sex
	*/
	public String getSex(){
		return sex;
	}

	/**
	* @param sex the sex to set
	*/
	public void setSex(String sex){
		this.sex = StringUtils.trim(sex);
	}
	/**
	* @return the telephone
	*/
	public String getTelephone(){
		return telephone;
	}

	/**
	* @param telephone the telephone to set
	*/
	public void setTelephone(String telephone){
		this.telephone = StringUtils.trim(telephone);
	}
	/**
	* @return the mobile
	*/
	public String getMobile(){
		return mobile;
	}

	/**
	* @param mobile the mobile to set
	*/
	public void setMobile(String mobile){
		this.mobile = StringUtils.trim(mobile);
	}
	/**
	* @return the email
	*/
	public String getEmail(){
		return email;
	}

	/**
	* @param email the email to set
	*/
	public void setEmail(String email){
		this.email = StringUtils.trim(email);
	}
	/**
	* @return the address
	*/
	public String getAddress(){
		return address;
	}

	/**
	* @param address the address to set
	*/
	public void setAddress(String address){
		this.address = StringUtils.trim(address);
	}
	/**
	* @return the postcode
	*/
	public String getPostcode(){
		return postcode;
	}

	/**
	* @param postcode the postcode to set
	*/
	public void setPostcode(String postcode){
		this.postcode = StringUtils.trim(postcode);
	}
	/**
	* @return the firstletter
	*/
	public String getFirstletter(){
		return firstletter;
	}

	/**
	* @param firstletter the firstletter to set
	*/
	public void setFirstletter(String firstletter){
		this.firstletter = StringUtils.trim(firstletter);
	}
	/**
	* @return the spell
	*/
	public String getSpell(){
		return spell;
	}

	/**
	* @param spell the spell to set
	*/
	public void setSpell(String spell){
		this.spell = StringUtils.trim(spell);
	}
	/**
	* @return the allspell
	*/
	public String getAllspell(){
		return allspell;
	}

	/**
	* @param allspell the allspell to set
	*/
	public void setAllspell(String allspell){
		this.allspell = StringUtils.trim(allspell);
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
	* @return the wechat
	*/
	public String getWechat(){
		return wechat;
	}

	/**
	* @param wechat the wechat to set
	*/
	public void setWechat(String wechat){
		this.wechat = StringUtils.trim(wechat);
	}
	/**
	* @return the qq
	*/
	public String getQq(){
		return qq;
	}

	/**
	* @param qq the qq to set
	*/
	public void setQq(String qq){
		this.qq = StringUtils.trim(qq);
	}
	/**
	* @return the headpic
	*/
	public String getHeadpic(){
		return headpic;
	}

	/**
	* @param headpic the headpic to set
	*/
	public void setHeadpic(String headpic){
		this.headpic = StringUtils.trim(headpic);
	}
	/**
	* @return the vcode
	*/
	public String getVcode(){
		return vcode;
	}

	/**
	* @param vcode the vcode to set
	*/
	public void setVcode(String vcode){
		this.vcode = StringUtils.trim(vcode);
	}
	/**
	* @return the inventcode
	*/
	public String getInventcode(){
		return inventcode;
	}

	/**
	* @param inventcode the inventcode to set
	*/
	public void setInventcode(String inventcode){
		this.inventcode = StringUtils.trim(inventcode);
	}
	/**
	* @return the logintimes
	*/
	public Integer getLogintimes(){
		return logintimes;
	}

	/**
	* @param logintimes the logintimes to set
	*/
	public void setLogintimes(Integer logintimes){
		this.logintimes = logintimes;
	}
	/**
	* @return the lastlogintime
	*/
	public Date getLastlogintime(){
		return lastlogintime;
	}

	/**
	* @param lastlogintime the lastlogintime to set
	*/
	public void setLastlogintime(Date lastlogintime){
		this.lastlogintime = lastlogintime;
	}
	/**
	* @return the lastloginip
	*/
	public String getLastloginip(){
		return lastloginip;
	}

	/**
	* @param lastloginip the lastloginip to set
	*/
	public void setLastloginip(String lastloginip){
		this.lastloginip = StringUtils.trim(lastloginip);
	}
	/**
	* @return the platform
	*/
	public String getPlatform(){
		return platform;
	}

	/**
	* @param platform the platform to set
	*/
	public void setPlatform(String platform){
		this.platform = StringUtils.trim(platform);
	}
	/**
	* @return the isvip
	*/
	public String getIsvip(){
		return isvip;
	}

	/**
	* @param isvip the isvip to set
	*/
	public void setIsvip(String isvip){
		this.isvip = StringUtils.trim(isvip);
	}
	/**
	* @return the vipstarttime
	*/
	public Date getVipstarttime(){
		return vipstarttime;
	}

	/**
	* @param vipstarttime the vipstarttime to set
	*/
	public void setVipstarttime(Date vipstarttime){
		this.vipstarttime = vipstarttime;
	}
	/**
	* @return the vipendtime
	*/
	public Date getVipendtime(){
		return vipendtime;
	}

	/**
	* @param vipendtime the vipendtime to set
	*/
	public void setVipendtime(Date vipendtime){
		this.vipendtime = vipendtime;
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