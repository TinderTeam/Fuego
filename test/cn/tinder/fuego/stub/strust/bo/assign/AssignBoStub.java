/**   
 * @Title: AssignBoStub.java 
 * @Package cn.tinder.fuego.stub.strust.bo.assign 
 * @Description: TODO
 * @author Tang Jun   
 * @date 2013-9-25 下午10:10:51 
 * @version V1.0   
 */
package cn.tinder.fuego.stub.strust.bo.assign;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.tinder.fuego.util.date.DateService;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsInfoBo;
import cn.tinder.fuego.webservice.struts.bo.assets.AssetsPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPageBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignPlanBo;
import cn.tinder.fuego.webservice.struts.bo.assign.AssignTransBo;
import cn.tinder.fuego.webservice.struts.bo.base.AssetsBo;
import cn.tinder.fuego.webservice.struts.bo.base.DeptInfoBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

/**
 * @ClassName: AssignBoStub
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-9-25 下午10:10:51
 * 
 */

public class AssignBoStub
{
	public static List<DeptInfoBo> getDeptInfoList()
	{
		List<DeptInfoBo> deptList = new ArrayList<DeptInfoBo>();

		DeptInfoBo dept = new DeptInfoBo();
		dept.setName("中国石化");

		for (int i = 0; i < 10; i++)
		{
			DeptInfoBo dept1 = new DeptInfoBo();
			dept1.setName("中国石油" + i);

			deptList.add(dept1);
		}

		deptList.add(dept);

		return deptList;
	}

	public static AssignPageBo getAssignPageBo()
	{
		AssignPageBo assignPage = new AssignPageBo();
		assignPage.setDeptInfoList(getDeptInfoList());

		assignPage.setAssignPlan(getAssignPlanBo());
		assignPage.getAssignPlan().setAssetsPage(getAssetsPage());
		return assignPage;
	}

	public static AssignPlanBo getAssignPlanBo()
	{
		AssignPlanBo assignPlan = new AssignPlanBo();
		AssignTransBo trans = new AssignTransBo();
		assignPlan.setTransInfo(trans);

		assignPlan.getTransInfo().setTransInfo(getTransactionBo());
		assignPlan.getTransInfo().setInDept("加油站1");
		assignPlan.getTransInfo().setOutDept("加油站2");
		assignPlan.getTransInfo().setInDeptStatus("未确认");

		return assignPlan;
	}

	public static TransactionBaseInfoBo getTransactionBo()
	{
		TransactionBaseInfoBo trans = new TransactionBaseInfoBo();
		trans.setTransID("T000001");
		trans.setTransName("调拨计划");
		trans.setCreateUser("admin");

		trans.setCreateTime(new Date(System.currentTimeMillis()));

		return trans;

	}

	public static AssetsPageBo getAssetsPage()
	{
		AssetsPageBo assetsPage = new AssetsPageBo();
		assetsPage.setShowCheckBox(true);

		for (int i = 0; i < 10; i++)
		{
			AssetsInfoBo assetsInfo = new AssetsInfoBo();
			assetsInfo.setAssets(getAssetsBo());
			assetsPage.getAssetsList().add(assetsInfo);
		}
		return assetsPage;

	}

	public static AssetsBo getAssetsBo()
	{
		AssetsBo assets = new AssetsBo();
		assets.setAssetsID("12345");
		assets.setAssetsName("电脑");
		assets.setAssetsType("易耗品");
		assets.setCheckDate(DateService.DateToString(new Date(System.currentTimeMillis())));

		return assets;
	}

}
