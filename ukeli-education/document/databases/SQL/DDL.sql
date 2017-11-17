

-- 年级|clazz
CREATE TABLE t_grade
(
	-- 自增主键2|3|0
	id bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键2|3|0',
	-- 年级名称|1|1|1
	grade varchar(20) COMMENT '年级名称|1|1|1',
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
) COMMENT = '年级|clazz';



-- 知识点|knowledge
CREATE TABLE t_knowledge
(
	-- 自增主键2|3|0
	id bigint NOT NULL COMMENT '自增主键2|3|0',
	-- 知识点类型编号|1|1|1
	typeid bigint DEFAULT 0 NOT NULL COMMENT '知识点类型编号|1|1|1',
	-- 年级id|外键
	gradeid bigint(20) NOT NULL COMMENT '年级id|外键',
	-- 知识点名称
	nodename varchar(50) NOT NULL COMMENT '知识点名称',
	-- 知识点介绍
	remark varchar(500) COMMENT '知识点介绍',
	-- 总试题数量|2|3|0
	totalexam int DEFAULT 0 COMMENT '总试题数量|2|3|0',
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


-- 知识点类型|knowledge
CREATE TABLE t_knowledge_type
(
	-- 自增主键2|3|0
	id bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键2|3|0',
	-- 知识点类型名称|1|1|1
	typename varchar(255) COMMENT '知识点类型名称|1|1|1',
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
) COMMENT = '知识点类型|knowledge';



-- 题目|subject
CREATE TABLE t_subject
(
	-- 自增主键2|3|0
	id bigint NOT NULL COMMENT '自增主键2|3|0',
	-- 试题类型|1|1|1| 1 单选 2 多选  3 填空
	stype char(1) COMMENT '试题类型|1|1|1| 1 单选 2 多选  3 填空',
	-- 试题题目类型|1|1|1|1 例题，2 随堂练习，3 自测卷
	vtype char(1) COMMENT '试题题目类型|1|1|1|1 例题，2 随堂练习，3 自测卷',
	-- 题目名称
	name varchar(20) NOT NULL COMMENT '题目名称',
	-- 题目内容
	content text NOT NULL COMMENT '题目内容',
	-- 答案
	answer varchar(200) NOT NULL COMMENT '答案',
	-- 试题解析|1|1|1
	analysis varchar(500) COMMENT '试题解析|1|1|1',
	-- 题目图片
	image blob COMMENT '题目图片',
	-- 浏览次数|1|1|1
	viewtimes int DEFAULT 0 COMMENT '浏览次数|1|1|1',
	-- 学习次数|1|1|1
	studytimes bigint DEFAULT 0 COMMENT '学习次数|1|1|1',
	-- 备注信息|1|1|1
	detail varchar(500) COMMENT '备注信息|1|1|1',
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


CREATE TABLE t_subject_video
(
	-- 自增主键2|3|0
	id bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键2|3|0',
	-- 试题编号|1|1|1
	subjectid bigint COMMENT '试题编号|1|1|1',
	-- 视频编号|1|1|1
	videoid bigint(20) COMMENT '视频编号|1|1|1',
	PRIMARY KEY (id)
);



-- 视频|video
CREATE TABLE t_video
(
	-- id
	id bigint(20) NOT NULL COMMENT 'id',
	-- 视频名称|1|1|1
	videoname varchar(255) COMMENT '视频名称|1|1|1',
	-- 视频地址
	videourl varchar(255) COMMENT '视频地址',
	-- 视频类型|1|1|1|1 MP4  2 m3u8
	videotype char(1) COMMENT '视频类型|1|1|1|1 MP4  2 m3u8',
	-- 视频描述
	detail varchar(500) COMMENT '视频描述',
	-- 视频时长|1|1|1|单位为妙
	videotime int COMMENT '视频时长|1|1|1|单位为妙',
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
) COMMENT = '视频|video';

CREATE TABLE t_knowledge_video
(
	-- 自增主键2|3|0
	id bigint NOT NULL AUTO_INCREMENT COMMENT '自增主键2|3|0',
	-- 知识点编号|1|1|1
	knowledgeid bigint COMMENT '知识点编号|1|1|1',
	-- 视频编号|1|1|1
	videoid bigint(20) COMMENT '视频编号|1|1|1',
	PRIMARY KEY (id)
);

