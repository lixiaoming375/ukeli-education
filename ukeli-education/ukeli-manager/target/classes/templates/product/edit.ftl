<div class="row margin0">
    <div class="col-sm-12">
        <div class="space-2"></div>
        <form class="form-horizontal"  id="productForm" method="post" action="${adminPath}/product/save.json">
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="productname"> 产品名称: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input  type="text" name="productname"  id="productname" class="col-sm-11" value="${product.productname!}"/>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="producttype"> 产品类型: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input type="text" name="producttype"  id="producttype" class="col-sm-11" value="${product.producttype!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="ident"> 唯一标识: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input type="text" name="ident"  id="ident" class="col-sm-11" value="${product.ident!}"/>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="marketprice"> 市场价: </label>
                        <div class="col-sm-4">
                            <div class="input-group">
                              <span class="input-group-addon">¥</span>
					          <input type="text" name="marketprice"  id="marketprice" class="col-sm-11 form-control" value="${product.marketprice!}"/>
					          <span class="input-group-addon">.00</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="price"> 商城价: </label>
                        <div class="col-sm-4">
                            <div class="input-group">
                              <span class="input-group-addon">¥</span>
					          <input type="text" name="price"  id="price" class="col-sm-11 form-control" value="${product.price!}"/>
					          <span class="input-group-addon">.00</span>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="vipprice"> VIP会员价: </label>
                        <div class="col-sm-4">
                            <div class="input-group">
                              <span class="input-group-addon">¥</span>
					          <input type="text" name="vipprice"  id="vipprice" class="col-sm-11 form-control" value="${product.vipprice!}"/>
					          <span class="input-group-addon">.00</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>
 			 <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                    
                        <label class="col-sm-2 control-label no-padding-right" for="datetype"> 日期类型: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                               <select name="datetype"  id="datetype"  data-placeholder="日期类型" style="width: 180px;vertical-align:middle;" aria-invalid="false" class="help-block m-b-none" >
			                        <option value='' >----请选择----</option>
			                        <option value='1'  <#if product.datetype ?? && product.datetype ==1  >selected="selected"</#if> >年</option>
			                        <option value='2'  <#if product.datetype ?? && product.datetype ==2  >selected="selected"</#if> >月</option>
			                        <option value='3'  <#if product.datetype ?? && product.datetype ==3  >selected="selected"</#if> >日</option>
			                        <option value='4'  <#if product.datetype ?? && product.datetype ==4  >selected="selected"</#if> >时</option>
			                        <option value='5'  <#if product.datetype ?? && product.datetype ==5  >selected="selected"</#if> >分</option>
			                        <option value='6'  <#if product.datetype ?? && product.datetype ==6  >selected="selected"</#if> >秒</option>
								</select>	
                            </div>
                        </div>
                    
                        <label class="col-sm-2 control-label no-padding-right" for="datecount"> 日期数量: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input type="text" name="datecount"  id="datecount" class="col-sm-11" value="${product.datecount!}"/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
    
            <div class="space-2"></div>
             <div class="row">
                <div class="col-sm-12">
                    <div class="form-group">
                        <label class="col-sm-2 control-label no-padding-right" for="detail"> 简介: </label>
                        <div class="col-sm-10">
                            <div class="clearfix">
                                <textarea  id="detail" rows="4" class="col-sm-12" name="detail">${product.detail!} </textarea>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="space-2"></div>

            <div class="clearfix form-actions">
                <div class="col-md-offset-4 col-md-8">
                    <button class="btn btn-info" type="submit"><i class="ace-icon fa fa-check bigger-110"></i>保存</button>
                    &nbsp; &nbsp; &nbsp;
                    <button class="btn" type="reset"><i class="ace-icon fa fa-undo bigger-110"></i>重置</button>
                </div>
            </div>
             	<input name="id" type="hidden" value="${product.id!}">
        </form>

    </div>
</div>

<script src="${adminPath}/res/js/product/product.edit.js?ver=${version}"></script>
