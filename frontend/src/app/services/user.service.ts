import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../models/User";
import {TokenService} from "./token.service";
import {AuthService} from "./auth.service";

const API = 'http://localhost:8083/users'

@Injectable({
  providedIn: 'root'
})


export class UserService {


  constructor(private http: HttpClient,
  ) {

  }

  getAllUser(): Observable<any> {
    return this.http.get(API);
  }

  deleteUser(id: any): Observable<any> {
    return this.http.delete(API + "/" + id);
  }

  getUser(username: any): Observable<any> {
    return this.http.get(API + "/" + username);
  }

  updateUser(id: number, fullName: string, email: string, roles: string[]): Observable<any> {
    var user: User = new User();
    user.fullName = fullName;
    user.email = email;
    user.roles = roles;
    return this.http.post(API + "/" + id, user);
  }

}
