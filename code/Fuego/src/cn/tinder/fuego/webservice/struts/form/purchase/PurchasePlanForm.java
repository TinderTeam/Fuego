package cn.tinder.fuego.webservice.struts.form.purchase;

import java.util.Arrays;

import org.apache.struts.action.ActionForm;

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
	private String[] techStatusList;
	private String[] matchAttrList;
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
	public String[] getTechStatusList()
	{
		return techStatusList;
	}
	public void setTechStatusList(String[] techStatusList)
	{
		this.techStatusList = techStatusList;
	}
	public String[] getMatchAttrList()
	{
		return matchAttrList;
	}
	public void setMatchAttrList(String[] matchAttrList)
	{
		this.matchAttrList = matchAttrList;
	}
	@Override
	public String toString()
	{
		return "PurchasePlanForm [date=" + date + ", duty=" + duty + ", manageName=" + manageName + ", techStatusList=" + Arrays.toString(techStatusList) + ", matchAttrList="
				+ Arrays.toString(matchAttrList) + ", typeList=" + Arrays.toString(typeList) + "]";
	}
 

}
