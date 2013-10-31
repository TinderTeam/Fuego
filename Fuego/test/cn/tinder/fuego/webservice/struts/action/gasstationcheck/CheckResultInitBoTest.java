package cn.tinder.fuego.webservice.struts.action.gasstationcheck;

import java.util.ArrayList;
import java.util.List;

import cn.tinder.fuego.service.ConstServiceTest;
import cn.tinder.fuego.util.constant.LogKeyConst;
import cn.tinder.fuego.webservice.struts.bo.check.CheckBo;
import cn.tinder.fuego.webservice.struts.bo.check.CheckResultInitBo;
import cn.tinder.fuego.webservice.struts.bo.check.ResultBo;

public class CheckResultInitBoTest {

	public static CheckResultInitBo getCase() {
		// TODO Auto-generated method stub
		
		
		CheckBo cb =new CheckBo();
		ResultBo rb=new ResultBo();
		
		//cb
		
		List<String> gas =null ;
		List<String> dept =null ;
		List<String> type =null ;
		List<String> number=null;
		List<String> assetsName=null;
		gas = ConstServiceTest.getAllGasNameList() ;
		dept = ConstServiceTest.getAllUserNameList() ;
		type=ConstServiceTest.getAssetsTypeList() ;
		//number=ConstServiceTest.getAllNumberList();
		//assetsName=ConstServiceTest.getAllUserNameList();
		
		cb.setGasStation(gas);
		cb.setDeptName(dept) ;
		cb.setAssetsType(type) ;
		
		//rb
		
						
		
		rb.setNumber("1");
		rb.setGasName("惠东加油站");
		rb.setDeptName("财经");
		rb.setAssetsName("电脑");
		rb.setAssetsType("Lennvo");
		rb.setAssetsType("sfg");
		rb.setAccountQunty("50");
		rb.setActQunty("30");
		rb.setDiff("aoojg");
		

		
		
		CheckResultInitBo bo =new CheckResultInitBo();
		bo.setCheckBo(cb);
		 /**
		List<ResultBo> rBo = new ArrayList<ResultBo>();
		rBo.add(rb);
		bo.setResultBo(rBo);
		*/
		bo.getResultBo().add(rb);
		System.out.println(bo);
		
		return bo;
	}

}
