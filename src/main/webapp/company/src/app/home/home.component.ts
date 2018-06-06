import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  welcome: string;

  constructor() {
    this.welcome = "Welcome to home page"
  }

  ngOnInit() {
  }

}
