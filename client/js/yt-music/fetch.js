/**
 * fetches youtube api calls from YoutubeServlet.java
 * and displays on youtube-genre.html
 */

const genreBlock = document.getElementById('genres');
const numVideosInput = document.getElementById('numVideos');
const DEFAULT_NUM_VIDS = 10;
window.data;
/**
 * extracts num vids from input, otherwise returns default
 * helper fn for displayMusicGenre()
 *
 * @returns {number} num videos user wants to retrieve
 */
function getNumVids() {
  const numVideos = numVideosInput.value;
  return numVideos || DEFAULT_NUM_VIDS;
}

/**
 * fetches genre count hashmap from /api/youtube and updates html
 */
async function displayMusicGenre() {
  const numVideos = getNumVids();

  // keep track of num_videos in URL w/o reload
  history.pushState('', '', `youtube-genre.html?num_videos=${numVideos}`);

  const response = await fetch(`/api/youtube?num_videos=${numVideos}`);
  if (response.status == 401) {
    // no oauth login so redirect to new page
    window.open('/api/oauth/login/youtube');
  }

  const genreCount = await response.text();
  genreBlock.innerHTML = genreCount;

  window.data = JSON.parse(genreCount);
  console.log(window.data);

  // call function in section.js here
  // - render charts -- hidden
  // page doesn't load until everything is loading
}

/**
 * updates value in num videos input
 * by extracting num_videos param in URL
 * otherwise if no num_videos param does nothing
 */
function updateNumVids() {
  const queryString = window.location.search;
  const urlParams = new URLSearchParams(queryString);
  numVideosInput.value = urlParams.get('num_videos');
}

updateNumVids();
