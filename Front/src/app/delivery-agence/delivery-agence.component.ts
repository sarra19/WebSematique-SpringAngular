import { Component, OnInit } from '@angular/core';
import { DeliveryAgence } from '../models/deliveryAgence'; // Assurez-vous que le chemin est correct
import { DeliveryAgencyService } from '../service/delivery-agency.service'; // Assurez-vous que le chemin est correct

@Component({
  selector: 'app-delivery-agence',
  templateUrl: './delivery-agence.component.html',
  styleUrls: ['./delivery-agence.component.css']
})
export class DeliveryAgenceComponent implements OnInit {

  deliveryAgences: DeliveryAgence[] = [];  // Liste des agences de livraison
  selectedDeliveryAgence: DeliveryAgence | null = null;  // Agence sélectionnée pour modification
  isAddFormVisible: boolean = false;

  // Nouvelle agence à ajouter
  newDeliveryAgence: DeliveryAgence = {
      name: '',
      address: '',
      phoneNumber: '',
      image: '',
      openingHours: '',
      closingHours: ''
  };

  constructor(private deliveryAgencyService: DeliveryAgencyService) { }

  ngOnInit(): void {
    this.getDeliveryAgences();  // Récupérer les agences lors de l'initialisation du composant
  }

  // Méthode pour afficher le formulaire d'ajout
  showAddForm() {
    this.isAddFormVisible = true;
    this.selectedDeliveryAgence = null; // Réinitialise la sélection
    this.newDeliveryAgence = {
        name: '',
        address: '',
        phoneNumber: '',
        image: '',
        openingHours: '',
        closingHours: ''
    }; // Réinitialise le nouvel objet d'agence
  }

  // Récupérer les agences de livraison
  getDeliveryAgences(): void {
    this.deliveryAgencyService.getDeliveryAgences().subscribe({
      next: (agences) => {
        this.deliveryAgences = agences;
      },
      error: (error) => {
        console.error('Error fetching delivery agencies:', error);
      }
    });
  }

  // Ajouter une nouvelle agence de livraison
  addDeliveryAgence(): void {
    this.deliveryAgencyService.addDeliveryAgence(this.newDeliveryAgence).subscribe({
      next: (response) => {
        console.log('Delivery agency added:', response);
        this.getDeliveryAgences();  // Rafraîchir la liste des agences
        this.newDeliveryAgence = {  // Réinitialiser le formulaire
            name: '',
            address: '',
            phoneNumber: '',
            image: '',
            openingHours: '',
            closingHours: ''
        };
      },
      error: (error) => {
        console.error('Error adding delivery agency:', error);
      }
    });
  }

   // Méthode pour supprimer une agence
   deleteDeliveryAgence(name: string) {
    if (confirm(`Êtes-vous sûr de vouloir supprimer l'agence "${name}" ?`)) {
      this.deliveryAgences = this.deliveryAgences.filter(agence => agence.name !== name);
    }
  }

  // Sélectionner une agence pour modification
  editDeliveryAgence(agence: DeliveryAgence): void {
    this.selectedDeliveryAgence = { ...agence };  // Cloner l'agence pour modification
  }

  // Mettre à jour une agence de livraison
  updateDeliveryAgence(): void {
    if (this.selectedDeliveryAgence) {
      this.deliveryAgencyService.updateDeliveryAgence(this.selectedDeliveryAgence).subscribe({
        next: (response) => {
          console.log('Delivery agency updated:', response);
          this.getDeliveryAgences();  // Rafraîchir la liste des agences
          this.selectedDeliveryAgence = null;  // Réinitialiser l'agence sélectionnée
        },
        error: (error) => {
          console.error('Error updating delivery agency:', error);
        }
      });
    }
  }

  // Annuler l'édition d'une agence
  cancelEdit(): void {
    this.selectedDeliveryAgence = null;  // Réinitialiser l'agence sélectionnée
  }
}