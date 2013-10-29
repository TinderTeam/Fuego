package cn.tinder.fuego.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.MenuTreeDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.MenuTree;

public class MenuTreeDaoImpl implements MenuTreeDao
{
	private static final Log log = LogFactory.getLog(MenuTreeDaoImpl.class);

	@Override
	public List<MenuTree> getAllMenuTree()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MenuTree> getMenuByParentID(int parentID)
	{
		List<MenuTree> menuTreeList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(MenuTree.class);
			c.add(Restrictions.eq("parentID", parentID));
			menuTreeList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get menu by parent id." + parentID, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}

		return menuTreeList;
	}

}
