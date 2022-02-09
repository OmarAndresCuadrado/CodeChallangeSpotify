package com.code.challange.serviceInterfaceImplementations;

import java.io.IOException;
import java.net.URI;

import org.apache.hc.core5.http.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.code.challange.constants.AuthenticationConstant;
import com.code.challange.controllers.AuthController;
import com.code.challange.serviceInterfaces.AuthServiceInterface;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import se.michaelthelin.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;

@Service
public class AuthServiceImplementation implements AuthServiceInterface {

	private final Logger log = LoggerFactory.getLogger(AuthController.class);
	private SpotifyApi spotifyClient;

	@Override
	public String establishSpotifyAuthSystem() {
		spotifyClient = createSpotifyClient();
		AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyClient.authorizationCodeUri()
				.scope("user-read-private, user-read-email, user-top-read").show_dialog(true).build();

		URI uri = authorizationCodeUriRequest.execute();
		return uri.toString();
	}

	@Override
	public void createSpotifyAuthToken(String authenticationCode)
			throws ParseException, SpotifyWebApiException, IOException {

		String codeResponse = authenticationCode;
		AuthorizationCodeRequest authorizationCodeRequest = spotifyClient.authorizationCode(codeResponse).build();
		AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest.execute();

		spotifyClient.setAccessToken(authorizationCodeCredentials.getAccessToken());
		spotifyClient.setRefreshToken(authorizationCodeCredentials.getRefreshToken());

		log.info("accessToken " + authorizationCodeCredentials.getAccessToken());
	}

	public SpotifyApi createSpotifyClient() {
		SpotifyApi spotifyClient = new SpotifyApi.Builder().setClientId(AuthenticationConstant.publicId)
				.setClientSecret(AuthenticationConstant.privateKey).setRedirectUri(AuthenticationConstant.redirectUrl)
				.build();

		return spotifyClient;
	}

	@Override
	public SpotifyApi returnAccessToken() {
		return spotifyClient;
	}

}
