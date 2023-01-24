import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { Buffer } from 'buffer';

@Component({
  selector: 'app-authorize',
  templateUrl: './authorize.component.html',
  styleUrls: ['./authorize.component.scss']
})
export class AuthorizeComponent {
  public code: string = '';
  public tokens: any;

  constructor(
    private httpClient: HttpClient,
    private activateRoute: ActivatedRoute,
    private router:Router
    ) {
      this.activateRoute.queryParams.subscribe((params) => {
        if(params?.['code']){
          this.code = params['code'];
        }
      })  
  }

  ngOnInit(): void {
    this.getToken().subscribe({
      next: data => {
         this.tokens = data;
      },
      error: error => {
          console.error('There was an error!', error);
          this.router.navigateByUrl("/singup");
      }
  })
  }

  getToken() {
    const mockUserClient = 'client';
    const mockUserSecret = 'secret';
    const basicAuth = `Basic ` + Buffer.from(`${mockUserClient}:${mockUserSecret}`).toString('base64');
    const headers = new HttpHeaders({
      'content-type': 'application/json',
      'Authorization': basicAuth
    });
    const options = {
      headers: headers
    }

    return this.doPost(this.tokenUrl(this.code), null, options);
  }

  doPost(url: string, body: any, options: { headers: HttpHeaders }) {
    return this.httpClient.post(url, body, options);
  }

  tokenUrl = (code: string) => {
    const codeVerifier = 'TABN8eEGN6aQWGkKMvu6GDxTpoC-5z2rtKN3cFdRnH0'; 
    const redirectUrl = `http://127.0.0.1:4200/authorized&grant_type=authorization_code&code=${code}&code_verifier=${codeVerifier}`;
    return `http://localhost:8080/oauth2/token?client_id=client&redirect_uri=${redirectUrl}`;
  };
}
