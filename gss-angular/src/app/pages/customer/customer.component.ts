import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-customer',
  imports: [FormsModule, CommonModule],
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent {
  showForm = false; // Pour afficher/masquer le formulaire
  customers: any[] = []; // Liste des clients
  newCustomer: any = {}; // Nouveau client à ajouter

  // Fonction pour afficher/masquer le formulaire
  toggleForm() {
    this.showForm = !this.showForm;
  }

  // Fonction pour ajouter un client
  addCustomer() {
    if (this.newCustomer.firstName && this.newCustomer.lastName && this.newCustomer.email) {
      this.customers.push({ ...this.newCustomer });
      this.newCustomer = {}; // Réinitialiser le formulaire
      this.showForm = false; // Masquer le formulaire après l'ajout
    } else {
      alert('Veuillez remplir tous les champs du formulaire.');
    }
  }
}
