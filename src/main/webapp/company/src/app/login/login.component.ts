import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {TemplateRef, ViewChild} from '@angular/core';

import {LoginService} from "./login.service";
import {TokenStorage} from "./token-storage";
import {User} from "./user";
import {Employee} from "../employee/employee";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  @ViewChild('editTemplate') editTemplate: TemplateRef<any>;

  constructor(private router: Router, private authService: LoginService, private token: TokenStorage) { }

  editedUser: User;

  login() : void {
    this.authService.attemptAuth(this.editedUser).subscribe(
      data => {
        this.token.saveToken(data.toString());
        //this.router.navigate(['user']);
      }
    );
  }

  loadTemplate(user: User): TemplateRef<any> {
    if (this.editedUser && this.editedUser.username == user.username) {
      return this.editTemplate;
    }
  }

  ngOnInit() {
  }

}
