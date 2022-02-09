package com.code.challange.serviceInterfaces;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

public interface AuthServiceInterface {

	public String establishSpotifyAuthSystem();

	public void createSpotifyAuthToken(String authenticationCode) throws ParseException, SpotifyWebApiException, IOException;

	public SpotifyApi returnAccessToken();
}
