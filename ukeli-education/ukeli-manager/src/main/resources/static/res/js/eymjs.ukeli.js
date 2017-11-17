/**
 *
 * @type {{}}
 */
Eymjs.ukeli ={
    url : {
        role :{
            LIST :adminPath+'/manager/role/list.json',
            EDIT :adminPath+'/manager/role/edit.html',
            DELETE :adminPath+'/manager/role/del.json',
            STATUS :adminPath+'/manager/role/status.json',
            PERMISSION :adminPath+'/manager/role/permission.json',
            ORDER : adminPath+'/manager/role/order.json'
        },
        permission : {
            LIST :adminPath+'/manager/permission/list.json',
            EDIT :adminPath+'/manager/permission/edit.html',
            DELETE :adminPath+'/manager/permission/del.json',
            STATUS :adminPath+'/manager/permission/status.json',
            ORDER : adminPath+'/manager/permission/order.json'
        },
        admin :{
            LIST :adminPath+'/manager/admin/list.json',
            EDIT :adminPath+'/manager/admin/edit.html',
            DELETE :adminPath+'/manager/admin/del.json',
            STATUS :adminPath+'/manager/admin/status.json',
            PASSWORD :adminPath+'/manager/admin/password.html',
            ORDER : adminPath+'/manager/permission/order.json'
        },
        clazz :{
            LIST :adminPath+'/manager/clazz/list.json',
            TREETABLE :adminPath+'/manager/clazz/treetable.html',
            EDIT :adminPath+'/manager/clazz/edit.html',
            DELETE :adminPath+'/manager/clazz/del.json',
            STATUS :adminPath+'/manager/clazz/status.json',
            ORDER :adminPath+'/manager/clazz/order.json',
            TABEL_TREE : adminPath+'/manager/clazz/tree/list.json'
        },
        clazzttype :{
            LIST :adminPath+'/manager/clazztype/list.json',
            EDIT :adminPath+'/manager/clazztype/edit.html',
            DELETE :adminPath+'/manager/clazztype/del.json',
            ORDER :adminPath+'/manager/clazztype/order.json',
            STATUS :adminPath+'/manager/clazztype/status.json'
        },
        knowledge :{
            LIST :adminPath+'/knowledge/list.json',
            SHOW :adminPath+'/knowledge/show.html',
            EDIT :adminPath+'/knowledge/edit.html',
            DELETE :adminPath+'/knowledge/del.json',
            ORDERSERIAL :adminPath+'/knowledge/orderserial.json',
            STATUS :adminPath+'/knowledge/status.json',
            BINDDIALOG :adminPath+'/knowledge/bindDialog.html',
            BINDBYID:adminPath+'/knowledge/bindSubject.json',
            CANCELBIND:adminPath+'/knowledge/cancelBind.json',
            BINDADDSCORE:adminPath+'/knowledge/bindAddScore.html',
            VIDEO :adminPath+'/knowledge/video.html',
        },
        knowledgetype :{
        	LIST :adminPath+'/knowledge/knowledgetype/list.json',
        	EDIT :adminPath+'/knowledge/knowledgetype/edit.html',
        	DELETE :adminPath+'/knowledge/knowledgetype/del.json',
            ORDER :adminPath+'/knowledge/knowledgetype/order.json',
            STATUS :adminPath+'/knowledge/knowledgetype/status.json'
        },
        video :{
        	LIST :adminPath+'/video/list.json',
        	EDIT :adminPath+'/video/edit.html',
        	DELETE :adminPath+'/video/del.json',
            ORDER :adminPath+'/video/order.json',
            STATUS :adminPath+'/video/status.json',
            LISTBYKONW :adminPath+'/video/listByKnowId.json',
            LISTBYSUBJECT:adminPath+'/video/listBySubjectId.json'
        },
        subject :{
        	LIST :adminPath+'/subject/list.json',
        	SHOW :adminPath+'/subject/show.html',
        	EDIT :adminPath+'/subject/edit.html',
        	DELETE :adminPath+'/subject/del.json',
            ORDER :adminPath+'/subject/order.json',
            STATUS :adminPath+'/subject/status.json',
            VIDEO :adminPath+'/subject/video.html',
            LISTBYEXAM :adminPath+'/subject/listbyexam.json',
            BINDSUBLIST:adminPath+'/subject/bindSubList.json',
            LISTBYCUP :adminPath+'/subject/listbycup.json',
            BINDSUBLISTFORKNOW:adminPath+'/subject/bindSubListForKnowledge.json',
            LISTBYKNOW:adminPath+'/subject/listbyknow.json',
            NEXTEDIT:adminPath+'/subject/savenext.html'
            
        },
        exam :{
        	LIST :adminPath+'/exam/list.json',
        	SHOW :adminPath+'/exam/show.html',
        	EDIT :adminPath+'/exam/edit.html',
        	DELETE :adminPath+'/exam/del.json',
        	ORDERSERIAL :adminPath+'/exam/orderserial.json',
            STATUS :adminPath+'/exam/status.json',
            BINDDIALOG :adminPath+'/exam/bindDialog.html',
            BINDBYID:adminPath+'/exam/bindSubject.json',
            BINDADDSCORE:adminPath+'/exam/bindAddScore.html',
            CANCELBIND:adminPath+'/exam/cancelBind.json'
        },
        cup:{
        	LIST :adminPath+'/cup/list.json',
        	SHOW :adminPath+'/cup/show.html',
        	EDIT :adminPath+'/cup/edit.html',
        	DELETE :adminPath+'/cup/del.json',
            ORDER :adminPath+'/cup/order.json',
            ORDERSERIAL :adminPath+'/cup/orderserial.json',
            STATUS :adminPath+'/cup/status.json',
            BINDDIALOG :adminPath+'/cup/bindDialog.html',
            BINDBYID:adminPath+'/cup/bindSubject.json',
            BINDADDSCORE:adminPath+'/cup/bindAddScore.html',
            CANCELBIND:adminPath+'/cup/cancelBind.json'
        },
        teacher:{
        	LIST :adminPath+'/teacher/list.json',
        	SHOW :adminPath+'/teacher/show.html',
        	EDIT :adminPath+'/teacher/edit.html',
        	DELETE :adminPath+'/teacher/del.json',
            ORDER :adminPath+'/teacher/order.json',
            STATUS :adminPath+'/teacher/status.json'
        },
        user:{
        	LIST :adminPath+'/user/list.json',
        	SHOW :adminPath+'/user/show.html',
        	EDIT :adminPath+'/user/edit.html',
        	DELETE :adminPath+'/user/del.json',
            ORDER :adminPath+'/user/order.json',
            STATUS :adminPath+'/user/status.json',
            EDITPW :adminPath+'/user/editpw.html',
            EDITVIP :adminPath+'/user/editvip.html'
        },
        product:{
        	LIST :adminPath+'/product/list.json',
        	SHOW :adminPath+'/product/show.html',
        	EDIT :adminPath+'/product/edit.html',
        	DELETE :adminPath+'/product/del.json',
        	ORDER :adminPath+'/product/order.json',
            STATUS :adminPath+'/product/status.json'
        },
        order:{
        	LIST :adminPath+'/order/list.json',
        	SHOW :adminPath+'/order/show.html',
        	EDIT :adminPath+'/order/edit.html',
        	DELETE :adminPath+'/order/del.json',
        	ORDER :adminPath+'/order/order.json',
            STATUS :adminPath+'/order/status.json'
        },
        userexam:{
        	LIST :adminPath+'/userexam/list.json',
        	STATUS :adminPath+'/userexam/status.json',
        	ORDER :adminPath+'/userexam/order.json',
        	DELETE :adminPath+'/userexam/del.json'
        },
        usersubjectright:{
        	LIST :adminPath+'/usersubjectright/list.json',
        	STATUS :adminPath+'/usersubjectright/status.json',
        	ORDER :adminPath+'/usersubjectright/order.json',
        	DELETE :adminPath+'/usersubjectright/del.json'
        },
        usersubjectwrong:{
        	LIST :adminPath+'/usersubjectwrong/list.json',
        	STATUS :adminPath+'/usersubjectwrong/status.json',
        	ORDER :adminPath+'/usersubjectwrong/order.json',
        	DELETE :adminPath+'/usersubjectwrong/del.json'
        },
        usersubject:{
        	LIST:adminPath+'/usersubject/list.json',
        	STATUS :adminPath+'/usersubject/status.json',
        	DELETE:adminPath+'/usersubject/del.json',
        	SHOW :adminPath+'/usersubject/show.html'
        },
        userknowledge:{
        	LIST:adminPath+'/userknowledge/list.json',
        	STATUS :adminPath+'/userknowledge/status.json',
        	DELETE:adminPath+'/userknowledge/del.json',
        	SHOW :adminPath+'/userknowledge/show.html'
        },
        question:{
        	LIST :adminPath+'/question/list.json',
        	ORDER :adminPath+'/question/order.json',
        	STATUS :adminPath+'/question/status.json',
        	EDIT :adminPath+'/question/edit.html',
        	DELETE:adminPath+'/question/del.json',
        	SHOW :adminPath+'/question/show.html'
        }

    }
};

$.validator.setDefaults({
    highlight: function (element) {
        $(element).closest('.form-group').removeClass('has-info').addClass('has-error');
    },
    success: function (element) {
        $(element).closest('.form-group').removeClass('has-error');
        $(element).remove();
    },
    errorElement: "span",
    errorPlacement: function (error, element) {
        if(element.is('input[type=checkbox]') || element.is('input[type=radio]')) {
            var controls = element.closest('div[class*="col-"]');
            if(controls.find(':checkbox,:radio').length > 1) controls.append(error);
            else error.insertAfter(element.nextAll('.lbl:eq(0)').eq(0));
        }
        else if(element.is('.select2')) {
            error.insertAfter(element.siblings('[class*="select2-container"]:eq(0)'));
        }
        else if(element.is('.chosen-select')) {
            error.insertAfter(element.siblings('[class*="chosen-container"]:eq(0)'));
        }
        else error.insertAfter(element.parent());
    },
    errorClass: "help-block m-b-none",
    validClass: "help-block m-b-none",
    errorElement: 'div',
    focusInvalid: false
});