import {Component, Input} from '@angular/core';
import {UserService} from "../../services/user.service";
import {User} from "../../models/User";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {TokenService} from "../../services/token.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {
  alertMessage: string = "";
  role: any = "user";
  users: any;
  usernameSearch: string = "";
  fullNameSearch: string = "";
  emailSearch: string = "";
  roleSearch: string = "";
  isLogin: boolean = this.authService.isLogin;
  updateUser: User = new User();
  userModal: any = new User();

  constructor(private userService: UserService,
              private authService: AuthService,
              private tokenService: TokenService,
              private router: Router) {
  }
  ngOnInit(): void {
    this.isLogin = this.authService.isLogin;
    console.log(this.authService.isLogin)
    if (this.isLogin === false) {
      this.router.navigate(['/login'])
    }
    this.userService.getAllUser().subscribe(data => {
      this.users = data.data;
    })
    console.log(this.users);
  }

  onUpdate(user: any) {
      this.userModal = user;
  }

  onModalSubmit() {
    if (this.tokenService.getRole()!=="admin"){
      alert("you don't have permission to do this action")
      return;
    }
    var roles: string[] = [this.role];
    this.userService.updateUser(this.userModal.id, this.updateUser.fullName, this.updateUser.email, roles)
      .subscribe(data => {
        alert("update successfully")
        this.userService.getAllUser().subscribe(data => {
          this.users = data.data;
        })
      }, error => {
        this.alertMessage = error.error.message ? error.error.message : error.error.error;
        alert(this.alertMessage);
      });
  }

  delete(user: any) {
    if (user.username===this.tokenService.getUsername()){
      alert("you can't do this action");
      return;
    }
    var id = user.id;
    if (confirm("Do you want to delete user " + user.username + "?")) {
      this.userService.deleteUser(id).subscribe(data => {
        var listUser: User[] = <User[]>this.users;
        this.users = listUser.filter(function (user) {
          return user.id != id;
        })
      }, error => {
        alert("You don't have permission to do this action!")
      })
    }
  }
}
