/**
* <p> * Title: 叶明开发的代码系统*</p>
* <p> * Description: * </p>
* <p> * Copyright: Copyright (c) 2012-2018* </p>
* <p> * Company: java工作者 * </p>
* @author 叶明（开发）
* @version 4.0.2
*/
package cn.javaworker.yeming.ukeli;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cn.javaworker.yeming.core.source.db.DatabaseType;
import cn.javaworker.yeming.core.source.db.impl.MysqlType;
import cn.javaworker.yeming.core.source.file.CreateController;
import cn.javaworker.yeming.core.source.file.CreateDao;
import cn.javaworker.yeming.core.source.file.CreatePageAction;
import cn.javaworker.yeming.core.source.file.CreatePojo;
import cn.javaworker.yeming.core.source.file.CreateService;
import cn.javaworker.yeming.core.source.util.DBConn;
import cn.javaworker.yeming.core.source.vo.Table;

/**
 * 创建日期：2017年8月6日 下午3:18:18
 * 开发者：叶明(email:guming123416@163.com,QQ:47043760)
 * 修改者：
 * 修改时间：
 * 程序作用：
 * 1、
 * 2、
 * 修改说明：
 * 1、
 * 2、
 * 版本：@version 4.0.2
 * @author 叶明
 */
public class RunMain {
	
	
	public static String filepath = "/Users/yeming/devp/project/java/";
//	public static String filepath = "D:/source/";
	
	private final static String URL = "jdbc:mysql://192.168.0.125:4406/ukeli?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&allowMultiQueries=true&serverTimezone=PRC&useSSL=false";
	private final static String USERNAME ="root";
	private final static String PASSWORD = "123456";
//	
	private final static String DATABASE ="ukeli";
	private final static String tempatePath="ace";
	
	public static void main(String[] args) {
			executeMysqlFile();
	}

	public static void executeMysqlFile() {
		Connection conn = null;
		String packet = "cn.javaworker.yeming.ukeli";
		try {
			conn =DBConn.getConn(URL,USERNAME,PASSWORD,1);
			DatabaseType datatype = new MysqlType();
			List<Table> list =datatype.getFromDateBase(DATABASE, conn);
			
			for (Table table : list) {

//				System.out.println("table="+table.getName() + ",="+table.getDesp());
//				String desp = getVisitsystemDesp().get(table.getName());
//				table.setDesp(desp);
				String desp =table.getDesp();
				if(null != desp){
					String[] strings = StringUtils.split(desp, "|");
					if(strings.length==2){
						table.setDesp(strings[0]);
						table.setModel(strings[1]);
					}
				}
				System.out.println("table="+table.getName() + ",="+table.getDesp() +",="+table.getModel());
				
				CreatePojo pojo = new CreatePojo(table);
				CreateDao dao = new CreateDao(table);
				CreateService servive =new CreateService(table);
				CreateController controller = new CreateController(table);

				CreatePageAction page = new CreatePageAction(table);
				try {
					pojo.createJdbc(filepath, packet,tempatePath);
					dao.createJdbc(filepath, packet, tempatePath);
////					dao.createMybaits(filepath, packet);
					servive.createJdbc(filepath, packet,tempatePath);
					controller.createAction(filepath, packet,tempatePath);
					page.createPage(filepath, packet, tempatePath,DATABASE);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
			
		}catch(Exception ex) {
			
		}finally {
			if (null != conn) {
				try {
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
		
		
	}

}
