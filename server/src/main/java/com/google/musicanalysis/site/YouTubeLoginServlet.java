package com.google.musicanalysis.site;

import java.net.URI;
import java.util.logging.Logger;
import com.google.musicanalysis.util.Constants;
import javax.servlet.annotation.WebServlet;

@WebServlet("/api/oauth/login/youtube")
public class YouTubeLoginServlet extends OAuthLoginServlet {
  private static final Logger LOGGER = Logger.getLogger(YouTubeLoginServlet.class.getName());

  @Override
  protected String getServiceName() {
    return "youtube";
  }

  @Override
  protected String getAuthUri() {
    return "https://accounts.google.com/o/oauth2/v2/auth";
  }

  @Override
  protected String getClientId() {
    return Constants.YOUTUBE_CLIENT_ID;
  }

  @Override
  protected String[] getScopes() {
    return new String[] {"https://www.googleapis.com/auth/youtube.readonly"};
  }

  @Override
  protected String getRedirectUri() {
    // URI that user should be redirected to after logging in
    try {
      var domainUri = URI.create(System.getenv().get("DOMAIN"));
      return domainUri.resolve("/api/oauth/callback/youtube").toString();
    } catch (NullPointerException e) {
      LOGGER.severe("The DOMAIN environment variable is not set.");
      throw e;
    }
  }

  @Override
  protected String getSessionServiceKey() {
    return Constants.YOUTUBE_SESSION_KEY;
  }
}
