package com.ethereal.stopit.objects;

import java.io.Serializable;

public class Event implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	int id;
	String eventName;
	String name;
	String workingPlace;
	String district;
	String upzilla;
	String thana;
	String description;
	String proofLink;
	String created_by;
	int voteYes;
	int voteNo;
	public Event(){
		
	}
	public Event(int id, String name, String workingPlace, String district,
			String upzilla, String thana, String description, String proofLink,
			String created_by) {
		this.id = id;
		this.name = name;
		this.workingPlace = workingPlace;
		this.district = district;
		this.upzilla = upzilla;
		this.thana = thana;
		this.description = description;
		this.proofLink = proofLink;
		this.created_by = created_by;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWorkingPlace() {
		return workingPlace;
	}
	public void setWorkingPlace(String workingPlace) {
		this.workingPlace = workingPlace;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getUpzilla() {
		return upzilla;
	}
	public void setUpzilla(String upzilla) {
		this.upzilla = upzilla;
	}
	public String getThana() {
		return thana;
	}
	public void setThana(String thana) {
		this.thana = thana;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getProofLink() {
		return proofLink;
	}
	public void setProofLink(String proofLink) {
		this.proofLink = proofLink;
	}
	public String getCreated_by() {
		return created_by;
	}
	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}
	public int getVoteYes() {
		return voteYes;
	}
	public void setVoteYes(int voteYes) {
		this.voteYes = voteYes;
	}
	public int getVoteNo() {
		return voteNo;
	}
	public void setVoteNo(int voteNo) {
		this.voteNo = voteNo;
	}
}
