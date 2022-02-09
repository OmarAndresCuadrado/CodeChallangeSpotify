package com.code.challange.serviceInterfaces;

import java.util.List;

import com.code.challange.domains.TrackModel;

public interface SpotifyServiceInterface {
	
	public List<TrackModel> listAllTracksOnDatabase();
	
	public TrackModel findTrackModelByisrc(String isrc);
	
	public TrackModel saveTrackModel(TrackModel trackModel);

}
