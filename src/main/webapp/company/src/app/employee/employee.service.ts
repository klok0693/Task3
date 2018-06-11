import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { HttpClient, HttpParams } from  '@angular/common/http';
import { map } from 'rxjs/operators';
//import { EMPLOYEES } from './mock-employees';
import {Employee} from "./employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  private _url = "http://localhost:8090/employee";

  constructor(private httpClient: HttpClient) { }

  /*getEmployees(): Observable<IEmployee[]> {
    return this.httpClient.get(this._url).pipe(map(empl => <IEmployee[]> empl));
  }*/

  /*getEmployees(): Observable<IEmployee[]> {
    return of(EMPLOYEES);
  }*/

  getEmployees(): Observable<Object> {
    return this.httpClient.get(this._url);
  }

  createEmployee(employee: Employee): Observable<Object> {
    return this.httpClient.post(this._url, employee);
  }

  updateEmployee(id: number, employee: Employee): Observable<Object> {
    //const urlParam = new HttpParams().set("id", id.toString());
    return this.httpClient.put(this._url, employee, {params: this.getUrlParam(id)});
  }

  deleteEmployee(id: number): Observable<Object> {
    return this.httpClient.delete("http://localhost:8090/employee/id", {params: this.getUrlParam(id)});
  }

  private getUrlParam(id: number): HttpParams {
    return new HttpParams().set("id", id.toString())
  }

  deleteUser(): Observable<string> {
    return this.httpClient.delete('http://localhost:8090/users', {responseType: 'text'});
  }
}
