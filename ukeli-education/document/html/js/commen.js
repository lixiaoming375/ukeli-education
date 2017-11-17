/**
  项目JS主入口
  以依赖Layui的layer和form模块为例
**/    
layui.define(['element','layer', 'form','laypage','util'], function(exports){
	var element=layui.element(),
		layer = layui.layer,
		form = layui.form(),
		$ = layui.jquery,
		laypage = layui.laypage,
		util = layui.util;

	util.fixbar();

	// 导航
	navShow();
	$(window).on('scroll',function(){
		navShow();
	});

	function navShow(){
		var sT=$(document).scrollTop();
		// console.log(sT);
		if(sT>100){
			$('.header').css('background','rgba(41,60,85,0.9)');
		}else{
			$('.header').css('background','rgba(41,60,85,0.1)');
		}
	}

	var navHoverPosition='';
	$('.nav').on({
		mouseenter:function(){
			var nW=$(this).width(),
				nL=$(this).position().left,
				uL=$(this).closest('.nav').position().left;
			// console.log(nL+uL);
			$('.nav-hover').css({
				'left':nL+uL,
				'width':nW,
				'opacity':1
			});

			var dpIn=$(this).find('.nav-dp-inner'),
				dpInLi=dpIn.find('li').length;
			if(dpIn.length>0){
				dpIn.css({
					'height':dpInLi*60,
					'opacity':1
				});
			}

			var dp=$('.nav-dropdown');
			if($(this).hasClass('hasDp')){
				dp.css({
					'opacity':1,
					'height':'530px'
				}).find('.layui-tab:eq('+$(this).index()+')').addClass('on').siblings('.layui-tab').removeClass('on');
			}

			navHoverPosition=$('.nav-hover').attr('style');

		},
		mouseleave:function(){
			$('.nav-hover').css({
				'left':'160px',
				'width':'40px',
				'opacity':0
			});

			var dpIn=$(this).find('.nav-dp-inner');
			if(dpIn.length>0){
				dpIn.css({
					'height':0,
					'opacity':0
				});
			}

			var dp=$('.nav-dropdown');
			if($(this).hasClass('hasDp')){
				dp.css({
					'opacity':0,
					'height':0
				});
				
			}
		}
	},'>li');

	$('.nav-dropdown').on({
		mouseenter:function(){
			$('.nav-hover').attr('style',navHoverPosition);
			$(this).css({
				'opacity':1,
				'height':'530px'
			});
		},
		mouseleave:function(){
			$(this).css({
				'opacity':0,
				'height':0
			});
			$('.nav-hover').css({
				'left':'160px',
				'width':'40px',
				'opacity':0
			});
		}
	});

  
	exports('commen', {}); //注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});


















