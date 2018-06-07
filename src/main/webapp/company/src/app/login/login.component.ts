import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {TemplateRef, ViewChild} from '@angular/core';

import {LoginService} from "./login.service";
import {TokenStorage} from "./token-storage";
import {User} from "./user";
import {Employee} from "../employee/employee";
import {FormGroup} from '@angular/forms';
import {FormBuilder} from '@angular/forms';
import {Validators} from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private authService: LoginService,
    private token: TokenStorage) {

    this.editedUser = new User('','');
  }


  editedUser: User;

  setUsername(username: string) {
     this.editedUser.username = username;
  }

  setPassword(password: string) {
    this.editedUser.password = password;
  }

  login() : void {
    const val = this.editedUser;
    console.log("val = ", val);

    if (val.username && val.password) {
      this.authService.attemptAuth(val.username, val.password)
        .subscribe((data: any) => {

          console.log('token = ', data);

          this.token.saveToken(data);
          this.router.navigateByUrl('/employee');
        },
          (error => alert("Wrong password"))
        );
    }
  }

  ngOnInit() {
  }

}
