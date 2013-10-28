package cn.tinder.fuego.webservice.struts.form;

import java.util.List;

import org.apache.struts.action.ActionForm;

public class GasAssetsApplyForm extends ActionForm{
	
	
	
	private List<String> techList; 	//TechList(Status change) 
	private List<String> noteList;	//NoteList (Status change)
	private String type;			//type choose (apply)
	private String assetName;		
	private String number;
	private String reason;
	
	
	public List<String> getTechList() {
		return techList;
	}
	public void setTechList(List<String> techList) {
		this.techList = techList;
	}
	public List<String> getNoteList() {
		return noteList;
	}
	public void setNoteList(List<String> noteList) {
		this.noteList = noteList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAssetName() {
		return assetName;
	}
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	@Override
	public String toString() {
		return "GasAssetsApplyForm [techList=" + techList + ", noteList="
				+ noteList + ", type=" + type + ", assetName=" + assetName
				+ ", number=" + number + ", reason=" + reason + "]";
	}
	
	
}
