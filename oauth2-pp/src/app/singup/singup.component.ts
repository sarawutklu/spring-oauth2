import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';

@Component({
  selector: 'app-singup',
  templateUrl: './singup.component.html',
  styleUrls: ['./singup.component.scss']
})
export class SingupComponent {

  constructor(
    private httpClient: HttpClient,
  ) { }


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

  public username = ''
  public password = ''
  public roles = 0
  public message =''
  singup() {
    const headers = new HttpHeaders({
      'content-type': 'application/json',
    });
    const options = {
      headers: headers
    }

    var user = {
      username: this.username,
      password: this.password,
      roles:[+this.roles]
    }
    console.log("user ::", user);
    
    this.doPost('http://localhost:8080/singup', user, options).subscribe({
      next: (data:any) => {
         this.message = data.message
      },
      error: error => {
          console.error('There was an error!', error);
      }
  })
  }

  doPost(url: string, body: any, options: { headers: HttpHeaders }) {
    return this.httpClient.post(url, body, options);
  }

}
