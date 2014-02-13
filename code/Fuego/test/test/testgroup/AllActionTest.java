package test.testgroup;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import cn.tinder.fuego.webservice.struts.action.purchaseplan.PurchasePlanActionTest;
import cn.tinder.fuego.webservice.struts.action.purchaseplan.PurchasePlanInitActionTest;


@RunWith(Suite.class)  
@SuiteClasses({PurchasePlanActionTest.class,PurchasePlanInitActionTest.class}) 

public class AllActionTest {

}
