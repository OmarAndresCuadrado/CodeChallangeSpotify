import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { SpotifyService } from 'src/app/services/spotify.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public spotifyLoginPage: string = '';
  public codeResponseFromLoginPage: string = '';
  public onCreateTrack: boolean = false;
  public onGetTrackById: boolean = false;
  public onGetAllTracks: boolean = false;
  public isrc: string = '';
  public listOfTracks: any[] = [];
  public trackFound: any = null;

  constructor(private authService: AuthService, private activatedRoute: ActivatedRoute, private spotifyService: SpotifyService) { }

  ngOnInit(): void {
    this.activatedRoute.queryParams.subscribe(params => {
      let code = params['code'];
      if (code) {
        this.codeResponseFromLoginPage = code;
        console.log("code response ", code);
        this.createAuthenticationToken();
      }
    })
    this.getAllTracks();
  }

  redirectToLoginPage() {
    this.getSpotifyLoginPage()
    setTimeout(() => {
      window.location.replace(this.spotifyLoginPage);
    }, 2000);

  }

  getSpotifyLoginPage() {
    this.authService.getLoginPage().subscribe(resp => {
      console.log("respuesta login page ", resp);
      this.spotifyLoginPage = resp;
    }, error => {
      console.log("error getting login page ", error);
    });
  }

  createAuthenticationToken() {
    this.authService.getSpotifyAuthenticationToken(this.codeResponseFromLoginPage).subscribe(resp => {
      resp;
    }, error => {
      console.log("error creating token ", error);
    })
  }

  getAllTracks() {
    this.listOfTracks = [];
    this.spotifyService.getAllTracks().subscribe(resp => {
      this.listOfTracks = resp;
    }, async errr => {
      console.log("error creating object ", errr);
      await Swal.fire(
        'Something went wrong',
        `${errr.error}`,
        'error'
      )
    });
  }

  getTrackById() {
    this.trackFound = {};
    console.log("valor del isrc ", this.isrc);
    this.spotifyService.getTrackById(this.isrc).subscribe(resp => {
      console.log("object found ", resp);
      this.trackFound = resp;
    }, async errr => {
      console.log("error creating object ", errr);
      await Swal.fire(
        'Something went wrong',
        `${errr.error}`,
        'error'
      )
    });
  }

  createTrack() {
    console.log("valor del isrc ", this.isrc);
    this.spotifyService.createTrack(this.isrc).subscribe(async resp => {
      console.log("object created ", resp);
      await Swal.fire(
        'New register track created',
        `The track with the isrc ${resp.trackiscr} was added to the database`,
        'success'
      )
    }, async errr => {
      console.log("error creating object ", errr);
      await Swal.fire(
        'Something went wrong',
        `${errr.error}`,
        'error'
      )
    });
  }

  openCreateTrack() {
    this.onCreateTrack = true;
    this.onGetTrackById = false;
    this.onGetAllTracks = false;
  }

  openGetTrackById() {
    this.onCreateTrack = false;
    this.onGetTrackById = true;
    this.onGetAllTracks = false;
  }

  openGetAllTracks() {
    this.onCreateTrack = false;
    this.onGetTrackById = false;
    this.onGetAllTracks = true;
    this.getAllTracks();
  }

}
