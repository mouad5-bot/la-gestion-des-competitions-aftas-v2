import {UserClass} from "./models/user";
import {JwtAuthenticationResponse} from "./models/jwt-auth-response";

class AuthUtils {


  constructor() {
  }


  /**
   * Logout the user
   */
  logout() {
    localStorage.removeItem('authUser');
    localStorage.clear();
  }

  setLoggedCredentials(user: UserClass, jwtAuthenticationResponse: JwtAuthenticationResponse) {
    if (user)
      console.log("han user agnso n util" + user);
      localStorage.setItem('authUser', JSON.stringify(user));
    if (jwtAuthenticationResponse) {
      this.setAccessToken(jwtAuthenticationResponse.accessToken);
      this.setRefreshToken(jwtAuthenticationResponse.refreshToken);
    }
  }

  setRefreshToken(refreshToken: string) {
    localStorage.setItem('refresh_token', refreshToken);
  }

  setAccessToken(accessToken: string) {
    localStorage.setItem('access_token', accessToken);
  }

  /**
   * Returns the authenticated user
   */
  getAuthenticatedUser(): UserClass | null {
    const authUserString = localStorage.getItem('authUser');
    console.log(authUserString)
    if (!authUserString) {
      return null;
    }
    return JSON.parse(authUserString) as UserClass;
  }

  currentAccessToken() {
    return localStorage.getItem('access_token') ?? null;
  }

  currentUserValue() {
    return this.getAuthenticatedUser();
  }

  getRefreshToken() {
    return localStorage.getItem('refresh_token') ?? null;
  }

  hasRole(role : string){
    const roles = this.getAuthenticatedUser()?.authorities;
    return roles?.includes( role ) ?? false;
  }
}

export const authUtils = new AuthUtils();
