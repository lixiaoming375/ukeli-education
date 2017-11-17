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
                                <input  type="text" name="productname"  id="productname" readonly class="col-sm-11" value="${product.productname!}"/>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="producttype"> 产品类型: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input type="text" name="producttype"  id="producttype" readonly class="col-sm-11" value="${product.producttype!}"/>
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
                                <input type="text" name="ident"  id="ident" readonly class="col-sm-11" value="${product.ident!}"/>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="marketprice"> 市场价: </label>
                        <div class="col-sm-4">
                            <div class="input-group">
                              <span class="input-group-addon">¥</span>
					          <input type="text" name="marketprice"  id="marketprice" readonly class="col-sm-11 form-control" value="${product.marketprice!}"/>
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
					          <input type="text" name="price"  id="price" readonly class="col-sm-11 form-control" value="${product.price!}"/>
					          <span class="input-group-addon">.00</span>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="vipprice"> VIP会员价: </label>
                        <div class="col-sm-4">
                            <div class="input-group">
                              <span class="input-group-addon">¥</span>
					          <input type="text" name="vipprice"  id="vipprice" readonly class="col-sm-11 form-control" value="${product.vipprice!}"/>
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
                                <input type="text" name="datetype"  id="datetype" readonly class="col-sm-11" value="${product.datetype!}"/>
                            </div>
                        </div>
                        <label class="col-sm-2 control-label no-padding-right" for="datecount"> 日期数量: </label>
                        <div class="col-sm-4">
                            <div class="clearfix">
                                <input type="text" name="datecount"  id="datecount" readonly class="col-sm-11" value="${product.datecount!}"/>
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
 			
            <input name="id" id="id" type="hidden" value="${product.id}" />
        </form>

    </div>
</div>


<script src="${adminPath}/res/js/product/product.edit.js?ver=${version}"></script>



