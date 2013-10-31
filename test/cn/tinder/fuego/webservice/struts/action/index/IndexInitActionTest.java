package cn.tinder.fuego.webservice.struts.action.index;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

import cn.tinder.fuego.webservice.struts.bo.base.UserNoticeBo;
import cn.tinder.fuego.webservice.struts.bo.index.IndexBo;
import cn.tinder.fuego.webservice.struts.bo.trans.TransactionBaseInfoBo;

public class IndexInitActionTest
{
	private static final Log log = LogFactory.getLog(IndexInitActionTest.class);

	public static List<UserNoticeBo> getTestNoticeList()
	{
		List<UserNoticeBo> list = new ArrayList<UserNoticeBo>();
		for(int i = 0;i<100;i++){
			UserNoticeBo n = new UserNoticeBo();			
			n.setUrl("www.baidu.com");
			TransactionBaseInfoBo bo = new TransactionBaseInfoBo();
			bo.setCreateTime(new Date(System.currentTimeMillis()));
			bo.setState("状态"+i);
			bo.setHandleUser("处理者"+i);
			bo.setCreateUser("创建者"+i);
			bo.setTransName("事务名称");
 
			list.add(n);
			
		}
		

		return list;

	}

	@Test
	public void tst()
	{
		log.warn(IndexInitActionTest.getTestNoticeList());
	}
}
