package cn.tinder.fuego.webservice.util;

import java.util.ArrayList;

/**
 * 
 * @ClassName: MyArrayList
 * @Description: TODO
 * @author Nan Bowen
 * @date 2013-9-26 下午10:42:53
 * 
 */
public class MyArrayList extends ArrayList
{
	private Class itemClass;

	public MyArrayList(Class itemClass)
	{
		this.itemClass = itemClass;
	}

	public Object get(int index)
	{
		try
		{
			while (index >= size())
			{
				add(itemClass.newInstance());
			}
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return super.get(index);
	}

}
