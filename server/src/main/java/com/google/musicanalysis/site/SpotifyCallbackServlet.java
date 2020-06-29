package com.google.musicanalysis.site;

import com.google.musicanalysis.util.Constants;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/oauth/callback/spotify")
public class SpotifyCallbackServlet extends OAuthCallbackServlet {
  @Override
  protected String getServiceName() {
    return "spotify";
  }

  @Override
  protected String getClientId() {
    return Constants.SPOTIFY_CLIENT_ID;
  }

  @Override
  protected String getClientSecret() {
    return System.getenv().get("SPOTIFY_CLIENT_SECRET");
  }

  @Override
  protected String getTokenUri() {
    return "https://accounts.spotify.com/api/token";
  }

  @Override
  protected String getRedirectUri() {
    return System.getenv().get("SPOTIFY_CALLBACK_URI");
  }

  @Override
  protected String getSessionServiceKey() {
    return Constants.SPOTIFY_SESSION_KEY;
  }
}