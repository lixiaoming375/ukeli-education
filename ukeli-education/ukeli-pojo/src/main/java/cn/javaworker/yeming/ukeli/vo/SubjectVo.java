/**
 * 
 */
package cn.javaworker.yeming.ukeli.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.javaworker.yeming.ukeli.pojo.SubjectDo;
import cn.javaworker.yeming.ukeli.pojo.SubjectOptionsDo;

/**
 * @author yeming
 *
 */
@SuppressWarnings("serial")
public class SubjectVo implements Serializable{
	
	private SubjectDo subjectDo;
	private List<SubjectOptionsDo> list = new ArrayList<>();
	/**
	 * @return the subjectDo
	 */
	public SubjectDo getSubjectDo() {
		return subjectDo;
	}
	/**
	 * @param subjectDo the subjectDo to set
	 */
	public void setSubjectDo(SubjectDo subjectDo) {
		this.subjectDo = subjectDo;
	}
	/**
	 * @return the list
	 */
	public List<SubjectOptionsDo> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(List<SubjectOptionsDo> list) {
		this.list = list;
	}
	
	
	

}
