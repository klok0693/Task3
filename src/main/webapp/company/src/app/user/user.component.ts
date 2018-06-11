import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from "./user.service";
import {TokenStorage} from "../login/token-storage";

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  constructor(
    private router: Router,
    private service: UserService,
    private storage: TokenStorage) { }

  ngOnInit() {
  }

  logout(): void {
    this.storage.signOut();
    this.router.navigateByUrl("/");
  }

  deleteUser(): void {
    this.service.deleteUser().subscribe(data => {
      if (data === 'true') {
        alert('Account deleted');
        this.router.navigateByUrl('/');
      }
      else {
        alert('Cant delete account');
      }
    },
      (error => alert(JSON.stringify(error)))
    );
  }
}
