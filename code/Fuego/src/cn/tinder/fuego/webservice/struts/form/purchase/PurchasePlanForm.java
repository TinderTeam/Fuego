package cn.tinder.fuego.webservice.struts.form.purchase;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

import cn.tinder.fuego.webservice.struts.form.AssetsFilterForm;

/**
 * 
 * @ClassName: PurchasePlanForm
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-24 下午08:33:43
 * 
 */
public class PurchasePlanForm extends ActionForm
{

	private String date;
	private String duty;
	private String manageName;
	private String[] typeList;
	
	
 
	public String getDuty()
	{
		return duty;
	}
	public void setDuty(String duty)
	{
		this.duty = duty;
	}
	public String getManageName()
	{
		return manageName;
	}
	public void setManageName(String manageName)
	{
		this.manageName = manageName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String[] getTypeList() {
		return typeList;
	}
	public void setTypeList(String[] typeList) {
		this.typeList = typeList;
	}
	@Override
	public String toString() {
		return "PurchasePlanForm [date=" + date + ", typeList="
				+ Arrays.toString(typeList) + "]";
	}

}
