package com.code.challange.serviceInterfaceImplementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.code.challange.daos.SpotifyDao;
import com.code.challange.domains.TrackModel;
import com.code.challange.serviceInterfaces.SpotifyServiceInterface;

@Service
public class SpotifyServiceImplemenation implements SpotifyServiceInterface {

	private SpotifyDao spotifyDao;

	@Autowired
	public SpotifyServiceImplemenation(SpotifyDao spotifyDao) {
		this.spotifyDao = spotifyDao;
	}

	@Override
	public List<TrackModel> listAllTracksOnDatabase() {
		return spotifyDao.findAll();
	}

	@Override
	public TrackModel findTrackModelByisrc(String isrc) {
		return spotifyDao.findTrackByisrc(isrc);
	}

	@Override
	public TrackModel saveTrackModel(TrackModel trackModel) {
		return spotifyDao.save(trackModel);
	}

}
