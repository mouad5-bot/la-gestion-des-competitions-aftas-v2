import {Component, OnInit} from '@angular/core';
import {authUtils} from "../../authUtils";
import {Router} from "@angular/router";
import {UserClass} from "../../models/user";
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{
  public user : UserClass | undefined;
  constructor(private router: Router , private authService: AuthService) {
  }
  ngOnInit(): void {
    //this.user = this.authService.me();
  }

  logout() {
    authUtils.logout();
    this.router.navigate(['/login']);
  };
}
