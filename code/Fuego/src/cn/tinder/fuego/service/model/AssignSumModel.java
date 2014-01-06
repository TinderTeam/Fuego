package cn.tinder.fuego.service.model;

public class AssignSumModel {
	String assetsID;
	String assetsOutGas;
	String assetsInGas;
	String dept;
	String note;
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the assetsInGas
	 */
	public String getAssetsInGas() {
		return assetsInGas;
	}
	/**
	 * @param assetsInGas the assetsInGas to set
	 */
	public void setAssetsInGas(String assetsInGas) {
		this.assetsInGas = assetsInGas;
	}
	/**
	 * @return the assetsID
	 */
	public String getAssetsID() {
		return assetsID;
	}
	/**
	 * @param assetsID the assetsID to set
	 */
	public void setAssetsID(String assetsID) {
		this.assetsID = assetsID;
	}
	/**
	 * @return the assetsOutGas
	 */
	public String getAssetsOutGas() {
		return assetsOutGas;
	}
	/**
	 * @param assetsOutGas the assetsOutGas to set
	 */
	public void setAssetsOutGas(String assetsOutGas) {
		this.assetsOutGas = assetsOutGas;
	}
	/**
	 * @return the dept
	 */
	public String getDept() {
		return dept;
	}
	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assetsID == null) ? 0 : assetsID.hashCode());
		result = prime * result
				+ ((assetsInGas == null) ? 0 : assetsInGas.hashCode());
		result = prime * result
				+ ((assetsOutGas == null) ? 0 : assetsOutGas.hashCode());
		result = prime * result + ((dept == null) ? 0 : dept.hashCode());
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
		AssignSumModel other = (AssignSumModel) obj;
		if (assetsID == null) {
			if (other.assetsID != null)
				return false;
		} else if (!assetsID.equals(other.assetsID))
			return false;
		if (assetsInGas == null) {
			if (other.assetsInGas != null)
				return false;
		} else if (!assetsInGas.equals(other.assetsInGas))
			return false;
		if (assetsOutGas == null) {
			if (other.assetsOutGas != null)
				return false;
		} else if (!assetsOutGas.equals(other.assetsOutGas))
			return false;
		if (dept == null) {
			if (other.dept != null)
				return false;
		} else if (!dept.equals(other.dept))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */

	
}
