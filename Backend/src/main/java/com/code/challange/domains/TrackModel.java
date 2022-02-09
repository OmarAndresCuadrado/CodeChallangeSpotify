package com.code.challange.domains;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tracks")
public class TrackModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String trackName;

	private Integer trackDuration;

	private boolean trackExplicit;

	private String trackiscr;

	private String trackImage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Integer getTrackDuration() {
		return trackDuration;
	}

	public void setTrackDuration(Integer trackDuration) {
		this.trackDuration = trackDuration;
	}

	public String getTrackiscr() {
		return trackiscr;
	}

	public void setTrackiscr(String trackiscr) {
		this.trackiscr = trackiscr;
	}

	public boolean isTrackExplicit() {
		return trackExplicit;
	}

	public void setTrackExplicit(boolean trackExplicit) {
		this.trackExplicit = trackExplicit;
	}

	public String getTrackImage() {
		return trackImage;
	}

	public void setTrackImage(String trackImage) {
		this.trackImage = trackImage;
	}

}
