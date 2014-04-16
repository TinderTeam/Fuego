package cn.tinder.fuego.domain.po;

public class AssetsType
{
	private String typeID;
	private String typeName;
	private String attrID;
	private String attrName;
	private String deptID;
	private int currentID;
	private String prefix;

	/**
	 * @return the typeID
	 */
	public String getTypeID()
	{
		return typeID;
	}

	/**
	 * @param typeID
	 *            the typeID to set
	 */
	public void setTypeID(String typeID)
	{
		this.typeID = typeID;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName()
	{
		return typeName;
	}

	/**
	 * @param typeName
	 *            the typeName to set
	 */
	public void setTypeName(String typeName)
	{
		this.typeName = typeName;
	}

	/**
	 * @return the attrID
	 */
	public String getAttrID()
	{
		return attrID;
	}

	/**
	 * @param attrID
	 *            the attrID to set
	 */
	public void setAttrID(String attrID)
	{
		this.attrID = attrID;
	}

	/**
	 * @return the attrName
	 */
	public String getAttrName()
	{
		return attrName;
	}

	/**
	 * @param attrName
	 *            the attrName to set
	 */
	public void setAttrName(String attrName)
	{
		this.attrName = attrName;
	}

	/**
	 * @return the deptID
	 */
	public String getDeptID()
	{
		return deptID;
	}

	/**
	 * @param deptID
	 *            the deptID to set
	 */
	public void setDeptID(String deptID)
	{
		this.deptID = deptID;
	}

	public int getCurrentID()
	{
		return currentID;
	}

	public void setCurrentID(int currentID)
	{
		this.currentID = currentID;
	}

	public String getPrefix()
	{
		return prefix;
	}

	public void setPrefix(String prefix)
	{
		this.prefix = prefix;
	}

	@Override
	public String toString()
	{
		return "AssetsType [typeID=" + typeID + ", typeName=" + typeName + ", attrID=" + attrID + ", attrName=" + attrName + ", deptID=" + deptID + ", currentID=" + currentID + ", prefix=" + prefix
				+ "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attrID == null) ? 0 : attrID.hashCode());
		result = prime * result + ((attrName == null) ? 0 : attrName.hashCode());
		result = prime * result + currentID;
		result = prime * result + ((deptID == null) ? 0 : deptID.hashCode());
		result = prime * result + ((prefix == null) ? 0 : prefix.hashCode());
		result = prime * result + ((typeID == null) ? 0 : typeID.hashCode());
		result = prime * result + ((typeName == null) ? 0 : typeName.hashCode());
		return result;
	}

	 

 
	

	 
}
