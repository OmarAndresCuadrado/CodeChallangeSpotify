import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  public environment = environment;
  public endpoint_login_auth = environment.auth.endpoint_login_page;
  public endpoint_get_auth_token = environment.auth.endpoint_get_spotify_token;

  constructor( private http: HttpClient) {

   }


   getLoginPage(): Observable<string> {
     let internalEndpoint = this.endpoint_login_auth;
    return this.http.get(internalEndpoint,  {responseType : 'text'});
   }

   getSpotifyAuthenticationToken(responseCode : string): Observable<any> {
    let internalEndpoint = this.endpoint_get_auth_token;
   return this.http.get<any>(`${internalEndpoint}?code=${responseCode}`);
  }
}
