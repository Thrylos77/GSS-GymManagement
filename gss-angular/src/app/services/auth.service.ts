import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export class LoginRequest {
  username?: string;
  password?: string;
}

export class LoginResponse {
  id?: number;
  role?: string;

}

const apiUrl = 'http://localhost:8284/gss/auth/login';  //format_underlined

@Injectable({
  providedIn: 'root'
})
export class AuthService {


  constructor(private http: HttpClient) { }

  login(credentials: LoginRequest):Observable<LoginResponse> {
    return this.http.post<LoginResponse>(apiUrl, credentials);
  }
}
