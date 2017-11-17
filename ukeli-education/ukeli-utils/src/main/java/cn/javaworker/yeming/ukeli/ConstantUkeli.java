package cn.javaworker.yeming.ukeli;

public class ConstantUkeli {
	
	
	public final static Short ONLINE_STATUS = Short.valueOf("1");
	public final static int SUBJECTCLAZZTYPEID=1;			//试题类型
	public final static int SUBJECTCLAZZVTYPEID=4;			//试题题目类型
	public final static int KNOWLEDGECLAZZTYPEID=2;
	public final static int GRADECLAZZTYPEID=3;   //年纪类型
	public final static int CUPTYPECLAZZTYPEID=5;//杯赛类型
	public final static int QUESTIONTYPECLAZZTYPEID=6;//留言类型

	public final static int SEARCHSUBJECTBYEXAM=11;
	public final static int SEARCHSUBJECTBYCUP=22;
	public final static int SEARCHSUBJECTBYKNOW=33;		
	
	public final static int QUESTIONVTYPEFOREXAM=34;	
	public final static int QUESTIONVTYPEFORCUP=38;	
	public final static int QUESTIONVTYPEFORKNOW=39;	
	public final static int QUESTIONVTYPEFORSUBJECT=40;	
	public final static int QUESTIONVTYPEFORZICE=41;	
	public final static int QUESTIONVTYPEFOR=33;	
	
	
	public final static int NOPAGELIMIT=1000;
	
	public final static int ISADDCOURSE=1;
	
	public final static int SUBJECTVTYPELITI=7;
	public final static int SUBJECTVTYPESUITANG=8;
	public final static int SUBJECTVTYPEZICE=9;
	public final static int SUBJECTVTYPECUP=10;
	public final static int SUBJECTVTYPEKAOSHI=11;
	

	/**
	 * 缓存中保存分类类别的key
	 */
	public final  static  String CLAZZ_LIST_CACHA_KEY = "clazz_list_type_";

}
