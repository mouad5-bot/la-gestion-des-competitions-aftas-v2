import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable, pipe} from "rxjs";
import {UserClass} from "../models/user";
import {environment} from "../models/environment";
import {authUtils} from "../authUtils";
import {JwtAuthenticationResponse} from "../models/jwt-auth-response";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  public isLoggedIn = false;
  redirectUrl: string | undefined;
  private apiUrl: string = environment.apiUrl + "/api/v2/auth";

  constructor(private http: HttpClient, private router : Router) { }

  register(name: string,email: string, password: string): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(this.apiUrl + "/register", {name ,email, password});
  }

  login(email: string, password: string): Observable<JwtAuthenticationResponse> {
    return this.http.post<JwtAuthenticationResponse>(this.apiUrl + "/login", {email, password})
      .pipe(
        map((response: JwtAuthenticationResponse) => {

            // login successful if there's a jwt token in the response
            if (response.accessToken && response.refreshToken) {

              // retrieve the user info
              this.me(response.accessToken).subscribe({
                next: (user: UserClass) => {
                  authUtils.setLoggedCredentials(user, response);
                  this.router.navigate(['']);
                }
              });
            }
            this.isLoggedIn = true;
            return response;
          }
        ));
  }

  me(access_token: string): Observable<UserClass> {
    return this.http.get<UserClass>(this.apiUrl + "/me", {headers: {Authorization: `Bearer ${access_token}`}})
  }

  logout() {
    this.isLoggedIn = false;
    authUtils.logout();
  }

  getRefreshToken(){
    return authUtils.getRefreshToken();
  }

  refreshToken(refresh_token: string | null): Observable<JwtAuthenticationResponse> {
    return this.http.get<JwtAuthenticationResponse>(this.apiUrl + "/token/refresh", {headers: {Authorization: `Bearer ${refresh_token}`}})
      .pipe(
        map((response: JwtAuthenticationResponse) => {
          if (response) {
            authUtils.setAccessToken(response.accessToken);
          }
          return response;
        })
      );
  }
}
