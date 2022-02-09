import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SpotifyService {


  public environment = environment;
  public endpoint_get_all_tracks = environment.spotify.endpoint_get_all_tracks;
  public endpoint_get_track_by_isrc = environment.spotify.endpoint_get_track_by_isrc;
  public endpoint_create_track = environment.spotify.endpoint_create_track;

  constructor(private http: HttpClient) { }

  getAllTracks(): Observable<any> {
    let internalEndpoint = this.endpoint_get_all_tracks;
    return this.http.get<any>(internalEndpoint);
  }

  getTrackById(isrc: string): Observable<any> {
    let internalEndpoint = this.endpoint_get_track_by_isrc;
    return this.http.get<any>(`${internalEndpoint}${isrc}`);
  }

  createTrack(isrc: string): Observable<any> {
    let internalEndpoint = this.endpoint_create_track;
    return this.http.post<any>(`${internalEndpoint}${isrc}`, null);
  }

}