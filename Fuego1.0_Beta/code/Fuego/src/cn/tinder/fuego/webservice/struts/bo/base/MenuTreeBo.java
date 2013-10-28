package cn.tinder.fuego.webservice.struts.bo.base;

import java.util.List;

public class MenuTreeBo
{
	private String name;
	private String value;
	private String menuCss;
	private String iconCss;
	private String url;
	private List<MenuTreeBo> childItemList;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public String getMenuCss()
	{
		return menuCss;
	}

	public void setMenuCss(String menuCss)
	{
		this.menuCss = menuCss;
	}

	public String getIconCss()
	{
		return iconCss;
	}

	public void setIconCss(String iconCss)
	{
		this.iconCss = iconCss;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public List<MenuTreeBo> getChildItemList()
	{
		return childItemList;
	}

	public void setChildItemList(List<MenuTreeBo> childItemList)
	{
		this.childItemList = childItemList;
	}

	@Override
	public String toString()
	{
		return "MenuItemTree [name=" + name + ", value=" + value + ", menuCss=" + menuCss + ", iconCss=" + iconCss + ", url=" + url + ", childItemList=" + childItemList + "]";
	}

}
