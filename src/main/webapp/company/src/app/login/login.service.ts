import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams } from  '@angular/common/http';
import {User} from "./user";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {}

  attemptAuth(user: User): Observable<Object> {
    const credentials = {username: user.username, password: user.password};
    return this.http.post('http://localhost:8090/login', credentials);
  }
}

