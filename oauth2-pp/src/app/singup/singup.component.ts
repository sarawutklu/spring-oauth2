import { Component } from '@angular/core';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent {

  constructor() { }


  ngOnInit(): void {
    
  }
  redirect(){
    window.location.href = this.redirectUrl();
  }

  redirectUrl() {
    const codeChallengeMethod = 'YsvSDp9kDYZqai8HQnfOsko7xVrubzk7ZY7FjA9dRHs'
    const redirectUri = `http://127.0.0.1:4200/authorized&code_challenge=${codeChallengeMethod}&code_challenge_method=S256`;
    return `http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=read&redirect_uri=${redirectUri}`;
  }

}
