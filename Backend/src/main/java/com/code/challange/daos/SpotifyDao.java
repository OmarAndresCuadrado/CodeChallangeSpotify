package com.code.challange.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.code.challange.domains.TrackModel;

public interface SpotifyDao extends JpaRepository<TrackModel, Long> {
	
	@Query(value = "select * from tracks where trackiscr = ?1", nativeQuery = true)
	TrackModel findTrackByisrc(String isrc);

}
