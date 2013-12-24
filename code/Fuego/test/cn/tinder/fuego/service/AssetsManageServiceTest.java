package cn.tinder.fuego.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.domain.po.PhysicalAssetsStatus;
import cn.tinder.fuego.service.impl.AssetsManageServiceImpl;


public class AssetsManageServiceTest {
	AssetsManageService s = new AssetsManageServiceImpl();
	
	@Test
	public void initAssetsIDTest(){
		PhysicalAssetsStatus a1 = new PhysicalAssetsStatus();
		a1.setAssetsType("固定资产");
		a1.setAssetsID("testID");
		
		PhysicalAssetsStatus a2 = new PhysicalAssetsStatus();
		a2.setAssetsType("固定资产");
	
		PhysicalAssetsStatus a3= new PhysicalAssetsStatus();
		a3.setAssetsType("消防器材");
		
		PhysicalAssetsStatus a4= new PhysicalAssetsStatus();
		a4.setAssetsType("消防器材")
		
		;
		List<PhysicalAssetsStatus> list = new ArrayList<PhysicalAssetsStatus>();
		list.add(a1);
		list.add(a2);
		list.add(a3);
		list.add(a4);
		
		System.out.println(list);
		List<PhysicalAssetsStatus> list2=s.initAssetsID(list);
		
		System.out.println(list2);
		
	}
}
