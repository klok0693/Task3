import { Injectable } from '@angular/core';
import { HttpClient} from  '@angular/common/http';
import {HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }


  deleteUser(): Observable<string> {
    return this.http.delete('http://localhost:8090/users', {responseType:'text'});
  }
}
