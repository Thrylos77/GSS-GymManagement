import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-layout',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <nav class="navbar">
      <a routerLink="/dashboard">Dashboard</a>
      <a routerLink="/customer">Customers</a>
      <a routerLink="/pack">Packs</a>
      <a routerLink="/login">Logout</a>
    </nav>
    <div class="content">
      <router-outlet></router-outlet>
    </div>
  `,
  styles: [`
    .navbar {
      background-color: #764ba2;
      padding: 1rem;
      display: flex;
      gap: 1rem;
    }
    .navbar a {
      color: white;
      text-decoration: none;
    }
    .content {
      padding: 2rem;
    }
  `]
})
export class LayoutComponent {

}
