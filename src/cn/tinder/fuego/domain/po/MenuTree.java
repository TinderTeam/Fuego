package cn.tinder.fuego.domain.po;

public class MenuTree
{
	private int menuID;
	private String name;
	private String value;
	private String menuCss;
	private String iconCss;
	private String url;
	private int parentID;

	public int getMenuID()
	{
		return menuID;
	}

	public void setMenuID(int menuID)
	{
		this.menuID = menuID;
	}

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

	public int getParentID()
	{
		return parentID;
	}

	public void setParentID(int parentID)
	{
		this.parentID = parentID;
	}

	@Override
	public String toString()
	{
		return "MenuTree [menuID=" + menuID + ", name=" + name + ", value=" + value + ", menuCss=" + menuCss + ", iconCss=" + iconCss + ", url=" + url + ", parentID=" + parentID + "]";
	}

}
