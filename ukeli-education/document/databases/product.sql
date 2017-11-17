SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Indexes */

DROP INDEX unq_order_ordernumber ON t_order;
DROP INDEX unq_product_ident ON t_product;
DROP INDEX inx_subject_vtype_subjectid ON t_subject_vtype;
DROP INDEX inx_user_vcode ON t_user;



/* Drop Tables */

DROP TABLE IF EXISTS t_cpu_subject;
DROP TABLE IF EXISTS t_cpu;
DROP TABLE IF EXISTS t_exam_subject;
DROP TABLE IF EXISTS t_exam;
DROP TABLE IF EXISTS t_knowledge_subject;
DROP TABLE IF EXISTS t_knowledge;
DROP TABLE IF EXISTS t_order;
DROP TABLE IF EXISTS t_product;
DROP TABLE IF EXISTS t_question;
DROP TABLE IF EXISTS t_subject_options;
DROP TABLE IF EXISTS t_subject;
DROP TABLE IF EXISTS t_subject_vtype;
DROP TABLE IF EXISTS t_teacher;
DROP TABLE IF EXISTS t_user;
DROP TABLE IF EXISTS t_user_exam;
DROP TABLE IF EXISTS t_user_exam_answer;
DROP TABLE IF EXISTS t_user_subject_right;
DROP TABLE IF EXISTS t_user_subject_wrong;




/* Create Tables */

CREATE TABLE t_cpu
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 名称|1|1|1
	name varchar(200) COMMENT '名称|1|1|1',
	-- 年级编号|1|1|3
	gradeid bigint(20) unsigned COMMENT '年级编号|1|1|3',
	-- 详细介绍|1|1|1
	remark varchar(4000) COMMENT '详细介绍|1|1|1',
	-- 分数|1|1|1
	score int COMMENT '分数|1|1|1',
	-- 总试题数量|2|3|0
	totalexam int COMMENT '总试题数量|2|3|0',
	-- 考试时长|1|1|1|单位分钟
	times int COMMENT '考试时长|1|1|1|单位分钟',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


CREATE TABLE t_cpu_subject
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 杯赛编号|1|1|1
	cpuid bigint unsigned COMMENT '杯赛编号|1|1|1',
	-- 试题编号|1|1|1
	subjectid bigint unsigned COMMENT '试题编号|1|1|1',
	-- 序号|1|1|1
	serial int unsigned COMMENT '序号|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


CREATE TABLE t_exam
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 名称|1|1|1
	name varchar(200) COMMENT '名称|1|1|1',
	-- 年级编号|1|1|3
	gradeid bigint(20) unsigned COMMENT '年级编号|1|1|3',
	-- 考试时长|1|1|1|单位分钟
	times int COMMENT '考试时长|1|1|1|单位分钟',
	-- 总试题数量|2|3|0
	totalexam int COMMENT '总试题数量|2|3|0',
	-- 详细介绍|1|1|1
	remark varchar(4000) COMMENT '详细介绍|1|1|1',
	-- 总分数|1|1|1
	totalscore int COMMENT '总分数|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


CREATE TABLE t_exam_subject
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 考试编号|1|1|1
	examid bigint unsigned COMMENT '考试编号|1|1|1',
	-- 试题编号|1|1|1
	subjectid bigint unsigned COMMENT '试题编号|1|1|1',
	-- 序号|1|1|1
	serial int unsigned COMMENT '序号|1|1|1',
	-- 分数|1|1|1
	score int COMMENT '分数|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


-- 知识点|knowledge
CREATE TABLE t_knowledge
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 知识点类型编号|1|1|1
	typeid bigint DEFAULT 0 NOT NULL COMMENT '知识点类型编号|1|1|1',
	-- 年级编号|1|1|3
	gradeid bigint(20) unsigned NOT NULL COMMENT '年级编号|1|1|3',
	-- 知识点名称
	nodename varchar(50) NOT NULL COMMENT '知识点名称',
	-- 总试题数量|2|3|0
	totalexam int DEFAULT 0 COMMENT '总试题数量|2|3|0',
	-- 详细介绍|1|1|1
	remark varchar(4000) COMMENT '详细介绍|1|1|1',
	-- 视频路径|1|1|1
	videopath varchar(255) COMMENT '视频路径|1|1|1',
	-- 视频说明|1|1|1
	video text COMMENT '视频说明|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
) COMMENT = '知识点|knowledge';


