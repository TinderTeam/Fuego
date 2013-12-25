package cn.tinder.fuego.service.cache;

import org.junit.Test;


public class CacheContextTest {
	@Test
	public void test(){
		HandelDepartmentAssetsStyleCache test = CacheContext.getInstance().getHandelDepartmentAssetsStyleCache();
		
		System.out.println("根据类型获取归口管理部门："+test.getDeptByType("低值易耗品"));
		System.out.println("根据归口管理部门获取类型列表："+test.getTypeByDept("零售管理部"));
		
	}
	
}
