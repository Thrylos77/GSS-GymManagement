import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService, LoginRequest } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  username: string = '';
  password: string = '';
  errorMessage: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  onSubmit(): void {

    const credentials: LoginRequest = {
      username: this.username,
      password: this.password
    };
    if (this.username == "admin", this.password == "admin") {
      this.router.navigate(['/dashboard']);
    } else {
      this.errorMessage = 'Nom d\'utilisateur ou mot de passe incorrect';
    }


    /*this.authService.login(credentials).subscribe({
      next: (response) => {
        console.log('Réponse API:', response);
        localStorage.setItem('userId', (response.id ?? '').toString());
        localStorage.setItem('userRole', response.role?? '');
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        console.error('Erreur de connexion:', err);
        this.errorMessage = 'Nom d\'utilisateur ou mot de passe incorrect';
      }
    });*/
  }
}
