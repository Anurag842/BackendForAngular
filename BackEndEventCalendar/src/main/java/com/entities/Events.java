package com.entities;



import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;




@Entity
@Table
public class Events {
	
	
	@Id
	@GeneratedValue
	private int eventId;
	
	@NotEmpty(message="Cannot be empty")
	@Column(name="eventType")
	private String title;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm")
	@Column(name="startTimeDate")
	private Date start;
	
	@DateTimeFormat(pattern="yyyy-MM-dd hh:mm")
	@Column(name="endTimeDate")
	private Date end;
	
	@ManyToOne(cascade=CascadeType.MERGE)
	private Client cli;
	

	
	
	public int getEventId() {
		return eventId;
	}
	public void setEventId(int eventId) {
		this.eventId = eventId;
	}
	
	public Client getCli() {
		return cli;
	}
	public void setCli(Client cli) {
		this.cli = cli;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "Events [eventId=" + eventId + ", title=" + title + ", start=" + start + ", end=" + end + ", cli=" + cli
				+ "]";
	}
	
	
	
	
}
