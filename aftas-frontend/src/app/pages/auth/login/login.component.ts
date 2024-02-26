import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{
  form: FormGroup;

  constructor(private fb: FormBuilder,
              private authService: AuthService,
              private router: Router) {
    this.form = this.fb.group({
      email: ['mouad5@gmail.com', [Validators.required, Validators.email]],
      password: ['mouad5@gmail.com', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      email: ['mouad@gmail.com', [Validators.required, Validators.email]],
      password: ['mouad123', [Validators.required]],
    });
  }

  login() {
    const val = this.form.value;

    if (val.email && val.password) {
      this.authService.login(val.email, val.password)
        .subscribe(
          () => {
            console.log("User is logged in");
          }
        );
    }
  }
}
