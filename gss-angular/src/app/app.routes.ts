import { Routes } from '@angular/router';
import { CustomerComponent } from './pages/customer/customer.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { LayoutComponent } from './pages/layout/layout.component';
import { LoginComponent } from './pages/login/login.component';
import { PackComponent } from './pages/pack/pack.component';
import { UserComponent } from './pages/user/user.component';

export const routes: Routes = [
  {
    path:'',
    redirectTo:'/dashboard',
    pathMatch:'full'
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: '',
    component: LayoutComponent,
    children: [
      { path: 'dashboard', component: DashboardComponent },
      { path: 'customer', component: CustomerComponent },
      { path: 'pack', component: PackComponent },
      { path: 'user', component: UserComponent },

    ]
  }
];
