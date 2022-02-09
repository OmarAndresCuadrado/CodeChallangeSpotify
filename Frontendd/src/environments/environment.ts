// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,
  auth: {
    endpoint_login_page : 'http://localhost:8080/api/auth',
    endpoint_get_spotify_token : 'http://localhost:8080/api/get-token',
  },

  spotify: {
    endpoint_get_all_tracks : 'http://localhost:8080/api/getTracks',
    endpoint_get_track_by_isrc : 'http://localhost:8080/api/getTrack?isrc=',
    endpoint_create_track: 'http://localhost:8080/api/createTrack?isrc='
  }
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
