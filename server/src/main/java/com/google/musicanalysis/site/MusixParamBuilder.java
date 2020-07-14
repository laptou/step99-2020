package com.google.musicanalysis.site;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * Builds parameter strings based off what the user wants. This is structured the way it is becuase
 * I will be adding more to each function. I will clean it up later as well.
 */
public class MusixParamBuilder {

  private String requestType;

  // All possible parameters I can receive
  private String trackId;
  private String trackName;
  private String albumId;
  private String artistName;

  /**
   * Constructor.
   *
   * @param requestType determines what action we will send to the API
   * @param params array of parameters which get assigned to different values depending on
   *     requestType
   */
  public MusixParamBuilder(String requestType, String... params) {
    this.requestType = requestType;

    switch (requestType) {
      case "track.search":
        this.trackName = params[0];
        this.artistName = params[1];
        break;
      case "track.lyrics.get":
        this.trackId = params[0];
        break;
      default:
        throw new IllegalArgumentException("arguments required");
    }
  }

  /** Filters out the correct parameter string and returns it */
  public String getFilteredParams() {
    switch (this.requestType) {
      case "track.search":
        return buildTrackString(this.trackName, this.artistName);
      case "track.lyrics.get":
        return buildLyricsString(this.trackId);
    }
    return null;
  }

  /**
   * Builds string to serve to request (track search)
   *
   * @param trackName name of the desired track
   * @param artistName name of the desired artist
   */
  private String buildTrackString(String trackName, String artistName) {
    // ready string for sending
    trackName = URLEncoder.encode(trackName, StandardCharsets.UTF_8);
    artistName = URLEncoder.encode(artistName, StandardCharsets.UTF_8);
    return "q_track=" + trackName + "&q_artist=" + artistName + "&quorum_factor=1";
  }

  /**
   * Builds string to serve to request (lyric search)
   *
   * @param trackId id of the desired track
   */
  private String buildLyricsString(String trackId) {
    trackId = URLEncoder.encode(trackId, StandardCharsets.UTF_8);
    return "track_id=" + trackId;
  }
}