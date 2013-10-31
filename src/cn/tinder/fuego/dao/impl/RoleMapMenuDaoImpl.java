package cn.tinder.fuego.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import cn.tinder.fuego.dao.RoleMapMenuDao;
import cn.tinder.fuego.dao.hibernate.util.HibernateUtil;
import cn.tinder.fuego.domain.po.RoleMapMenu;

public class RoleMapMenuDaoImpl implements RoleMapMenuDao
{
	private static final Log log = LogFactory.getLog(RoleMapMenuDaoImpl.class);

	public void create(RoleMapMenu roelMenu)
	{
		try
		{
			HibernateUtil.add(roelMenu);
		} catch (RuntimeException re)
		{
			throw re;
		} finally
		{
			HibernateUtil.closeSession();
		}
	}

	@Override
	public List<Integer> getMenuIDByRole(String role)
	{
		List<RoleMapMenu> roleMenuList;
		Session s = null;
		try
		{
			s = HibernateUtil.getSession();

			Criteria c = s.createCriteria(RoleMapMenu.class);
			c.add(Restrictions.eq("role", role));
			roleMenuList = c.list();

		} catch (RuntimeException e)
		{
			log.error("get menu id by role failed." + role, e);
			throw e;
		} finally
		{
			if (s != null)
			{
				s.close();
			}
		}
		List<Integer> menuList = new ArrayList<Integer>();
		for (RoleMapMenu roleMenu : roleMenuList)
		{
			menuList.add(roleMenu.getMenuID());
		}
		return menuList;
	}

}
