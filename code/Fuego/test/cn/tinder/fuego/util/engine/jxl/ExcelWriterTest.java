package cn.tinder.fuego.util.engine.jxl;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.tinder.fuego.stub.strust.bo.base.ComplicatedSystemUserBoStub;
import cn.tinder.fuego.stub.strust.bo.base.SystemUserBoStub;
import cn.tinder.fuego.webservice.struts.bo.base.SystemUserBo;

public class ExcelWriterTest {

	@Test
	public void testExcelWriter() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcelWriterObjectString() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcelWriterObjectFile() {
		SystemUserBo ob =SystemUserBoStub.getSuperDeptSystemUser();
		try {
			Class<?> cla=Class.forName(ob.getClass().getName());
			System.out.print(cla.getField("this"));
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fail("Not yet implemented");
	}

	
	
	@Test
	public void testImplement2() {
		ComplicatedSystemUserBoStub b =ComplicatedSystemUserBoStub.getSuperDeptSystemUser();
		ComplicatedSystemUserBoStub c =ComplicatedSystemUserBoStub.getSuperDeptSystemUser();
		List<ComplicatedSystemUserBoStub> l = new ArrayList<ComplicatedSystemUserBoStub>();
		l.add(c);
		l.add(b);
		
		String[] title={"姓名","密码",null,"部门","角色","经管部","id"};
		
		
		ExcelWriter a = new ExcelWriter(l,title,"f:/test.xls");
		
	}
}