CREATE TABLE t_knowledge_subject
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 知识点编号|1|1|1
	knowledgeid bigint unsigned COMMENT '知识点编号|1|1|1',
	-- 例题编号|1|1|1
	subjectid bigint unsigned COMMENT '例题编号|1|1|1',
	-- 题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题
	vtype int COMMENT '题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题',
	-- 父类编号|1|1|1
	fatherid bigint COMMENT '父类编号|1|1|1',
	-- 序号|1|1|1
	serial int unsigned COMMENT '序号|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


-- 订单产品|order
CREATE TABLE t_order
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 订单号|1|1|1
	ordernumber varchar(40) COMMENT '订单号|1|1|1',
	-- 用户编号|1|1|1
	userid bigint unsigned COMMENT '用户编号|1|1|1',
	-- 用户名|1|1|1
	username varchar(50) COMMENT '用户名|1|1|1',
	-- 昵称|1|1|1|用户昵称
	nickname varchar(50) COMMENT '昵称|1|1|1|用户昵称',
	-- 产品编号|2|3|0
	productid bigint COMMENT '产品编号|2|3|0',
	-- 产品名称|1|1|1
	productname varchar(150) COMMENT '产品名称|1|1|1',
	-- 购买数量|1|1|1
	amount int unsigned DEFAULT 0 COMMENT '购买数量|1|1|1',
	-- 总市场价|1|1|1|
	totalmarketprice int unsigned DEFAULT 0 COMMENT '总市场价|1|1|1|',
	-- 总价|1|1|1
	totalprice int unsigned DEFAULT 0 COMMENT '总价|1|1|1',
	totalvipprice int unsigned DEFAULT 0,
	-- 支付价格|1|1|1
	payprice int unsigned DEFAULT 0 COMMENT '支付价格|1|1|1',
	couponprice int unsigned DEFAULT 0,
	-- 下单时间|1|1|1
	ordertime datetime COMMENT '下单时间|1|1|1',
	-- 订单状态|1|3|0| 0 默认 1 已经审核 2 已经发货 3 用户收货 4 完单   9 用户取消
	orderstatus int unsigned DEFAULT 0 COMMENT '订单状态|1|3|0| 0 默认 1 已经审核 2 已经发货 3 用户收货 4 完单   9 用户取消',
	-- 支付状态|1|3|0| 0 未支付  1 已经支付  9 退款
	paystatus tinyint unsigned DEFAULT 0 COMMENT '支付状态|1|3|0| 0 未支付  1 已经支付  9 退款',
	-- 支付时间|1|3|0
	paytime datetime COMMENT '支付时间|1|3|0',
	-- 支付类型|1|3|0| 0 默认  1 支付宝  2 微信  3 银联  4 货到汇款 5 其他  9 未知
	paytype tinyint unsigned DEFAULT 0 COMMENT '支付类型|1|3|0| 0 默认  1 支付宝  2 微信  3 银联  4 货到汇款 5 其他  9 未知',
	-- 支付订单号|1|3|0
	payorder varchar(100) COMMENT '支付订单号|1|3|0',
	-- 备注|2|3|0
	detail varchar(1000) COMMENT '备注|2|3|0',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
) COMMENT = '订单产品|order';


