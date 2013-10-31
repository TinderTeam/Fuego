package cn.tinder.fuego.webservice.struts.bo.check;

import java.util.ArrayList;
import java.util.List;

public class CheckResultInitBo {

	private  CheckBo checkBo =new CheckBo() ;
	
	private List<ResultBo> resultBo = new  ArrayList<ResultBo>();

	public CheckBo getCheckBo() {
		return checkBo;
	}

	public void setCheckBo(CheckBo checkBo) {
		this.checkBo = checkBo;
	}

	public List<ResultBo> getResultBo() {
		return resultBo;
	}

	public void setResultBo(List<ResultBo> resultBo) {
		this.resultBo = resultBo;
	}

	@Override
	public String toString() {
		return "CheckResultInitBo [checkBo=" + checkBo + ", resultBo="
				+ resultBo + "]";
	}

	
	
}
