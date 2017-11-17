
$(function(){
	pages(1);
})


function pages(page){
		var vtype=$("#vtype").val();
	    Eymjs.data.ajax(Eymjs.url.question.LIST,{vtype:vtype,page:page},function (data) {
	        if(data.success){
	           var questionhtml='';
	              for(var i=0;i<(data.obj.list).length;i++){
		              var isanswer=data.obj.list[i].isanswer;
		              questionhtml=questionhtml+'<li>'+
		                    '<div class="q-box" style="min-hight:100px;"> '+
		                       ' <h4 class="fwb t-blue">'+data.obj.list[i].ext_realname+'</h4>'+
		                        '<p>'+data.obj.list[i].question+'</p>'+
		                    '</div>'
		             if(isanswer==1){
		                     questionhtml=questionhtml+'<div class="q-box" style="min-hight:100px;">'+
		                       '<h4 class="fwb t-blue">老师回答：</h4>'+
		                       ' <p>'+data.obj.list[i].answer+'</p>'+
		                    '</div>'+
			                   '</li>';
		                  }
	               }
	              $("#questionul").html(questionhtml);
	              laypage({
	            	    cont: 'pageNav', //注意，这里的 pageNav 是 ID，不用加 # 号
	            	    pages: data.obj.totalPage, //总页数  
	            	    curr:data.obj.currentPage, //当前页 
	            	    jump:function(e,first){  
	            	        if(!first){  
	            	        	pages(e.curr);  
	            	        } 
	            	    }  
	            	  });
		        
	        }else{
	        }
	   });
	}

/**
 * 保存咨询问题
 */
function saveQuestion() {

    var question = $("#question").val();
    var vtype=$("#vtype").val();
    if(!question) {
        Eymjs.message.error("对不起，请输入您的问题！");
        return
    }
    Eymjs.data.ajax(Eymjs.url.question.SAVE,{question:question,vtype:vtype},function (data) {
        if(data.success){
            Eymjs.message.success(data.errorinfo,webPath + "question.html");
            return;
        }else{
            Eymjs.message.error(data.errorinfo);
        }
    });
}




