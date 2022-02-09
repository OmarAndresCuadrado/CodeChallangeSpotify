package com.code.challange.controllers;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.code.challange.serviceInterfaces.AuthServiceInterface;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class AuthController {

	private AuthServiceInterface authService;

	@Autowired
	public AuthController(AuthServiceInterface authService) {
		this.authService = authService;
	}

	@GetMapping("/auth")
	public String spotifyLoginPage() {
		return authService.establishSpotifyAuthSystem();
	}

	@GetMapping("/get-token")
	public void getSpotifyAuthenticationToken(@RequestParam("code") String authenticationCode)
			throws ParseException, SpotifyWebApiException, IOException {
		authService.createSpotifyAuthToken(authenticationCode);
	}

}
