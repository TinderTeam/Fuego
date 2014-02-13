package cn.tinder.fuego.webservice.struts.form.purchase;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

/**
 * 
 * @ClassName: RefPlanCreateForm
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-28 上午12:26:14
 * 
 */
public class RefPlanCreateForm extends ActionForm
{

	private String[] boxes;
	private int pageNum;

	public String[] getBoxes()
	{
		return boxes;
	}

	public void setBoxes(String[] boxes)
	{
		this.boxes = boxes;
	}

	
	public int getPageNum()
	{
		return pageNum;
	}

	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}

	@Override
	public String toString()
	{
		return "RefPlanCreateForm [boxes=" + Arrays.toString(boxes) + "]";
	}

}
