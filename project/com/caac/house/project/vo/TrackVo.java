package com.caac.house.project.vo;

import java.util.List;

public class TrackVo {
	private SupervisorVo supervisorInfo;
	private List<TrackInfoVo> trackInfo;

	public List<TrackInfoVo> getTrackInfo() {
		return trackInfo;
	}

	public void setTrackInfo(List<TrackInfoVo> trackInfo) {
		this.trackInfo = trackInfo;
	}

	public SupervisorVo getSupervisorInfo() {
		return supervisorInfo;
	}

	public void setSupervisorInfo(SupervisorVo supervisorInfo) {
		this.supervisorInfo = supervisorInfo;
	}

}
