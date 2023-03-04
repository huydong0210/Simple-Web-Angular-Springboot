import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {SignUp} from "../models/SignUp";
import {SignIn} from "../models/SignIn";

const AUTH_API = 'http://localhost:8083/auth/';
const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLogin: boolean=false;

  constructor(private http: HttpClient) {
    if (window.localStorage.getItem(TOKEN_KEY)) {
      this.isLogin = true;
    }
    else {
      this.isLogin=false;
    }
  }

  register(signUpRequest : SignUp): Observable<any> {
    return this.http.post(AUTH_API + 'sign-up', signUpRequest);
  }
  login(signInRequest : SignIn) : Observable<any>{
    return this.http.post(AUTH_API+ 'sign-in',signInRequest);
  }
  logOut() :void{
    window.localStorage.clear();
    this.isLogin=false;
  }

  test (): Observable<any>{
    return this.http.get('http://localhost:8083/users')
}
}
