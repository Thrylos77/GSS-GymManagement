import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {
  totalActiveCustomers: number = 100; // Exemple de donnée
  estimatedMonthlyRevenue: number = 5000; // Exemple de donnée

  exportData() {
    // Ajoutez ici la logique pour exporter les données
    console.log("Export des abonnements...");
  }
}
