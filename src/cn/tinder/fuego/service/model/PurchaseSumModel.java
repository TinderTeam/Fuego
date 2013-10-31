/**   
* @Title: PurchaseSumModel.java 
* @Package cn.tinder.fuego.service.model 
* @Description: TODO
* @author Tang Jun   
* @date 2013-10-4 下午07:15:22 
* @version V1.0   
*/ 
package cn.tinder.fuego.service.model;

/** 
 * @ClassName: PurchaseSumModel 
 * @Description: TODO
 * @author Tang Jun
 * @date 2013-10-4 下午07:15:22 
 *  
 */

public class PurchaseSumModel
{
	private String assetsName;
	private String manufacture;
	private String spec;
	public String getAssetsName()
	{
		return assetsName;
	}
	public void setAssetsName(String assetsName)
	{
		this.assetsName = assetsName;
	}
	public String getManufacture()
	{
		return manufacture;
	}
	public void setManufacture(String manufacture)
	{
		this.manufacture = manufacture;
	}
	public String getSpec()
	{
		return spec;
	}
	public void setSpec(String spec)
	{
		this.spec = spec;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assetsName == null) ? 0 : assetsName.hashCode());
		result = prime * result
				+ ((manufacture == null) ? 0 : manufacture.hashCode());
		result = prime * result + ((spec == null) ? 0 : spec.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PurchaseSumModel other = (PurchaseSumModel) obj;
		if (assetsName == null) {
			if (other.assetsName != null)
				return false;
		} else if (!assetsName.equals(other.assetsName))
			return false;
		if (manufacture == null) {
			if (other.manufacture != null)
				return false;
		} else if (!manufacture.equals(other.manufacture))
			return false;
		if (spec == null) {
			if (other.spec != null)
				return false;
		} else if (!spec.equals(other.spec))
			return false;
		return true;
	}
	@Override
	public String toString()
	{
		return "PurchaseSumModel [assetsName=" + assetsName + ", manufacture=" + manufacture + ", spec=" + spec + "]";
	}
	
	

}
