// import { CanActivateFn } from '@angular/router';
//
// export const authGuard: CanActivateFn = (route, state) => {
//   return true;
// };



import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {authUtils} from "../authUtils";

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {
  constructor(
    private router: Router,
    private authenticationService: AuthService
  ) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const currentUser = authUtils.currentUserValue();
    if (currentUser) {
      console.log("done with success")
      return true;
    }
    console.log("its not done  ;(")
    this.router.navigate(['/login'], { queryParams: { returnUrl: state.url } });
    return false;
  }
}
