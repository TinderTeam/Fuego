package cn.tinder.fuego.domain.po;

public class TransEventType
{
	private String type;
	private int step;
	private int currentID;

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @return the step
	 */
	public int getStep()
	{
		return step;
	}

	/**
	 * @param step
	 *            the step to set
	 */
	public void setStep(int step)
	{
		this.step = step;
	}

	/**
	 * @param currentID
	 *            the currentID to set
	 */
	public void setCurrentID(int currentID)
	{
		this.currentID = currentID;
	}

	/**
	 * @return the currentID
	 */
	public int getCurrentID()
	{
		return currentID;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return "TransEventType [currentID=" + currentID + ", step=" + step + ", type=" + type + "]";
	}

}
