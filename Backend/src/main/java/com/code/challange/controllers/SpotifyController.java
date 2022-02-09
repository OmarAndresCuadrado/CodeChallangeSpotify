package com.code.challange.controllers;

import java.io.IOException;
import java.util.List;

import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.code.challange.domains.TrackModel;
import com.code.challange.exceptions.AlreadyExistTrackException;
import com.code.challange.exceptions.AuthenticationExecption;
import com.code.challange.serviceInterfaces.AuthServiceInterface;
import com.code.challange.serviceInterfaces.SpotifyServiceInterface;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.special.SearchResult;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.SearchItemRequest;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class SpotifyController {

	private final Logger log = LoggerFactory.getLogger(SpotifyController.class);

	private SpotifyServiceInterface spotifyService;
	private AuthServiceInterface authService;

	@Autowired
	public SpotifyController(SpotifyServiceInterface spotifyService, AuthServiceInterface authService) {
		this.spotifyService = spotifyService;
		this.authService = authService;
	}

	@PostMapping("/createTrack")
	public ResponseEntity<?> createRecordAboutTheTrackToTheDatabase(@RequestParam String isrc)
			throws ParseException, SpotifyWebApiException, IOException {

		TrackModel track = new TrackModel();
		Track trackToBeSave = null;

		TrackModel trackFromDaatabase = spotifyService.findTrackModelByisrc(isrc);

		if (trackFromDaatabase != null && trackFromDaatabase.getTrackiscr().equals(isrc)) {
			AlreadyExistTrackException alreadyExistException = new AlreadyExistTrackException(
					"The track with the isrc " + isrc + " already exist on the database");
			return new ResponseEntity<String>(alreadyExistException.getMessage(), HttpStatus.BAD_REQUEST);
		}

		SpotifyApi spotifyClient = authService.returnAccessToken();

		if (spotifyClient == null) {
			AuthenticationExecption authenticationExistException = new AuthenticationExecption(
					"Full authentication is needed");
			return new ResponseEntity<String>(authenticationExistException.getMessage(), HttpStatus.UNAUTHORIZED);
		}

		SearchItemRequest searchItemRequest = spotifyClient.searchItem("isrc:".concat(isrc), "track").build();
		SearchResult searchResult = searchItemRequest.execute();
		trackToBeSave = searchResult.getTracks().getItems()[0];
		String albumImage = trackToBeSave.getAlbum().getImages()[0].getUrl();
		String isrcArgument = isrc;

		track.setTrackName(trackToBeSave.getName());
		track.setTrackDuration(trackToBeSave.getDurationMs());
		track.setTrackExplicit(trackToBeSave.getIsExplicit());
		track.setTrackImage(albumImage);
		track.setTrackiscr(isrcArgument);
		log.info("track model add to the database with the following isrc " + isrcArgument);

		return new ResponseEntity<TrackModel>(spotifyService.saveTrackModel(track), HttpStatus.CREATED);

	}

	@GetMapping("/getTrack")
	public TrackModel getTrackDetailsFromDatabase(@RequestParam String isrc) {
		return spotifyService.findTrackModelByisrc(isrc);

	}

	@GetMapping("/getTracks")
	public List<TrackModel> getTrackDetailsFromDatabase() {
		return spotifyService.listAllTracksOnDatabase();

	}

}
