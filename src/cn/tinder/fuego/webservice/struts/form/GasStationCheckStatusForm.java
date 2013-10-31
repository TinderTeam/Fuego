package cn.tinder.fuego.webservice.struts.form;

import org.apache.struts.action.ActionForm;

/**
 * 
* @ClassName: GasStationCheckStatusForm 
* @Description: TODO
* @author Kong Jingliang
* @date 2013-10-2 下午09:16:34 
*
 */

public class GasStationCheckStatusForm extends ActionForm{

	
	private String a;

	@Override
	public String toString() {
		return "GasStationCheckStatusForm [a=" + a + "]";
	}

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}
}
