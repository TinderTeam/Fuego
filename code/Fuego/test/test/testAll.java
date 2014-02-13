package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.testgroup.AllActionTest;
import test.testgroup.AllServiceTest;

@RunWith(Suite.class)  
@SuiteClasses({AllServiceTest.class,AllActionTest.class}) 

public class testAll {

}