-- 产品名称|product
CREATE TABLE t_product
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 产品名称|1|1|1
	productname varchar(150) COMMENT '产品名称|1|1|1',
	-- 唯一标识|2|1|1
	ident varchar(40) COMMENT '唯一标识|2|1|1',
	-- 备注|2|3|0
	detail varchar(1000) COMMENT '备注|2|3|0',
	-- 市场价|1|1|1|单位为分
	marketprice int unsigned DEFAULT 0 COMMENT '市场价|1|1|1|单位为分',
	-- 商城价|1|1|1|单位为分
	price int unsigned DEFAULT 0 COMMENT '商城价|1|1|1|单位为分',
	-- VIP会员价|1|1|1|单位为分
	vipprice int unsigned DEFAULT 0 COMMENT 'VIP会员价|1|1|1|单位为分',
	-- 类型|1|1|2|产品类型 1  绑定的全部商品  2 单个视频，其中标示，在订单中
	producttype tinyint unsigned COMMENT '类型|1|1|2|产品类型 1  绑定的全部商品  2 单个视频，其中标示，在订单中',
	-- 日期类型|1|1|2|1 年 2 月  3 日 4 小时  5 分 6 秒
	datetype tinyint unsigned DEFAULT 2 COMMENT '日期类型|1|1|2|1 年 2 月  3 日 4 小时  5 分 6 秒',
	-- 日期数量|1|1|2|日期增加数量
	datecount int unsigned DEFAULT 1 COMMENT '日期数量|1|1|2|日期增加数量',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
) COMMENT = '产品名称|product';


CREATE TABLE t_question
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 用户编号|1|1|1
	userid bigint unsigned COMMENT '用户编号|1|1|1',
	-- 问题内容|1|1|1
	question varchar(2000) COMMENT '问题内容|1|1|1',
	-- 问题类型|1|1|1|1 直接问题  2 考试问题  3 杯赛问题 4 知识点  5 试题 
	vtype tinyint COMMENT '问题类型|1|1|1|1 直接问题  2 考试问题  3 杯赛问题 4 知识点  5 试题 ',
	-- 关联编号|2|3|1
	relationid bigint DEFAULT 0 COMMENT '关联编号|2|3|1',
	-- 是否回答|1|1|1| 0 未回答  1 已经回答
	isanswer char(1) DEFAULT '0' COMMENT '是否回答|1|1|1| 0 未回答  1 已经回答',
	-- 教师回复|1|1|1
	answer varchar(4000) COMMENT '教师回复|1|1|1',
	-- 教师回答时间|1|1|1
	answer_time datetime COMMENT '教师回答时间|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


-- 题目|subject
CREATE TABLE t_subject
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 试题类型|1|1|1| 1 单选 2 多选  3 填空
	stype int COMMENT '试题类型|1|1|1| 1 单选 2 多选  3 填空',
	-- 名称|1|1|1
	name varchar(200) COMMENT '名称|1|1|1',
	-- 题目内容|1|1|1
	content text COMMENT '题目内容|1|1|1',
	-- 答案|1|1|1
	answer varchar(200) COMMENT '答案|1|1|1',
	-- 试题解析|1|1|1
	analysis varchar(500) COMMENT '试题解析|1|1|1',
	-- 题目图片|1|1|1
	imgurl varchar(255) COMMENT '题目图片|1|1|1',
	-- 学习次数|1|1|1
	studytimes bigint DEFAULT 0 COMMENT '学习次数|1|1|1',
	-- 备注|2|3|0
	detail varchar(1000) COMMENT '备注|2|3|0',
	-- 视频路径|1|1|1
	videopath varchar(255) COMMENT '视频路径|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
) COMMENT = '题目|subject';


CREATE TABLE t_subject_options
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 试题编号|2|3|0
	subjectid bigint unsigned COMMENT '试题编号|2|3|0',
	-- 试题选项名称|1|1|1
	optiionname varchar(5) COMMENT '试题选项名称|1|1|1',
	-- 选项内容|1|1|1
	optioncontext varchar(1000) COMMENT '选项内容|1|1|1',
	-- 浏览次数|1|1|1
	viewtimes int DEFAULT 0 COMMENT '浏览次数|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


CREATE TABLE t_subject_vtype
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 例题编号|1|1|1
	subjectid bigint unsigned COMMENT '例题编号|1|1|1',
	-- 题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题
	vtype int COMMENT '题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题',
	PRIMARY KEY (id)
);


CREATE TABLE t_teacher
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 科目名称|1|1|1
	typename varchar(50) NOT NULL COMMENT '科目名称|1|1|1',
	-- 名称|1|1|1
	name varchar(200) NOT NULL COMMENT '名称|1|1|1',
	-- 备注|2|3|0
	detail varchar(1000) COMMENT '备注|2|3|0',
	-- 图片地址|1|1|1
	imageurl varchar(200) NOT NULL COMMENT '图片地址|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


