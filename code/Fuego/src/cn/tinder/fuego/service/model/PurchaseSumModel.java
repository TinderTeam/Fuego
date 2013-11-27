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
	/* Edit By Bowen 
	private String manufacture;
	private String spec;
	*/
	private String gasName;
	public String getAssetsName()
	{
		return assetsName;
	}
	public void setAssetsName(String assetsName)
	{
		this.assetsName = assetsName;
	}
	
	
	public String getGasName()
	{
		return gasName;
	}
	public void setGasName(String gasName)
	{
		this.gasName = gasName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assetsName == null) ? 0 : assetsName.hashCode());
		result = prime * result + ((gasName == null) ? 0 : gasName.hashCode());
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
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
		if (gasName == null) {
			if (other.gasName != null)
				return false;
		} else if (!gasName.equals(other.gasName))
			return false;
		return true;
	}
	
	
 
	

}
