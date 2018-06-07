import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams } from  '@angular/common/http';
import {User} from "./user";
import {map} from 'rxjs/operators';
import {HttpResponse} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) {}

  attemptAuth(username: string, password: string): Observable<string> {
    const credentials = {username: username, password: password};
    return this.http.post('http://localhost:8090/login', credentials, {responseType:'text'});
  }
}

