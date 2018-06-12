import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {TemplateRef, ViewChild} from '@angular/core';

import {LoginService} from "./login.service";
import {TokenStorage} from "./token-storage";
import {Authoruty, User} from "./user";
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
  @ViewChild('loginTemplate') loginTemplate: TemplateRef<any>;
  @ViewChild('registerTemplate') registerTemplate: TemplateRef<any>;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private tokenStorage: TokenStorage) {

    this.editedUser = new User('','', []);
  }


  editedUser: User;


  setUsername(username: string) {
     this.editedUser.username = username;
  }

  setPassword(password: string) {
    this.editedUser.password = password;
  }

  setUserAuthority() {
    this.editedUser.authorities.push(new Authoruty(1));
  }

  login() : void {
    const val = this.editedUser;
    console.log("val = ", val);

    if (val.username && val.password) {
      this.loginService.getToken(val.username, val.password)
        .subscribe(data => {

          console.log('token = ', data);
          if (data) {

            this.tokenStorage.saveToken(data);

            this.loginService.getAuthority().subscribe((data: Authoruty[]) => {
                console.log("auth = ", data[0].name);

                let url;
                switch (data[0].name) {
                  case 'ROLE_USER':  url = '/user';      break;
                  case 'ROLE_ADMIN': url = '/employee';  break;
                }
                this.router.navigateByUrl(url);
              })
          }
          else console.log('User not exist');
        },
          (error => alert(JSON.stringify(error)))
        );
    }
  }

  register(): void {
    this.setUserAuthority();
    let val = this.editedUser;

    console.log("user = ", val);

    this.loginService.saveUser(val).subscribe(
      () => {
        this.loginService.getToken(val.username, val.password).subscribe(
          (token: string) => {
            this.tokenStorage.saveToken(token);
            this.router.navigateByUrl("/user");
          }
        )
      },
      (error => alert(JSON.stringify(error)))
    );
  }

  ngOnInit() {
  }

}
