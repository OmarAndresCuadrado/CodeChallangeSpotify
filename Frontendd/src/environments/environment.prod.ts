export const environment = {
  production: true,
  auth: {
    endpoint_login_page: 'http://localhost:8080/api/auth',
    endpoint_get_spotify_token: 'http://localhost:8080/api/get-token',
  },

  spotify: {
    endpoint_get_all_tracks : 'http://localhost:8080/api/getTracks',
    endpoint_get_track_by_isrc : 'http://localhost:8080/api/getTrack?isrc=',
    endpoint_create_track: 'http://localhost:8080/api/createTrack?isrc='
  }
};
