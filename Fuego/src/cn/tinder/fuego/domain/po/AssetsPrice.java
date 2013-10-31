package cn.tinder.fuego.domain.po;

/**
 * 
 * @ClassName: AssetsPrice
 * @Description:
 * @author Zhu Liucao
 * @date 2013-9-24 上午12:35:37
 * 
 */
public class AssetsPrice
{
	private String spec;
	private float price;

	public String getSpec()
	{
		return spec;
	}

	public void setSpec(String spec)
	{
		this.spec = spec;
	}

	public float getPrice()
	{
		return price;
	}

	public void setPrice(float price)
	{
		this.price = price;
	}

	@Override
	public String toString()
	{
		return "AssetsPrice [spec=" + spec + ", price=" + price + "]";
	}

}
