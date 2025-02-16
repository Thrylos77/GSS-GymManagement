import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-pack',
  imports: [FormsModule, CommonModule],
  templateUrl: './pack.component.html',
  styleUrls: ['./pack.component.css']
})
export class PackComponent {
  showForm = false; // Pour afficher/masquer le formulaire
  packs: any[] = []; // Liste des packs
  newPack: any = {}; // Nouveau pack à ajouter

  // Fonction pour afficher/masquer le formulaire
  toggleForm() {
    this.showForm = !this.showForm;
  }

  // Fonction pour ajouter un pack
  addPack() {
    if (this.newPack.offerName && this.newPack.duration && this.newPack.monthlyPrice) {
      this.packs.push({ ...this.newPack });
      this.newPack = {}; // Réinitialiser le formulaire
      this.showForm = false; // Masquer le formulaire après l'ajout
    } else {
      alert('Veuillez remplir tous les champs du formulaire.');
    }
  }
}
