package cn.tinder.fuego.dao;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import cn.tinder.fuego.domain.po.AssetsFix;
public class AssetsFixDaoTest {
	Log log = LogFactory.getLog(AssetsFixDaoTest.class);
	ApplicationContext ctx = new FileSystemXmlApplicationContext("/resource/assetsFixBean.xml");
	AssetsFix f =(AssetsFix) ctx.getBean("assetsFix");
	AssetsFixDao dao = (AssetsFixDao) ctx.getBean("assetsFixDao");
	
//@Test
	public void test(){
		f.setIndexNumber("Test");
		dao.create(f);
		log.info(f.toString());
		
	}

//@Test
public void test2(){
	f.setIndexNumber("Test");
	log.info(f.toString());
	dao.delete(f);
	
	
}
}
