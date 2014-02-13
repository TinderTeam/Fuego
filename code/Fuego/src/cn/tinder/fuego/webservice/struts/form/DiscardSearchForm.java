package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

public class DiscardSearchForm extends ActionForm{

	private String date;
	private String[] assetsTypeList;
	private String[] techStatusList;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String[] getAssetsTypeList()
	{
		return assetsTypeList;
	}
	public void setAssetsTypeList(String[] assetsTypeList)
	{
		this.assetsTypeList = assetsTypeList;
	}
	public String[] getTechStatusList()
	{
		return techStatusList;
	}
	public void setTechStatusList(String[] techStatusList)
	{
		this.techStatusList = techStatusList;
	}
 

}
