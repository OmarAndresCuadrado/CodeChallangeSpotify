package com.code.challange.constants;

import java.net.URI;

import se.michaelthelin.spotify.SpotifyHttpManager;

public class AuthenticationConstant {
	
	public static final String publicId = "acc3203686564bad89d683500f60c93c";
	public static final String privateKey = "da45f823fb8c4d9f9498b129c893d747";
	public static final URI redirectUrl = SpotifyHttpManager.makeUri("http://localhost:4200/home");
	

}
