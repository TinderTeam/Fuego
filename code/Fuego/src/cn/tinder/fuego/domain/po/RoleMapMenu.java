package cn.tinder.fuego.domain.po;

import java.io.Serializable;

public class RoleMapMenu implements Serializable
{
	private String role;
	private int menuID;

	public String getRole()
	{
		return role;
	}

	public void setRole(String role)
	{
		this.role = role;
	}

	public int getMenuID()
	{
		return menuID;
	}

	public void setMenuID(int menuID)
	{
		this.menuID = menuID;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + menuID;
		result = prime * result + ((role == null) ? 0 : role.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RoleMapMenu other = (RoleMapMenu) obj;
		if (menuID != other.menuID)
			return false;
		if (role == null)
		{
			if (other.role != null)
				return false;
		} else if (!role.equals(other.role))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "RoleMapMenu [role=" + role + ", menuID=" + menuID + "]";
	}

}
