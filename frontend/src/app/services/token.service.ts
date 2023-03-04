import {Injectable} from '@angular/core';
import {User} from "../models/User";
import {JwtHelperService} from "@auth0/angular-jwt";
import {UserService} from "./user.service";
import {Role} from "../models/Role";

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';
const ROLE_KEY = 'auth-role';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  constructor(private userService: UserService) {
  }

  getToken(): any {
    return window.localStorage.getItem(TOKEN_KEY);
  }

  saveToken(token: string): void {
    window.localStorage.clear();
    window.localStorage.setItem(TOKEN_KEY, token);
    const helper = new JwtHelperService();
    const decodedToken = helper.decodeToken(token);
    this.saveUser(decodedToken.sub);
    this.userService.getUser(decodedToken.sub).subscribe(data =>{
      this.saveRole(data.data.roles);
    })
  }

  logout(): void {
    window.localStorage.clear();
  }

  saveUser(username: any): void {
    window.localStorage.setItem(USER_KEY, username);
  }

  getUsername(): any {
    return window.localStorage.getItem(USER_KEY);
  }

  logOut(): void {
    window.localStorage.clear();
  }

  saveRole(role: any) {
    window.localStorage.setItem(ROLE_KEY,role);
  }
  getRole () : any {
    return window.localStorage.getItem(ROLE_KEY);
  }
}
