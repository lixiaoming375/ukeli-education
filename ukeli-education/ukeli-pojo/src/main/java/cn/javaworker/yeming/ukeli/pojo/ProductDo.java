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
public class ProductDo  extends BaseTableEntry implements Serializable {				//产品名称
	
	private String productname;   //产品名称|1|1|1
	private String ident;   //唯一标识|2|1|1
	private String detail;   //简介|2||1|7
	private Integer marketprice;   //市场价|1|1|1|单位为分
	private Integer price;   //商城价|1|1|1|单位为分
	private Integer vipprice;   //VIP会员价|1|1|1|单位为分
	private Short producttype;   //类型|1|1|2|产品类型 1  绑定的全部商品  2 单个视频，其中标示，在订单中
	private Short datetype;   //日期类型|1|1|2|1 年 2 月  3 日 4 小时  5 分 6 秒
	private Integer datecount;   //日期数量|1|1|2|日期增加数量

	public ProductDo(){}

	public ProductDo(Long id,String productname,String ident,String detail,Integer marketprice,Integer price,Integer vipprice,Short producttype,Short datetype,Integer datecount,Long iorder,Short status,Long adderid,String adder,String editer,Long editerid,String remark1,String remark2,Date addtime,Date edittime,Short slock){
		this.id=id;
		this.productname=productname;
		this.ident=ident;
		this.detail=detail;
		this.marketprice=marketprice;
		this.price=price;
		this.vipprice=vipprice;
		this.producttype=producttype;
		this.datetype=datetype;
		this.datecount=datecount;
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
		return "t_product";
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
	* @return the ident
	*/
	public String getIdent(){
		return ident;
	}

	/**
	* @param ident the ident to set
	*/
	public void setIdent(String ident){
		this.ident = StringUtils.trim(ident);
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
	* @return the marketprice
	*/
	public Integer getMarketprice(){
		return marketprice;
	}

	/**
	* @param marketprice the marketprice to set
	*/
	public void setMarketprice(Integer marketprice){
		this.marketprice = marketprice;
	}
	/**
	* @return the price
	*/
	public Integer getPrice(){
		return price;
	}

	/**
	* @param price the price to set
	*/
	public void setPrice(Integer price){
		this.price = price;
	}
	/**
	* @return the vipprice
	*/
	public Integer getVipprice(){
		return vipprice;
	}

	/**
	* @param vipprice the vipprice to set
	*/
	public void setVipprice(Integer vipprice){
		this.vipprice = vipprice;
	}
	/**
	* @return the producttype
	*/
	public Short getProducttype(){
		return producttype;
	}

	/**
	* @param producttype the producttype to set
	*/
	public void setProducttype(Short producttype){
		this.producttype = producttype;
	}
	/**
	* @return the datetype
	*/
	public Short getDatetype(){
		return datetype;
	}

	/**
	* @param datetype the datetype to set
	*/
	public void setDatetype(Short datetype){
		this.datetype = datetype;
	}
	/**
	* @return the datecount
	*/
	public Integer getDatecount(){
		return datecount;
	}

	/**
	* @param datecount the datecount to set
	*/
	public void setDatecount(Integer datecount){
		this.datecount = datecount;
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