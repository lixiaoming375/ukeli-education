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
* 创建日期：2017-09-12 02:28:24
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
public class OrderDo  extends BaseTableEntry implements Serializable {				//订单产品
	
	private String ordernumber;   //订单号|1|1|1
	private Long userid;   //用户主键|2|3|0
	private String username;   //用户名|1|1|1
	private String nickname;   //用户昵称|1|1|1
	private Long productid;   //产品编号|2|3|0
	private String productname;   //产品名称|1|1|1
	private Integer amount;   //购买数量|1|1|1
	private Integer totalmarketprice;   //总市场价|1|1|1|
	private Integer totalprice;   //总价|1|1|1
	private Integer totalvipprice;   //
	private Integer payprice;   //支付价格|1|1|1
	private Integer couponprice;   //
	private Date ordertime;   //下单时间|1|1|1
	private Integer orderstatus;   //订单状态|1|3|0| 0 默认 1 已经审核 2 已经发货 3 用户收货 4 完单   9 用户取消
	private Short paystatus;   //支付状态|1|3|0| 0 未支付  1 已经支付  9 退款
	private Date paytime;   //支付时间|1|3|0
	private Short paytype;   //支付类型|1|3|0| 0 默认  1 支付宝  2 微信  3 银联  4 货到汇款 5 其他  9 未知
	private String payorder;   //支付订单号|1|3|0
	private String detail;   //备注|2|3|0

	public OrderDo(){}

	public OrderDo(Long id,String ordernumber,Long userid,String username,String nickname,Long productid,String productname,Integer amount,Integer totalmarketprice,Integer totalprice,Integer totalvipprice,Integer payprice,Integer couponprice,Date ordertime,Integer orderstatus,Short paystatus,Date paytime,Short paytype,String payorder,String detail,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.ordernumber=ordernumber;
		this.userid=userid;
		this.username=username;
		this.nickname=nickname;
		this.productid=productid;
		this.productname=productname;
		this.amount=amount;
		this.totalmarketprice=totalmarketprice;
		this.totalprice=totalprice;
		this.totalvipprice=totalvipprice;
		this.payprice=payprice;
		this.couponprice=couponprice;
		this.ordertime=ordertime;
		this.orderstatus=orderstatus;
		this.paystatus=paystatus;
		this.paytime=paytime;
		this.paytype=paytype;
		this.payorder=payorder;
		this.detail=detail;
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
		return "t_order";
	}

	/**
	* @return the ordernumber
	*/
	public String getOrdernumber(){
		return ordernumber;
	}

	/**
	* @param ordernumber the ordernumber to set
	*/
	public void setOrdernumber(String ordernumber){
		this.ordernumber = StringUtils.trim(ordernumber);
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
	* @return the productid
	*/
	public Long getProductid(){
		return productid;
	}

	/**
	* @param productid the productid to set
	*/
	public void setProductid(Long productid){
		this.productid = productid;
	}
	/**
	* @return the productname
	*/
	public String getProductname(){
		return productname;
	}

	/**
	* @param productname the productname to set
	*/
	public void setProductname(String productname){
		this.productname = StringUtils.trim(productname);
	}
	/**
	* @return the amount
	*/
	public Integer getAmount(){
		return amount;
	}

	/**
	* @param amount the amount to set
	*/
	public void setAmount(Integer amount){
		this.amount = amount;
	}
	/**
	* @return the totalmarketprice
	*/
	public Integer getTotalmarketprice(){
		return totalmarketprice;
	}

	/**
	* @param totalmarketprice the totalmarketprice to set
	*/
	public void setTotalmarketprice(Integer totalmarketprice){
		this.totalmarketprice = totalmarketprice;
	}
	/**
	* @return the totalprice
	*/
	public Integer getTotalprice(){
		return totalprice;
	}

	/**
	* @param totalprice the totalprice to set
	*/
	public void setTotalprice(Integer totalprice){
		this.totalprice = totalprice;
	}
	/**
	* @return the totalvipprice
	*/
	public Integer getTotalvipprice(){
		return totalvipprice;
	}

	/**
	* @param totalvipprice the totalvipprice to set
	*/
	public void setTotalvipprice(Integer totalvipprice){
		this.totalvipprice = totalvipprice;
	}
	/**
	* @return the payprice
	*/
	public Integer getPayprice(){
		return payprice;
	}

	/**
	* @param payprice the payprice to set
	*/
	public void setPayprice(Integer payprice){
		this.payprice = payprice;
	}
	/**
	* @return the couponprice
	*/
	public Integer getCouponprice(){
		return couponprice;
	}

	/**
	* @param couponprice the couponprice to set
	*/
	public void setCouponprice(Integer couponprice){
		this.couponprice = couponprice;
	}
	/**
	* @return the ordertime
	*/
	public Date getOrdertime(){
		return ordertime;
	}

	/**
	* @param ordertime the ordertime to set
	*/
	public void setOrdertime(Date ordertime){
		this.ordertime = ordertime;
	}
	/**
	* @return the orderstatus
	*/
	public Integer getOrderstatus(){
		return orderstatus;
	}

	/**
	* @param orderstatus the orderstatus to set
	*/
	public void setOrderstatus(Integer orderstatus){
		this.orderstatus = orderstatus;
	}
	/**
	* @return the paystatus
	*/
	public Short getPaystatus(){
		return paystatus;
	}

	/**
	* @param paystatus the paystatus to set
	*/
	public void setPaystatus(Short paystatus){
		this.paystatus = paystatus;
	}
	/**
	* @return the paytime
	*/
	public Date getPaytime(){
		return paytime;
	}

	/**
	* @param paytime the paytime to set
	*/
	public void setPaytime(Date paytime){
		this.paytime = paytime;
	}
	/**
	* @return the paytype
	*/
	public Short getPaytype(){
		return paytype;
	}

	/**
	* @param paytype the paytype to set
	*/
	public void setPaytype(Short paytype){
		this.paytype = paytype;
	}
	/**
	* @return the payorder
	*/
	public String getPayorder(){
		return payorder;
	}

	/**
	* @param payorder the payorder to set
	*/
	public void setPayorder(String payorder){
		this.payorder = StringUtils.trim(payorder);
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