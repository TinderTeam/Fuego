package cn.tinder.fuego.domain.po;

import java.io.Serializable;

/**
 * 
* @ClassName: AssetsPrice 
* @Description: TODO
* @author Nan Bowen
* @date 2013-11-22 上午01:14:24 
*
 */
public class AssetsPrice implements Serializable
{
	private String assetsName;
	private String manufacture;
	private String spec;
	private float price;
	/**
	 * @return the assetsName
	 */
	public String getAssetsName() {
		return assetsName;
	}
	/**
	 * @param assetsName the assetsName to set
	 */
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	/**
	 * @return the manufacture
	 */
	public String getManufacture() {
		return manufacture;
	}
	/**
	 * @param manufacture the manufacture to set
	 */
	public void setManufacture(String manufacture) {
		this.manufacture = manufacture;
	}
	/**
	 * @return the spec
	 */
	public String getSpec() {
		return spec;
	}
	/**
	 * @param spec the spec to set
	 */
	public void setSpec(String spec) {
		this.spec = spec;
	}
	/**
	 * @return the price
	 */
	public float getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssetsPrice [assetsName=" + assetsName + ", manufacture="
				+ manufacture + ", spec=" + spec + ", price=" + price + "]";
	}


}