-- 用户信息|user
CREATE TABLE t_user
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 用户名|1|1|1
	username varchar(50) COMMENT '用户名|1|1|1',
	-- 密码|1|1|1
	password varchar(64) COMMENT '密码|1|1|1',
	-- 昵称|1|1|1|用户昵称
	nickname varchar(50) COMMENT '昵称|1|1|1|用户昵称',
	-- 真实姓名|1|1|1
	realname varchar(100) COMMENT '真实姓名|1|1|1',
	-- 出生年月|1|1|1
	bidthday date COMMENT '出生年月|1|1|1',
	-- 性别|1|1|1| 1男 2 女 其他为未知
	sex char(1) DEFAULT '1' COMMENT '性别|1|1|1| 1男 2 女 其他为未知',
	-- 联系电话|1|1|1
	telephone varchar(40) COMMENT '联系电话|1|1|1',
	-- 手机号码|1|1|1
	mobile varchar(50) COMMENT '手机号码|1|1|1',
	-- 电子邮件|1|1|1
	email varchar(150) COMMENT '电子邮件|1|1|1',
	-- 地址信息|1|1|1
	address varchar(255) COMMENT '地址信息|1|1|1',
	-- 邮编|2|1|1
	postcode varchar(20) COMMENT '邮编|2|1|1',
	-- 首拼字母|2|3|0
	firstletter varchar(2) COMMENT '首拼字母|2|3|0',
	-- 拼音|2|3|0
	spell varchar(20) COMMENT '拼音|2|3|0',
	-- 全拼|2|3|0
	allspell varchar(150) COMMENT '全拼|2|3|0',
	-- 备注|2|3|0
	detail varchar(1000) COMMENT '备注|2|3|0',
	-- 微信号|2|3|0
	wechat varchar(150) COMMENT '微信号|2|3|0',
	-- qq号码|2|3|0
	qq varchar(20) COMMENT 'qq号码|2|3|0',
	-- 头像地址|2|3|0
	headpic varchar(150) COMMENT '头像地址|2|3|0',
	-- 推广编号|2|3|0
	vcode varchar(20) COMMENT '推广编号|2|3|0',
	-- 邀请码|2|3|0
	inventcode varchar(20) COMMENT '邀请码|2|3|0',
	-- 登陆次数|2|3|0
	logintimes int DEFAULT 0 COMMENT '登陆次数|2|3|0',
	-- 最后登录时间|2|3|0
	lastlogintime timestamp COMMENT '最后登录时间|2|3|0',
	-- 最后登录IP|2|3|0
	lastloginip varchar(60) COMMENT '最后登录IP|2|3|0',
	-- 注册平台|2|3|0|1iphone 2 android 3 wechat 4 web 5 ipad 6 androidpad 9 其他
	platform varchar(2) COMMENT '注册平台|2|3|0|1iphone 2 android 3 wechat 4 web 5 ipad 6 androidpad 9 其他',
	-- 是否vip会员|1|3|0| 0 不是  1 是
	isvip char(1) DEFAULT '0' COMMENT '是否vip会员|1|3|0| 0 不是  1 是',
	-- vip开始时间|2|3|0
	vipstarttime datetime COMMENT 'vip开始时间|2|3|0',
	-- vip结束时间|1|1|1
	vipendtime datetime COMMENT 'vip结束时间|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id),
	CONSTRAINT unq_user_username UNIQUE (username)
) COMMENT = '用户信息|user';


CREATE TABLE t_user_exam
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 用户编号|1|1|1
	userid bigint unsigned COMMENT '用户编号|1|1|1',
	-- 考试编号|1|1|1
	examid bigint unsigned COMMENT '考试编号|1|1|1',
	-- 使用时间|1|2|1| 单位秒
	usetime int COMMENT '使用时间|1|2|1| 单位秒',
	-- 得分情况|1|1|1
	score int COMMENT '得分情况|1|1|1',
	-- 考试次数|1|1|1|我参与此次试卷的次数
	vtime int unsigned COMMENT '考试次数|1|1|1|我参与此次试卷的次数',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


