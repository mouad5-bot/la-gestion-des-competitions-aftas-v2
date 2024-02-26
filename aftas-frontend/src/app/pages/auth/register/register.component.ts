import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {AuthService} from "../../../services/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  form: FormGroup;
  submitted = false;
  error = '';
  successmsg = false;

  constructor(private fb: FormBuilder,
              private route: ActivatedRoute,
              private router: Router,
              private authenticationService: AuthService,
  ){
    this.form = this.fb.group({
      name: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required],
    });
  }

  register() {
    const val = this.form.value;
    if (val.password != val.confirmPassword){
      alert ("The password should be same !")
      return;
    }
    this.submitted = true;
    if (this.form.invalid) {
      return;
    } else {
      this.authenticationService.register(val.name, val.email, val.password).subscribe({
        next: () => {
          this.successmsg = true;
          if (this.successmsg) {
            this.router.navigate(['/login']);
          }
        },
        error: (error) => {
          this.error = error ? error : '';

        },
        complete: () => {
        }
      })
    }
  }
}
