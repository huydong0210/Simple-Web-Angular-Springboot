import {Component} from '@angular/core';
import {SignIn} from "../../../models/SignIn";
import {AuthService} from "../../../services/auth.service";
import {UserService} from "../../../services/user.service";
import {TokenService} from "../../../services/token.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  signIn: SignIn = new SignIn();
  isSuccessful: boolean = true;
  errorMessage: string ="";

  constructor(
    private authService: AuthService,
    private userService: UserService,
    private tokenService: TokenService,
    private router: Router
  ) {
  }
  ngOnInit():void{
    if (this.authService.isLogin){
      this.router.navigate(['home'])
    }
  }

  onSubmit(): void {
    this.authService.login(this.signIn).subscribe(data => {
        this.tokenService.saveToken(data.data.token);
        this.authService.isLogin = true;
        this.router.navigate(['/home']);
      },
      error => {
        this.isSuccessful = false;
         this.errorMessage=error.error.message? error.error.message : "Password was wrong";
      })
  }

  test(): void {
    this.userService.getAllUser().subscribe(data => {
      console.log(data)
    })
  }


}