CREATE TABLE t_user_exam_answer
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 用户考试表编号|1|1|1
	userexamid bigint unsigned COMMENT '用户考试表编号|1|1|1',
	-- 用户编号|1|1|1
	userid bigint unsigned COMMENT '用户编号|1|1|1',
	-- 考试编号|1|1|1
	examid bigint unsigned COMMENT '考试编号|1|1|1',
	-- 例题编号|1|1|1
	subjectid bigint unsigned COMMENT '例题编号|1|1|1',
	-- 我的答案|1|1|1
	myanswer varchar(500) COMMENT '我的答案|1|1|1',
	-- 答案|1|1|1
	answer varchar(200) COMMENT '答案|1|1|1',
	-- 是否错误|1|1|1| 1 正确  2 错误
	iswrong char(1) DEFAULT '1' COMMENT '是否错误|1|1|1| 1 正确  2 错误',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


CREATE TABLE t_user_subject_right
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 用户编号|1|1|1
	userid bigint unsigned COMMENT '用户编号|1|1|1',
	-- 例题编号|1|1|1
	subjectid bigint unsigned COMMENT '例题编号|1|1|1',
	-- 题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题
	vtype int COMMENT '题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题',
	-- 是否答题|1|1|1|0 未回答  1 已经回答
	isanswer char(1) DEFAULT '0' COMMENT '是否答题|1|1|1|0 未回答  1 已经回答',
	-- 是否错误|1|1|1| 0 正确  1 错误1
	iswrong char(1) DEFAULT '1' COMMENT '是否错误|1|1|1| 0 正确  1 错误1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);


CREATE TABLE t_user_subject_wrong
(
	-- 主键|1|1|1
	id bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '主键|1|1|1',
	-- 用户编号|1|1|1
	userid bigint unsigned COMMENT '用户编号|1|1|1',
	-- 例题编号|1|1|1
	subjectid bigint unsigned COMMENT '例题编号|1|1|1',
	-- 题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题
	vtype int COMMENT '题目类别|1|1|1|1 例题 2 课堂联系 3 自测卷  4 杯赛试题  5 考试试题',
	-- 是否修改|1|1|1
	ismod char(1) COMMENT '是否修改|1|1|1',
	-- 排序字段|2|3|1
	iorder bigint DEFAULT 0 COMMENT '排序字段|2|3|1',
	-- 状态|1|3|0|0正常
	status tinyint DEFAULT 0 COMMENT '状态|1|3|0|0正常',
	-- 增加者编号|2|3|0
	adderid bigint DEFAULT 0 COMMENT '增加者编号|2|3|0',
	-- 增加者|2|3|0
	adder varchar(50) COMMENT '增加者|2|3|0',
	-- 最后修改者|2|3|0
	editer varchar(50) COMMENT '最后修改者|2|3|0',
	-- 最后修改者编号|2|3|0
	editerid bigint COMMENT '最后修改者编号|2|3|0',
	-- 备注字段1|2|3|0
	remark1 varchar(255) COMMENT '备注字段1|2|3|0',
	-- 备注字段2|2|3|0
	remark2 varchar(255) COMMENT '备注字段2|2|3|0',
	-- 增加时间|2|3|0
	addtime datetime COMMENT '增加时间|2|3|0',
	-- 最后修改时间|2|3|0
	edittime datetime COMMENT '最后修改时间|2|3|0',
	-- 锁定标志|1|3|0|0正常 1锁定 2 删除
	slock tinyint DEFAULT 0 COMMENT '锁定标志|1|3|0|0正常 1锁定 2 删除',
	PRIMARY KEY (id)
);



/* Create Indexes */

CREATE INDEX unq_order_ordernumber ON t_order (ordernumber ASC);
CREATE INDEX unq_product_ident ON t_product (ident ASC);
CREATE INDEX inx_subject_vtype_subjectid ON t_subject_vtype (subjectid ASC);
CREATE INDEX inx_user_vcode ON t_user ();



