package test.testgroup;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.cn.tinder.fuego.webservice.struts.action.purchaseplan.purchaseplanaction.PurchasePlanActionBasicTest;

@RunWith(Suite.class)  
@SuiteClasses({PurchasePlanActionBasicTest.class}) 
public class AllActionTest {

}
