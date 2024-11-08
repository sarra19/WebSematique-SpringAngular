// delivery-agency.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { DeliveryAgence } from '../models/deliveryAgence'; // Assurez-vous que le chemin est correct

@Injectable({
  providedIn: 'root'
})
export class DeliveryAgencyService {
  private apiUrl = 'http://localhost:8085';  // URL de l'API Spring Boot

  constructor(private http: HttpClient) { }

  // Méthode pour récupérer toutes les agences de livraison
  getDeliveryAgences(): Observable<DeliveryAgence[]> {
    return this.http.get<DeliveryAgence[]>(`${this.apiUrl}/getAllDeliveryAgencies`);
  }

  // Méthode pour ajouter une nouvelle agence de livraison
  addDeliveryAgence(deliveryAgence: DeliveryAgence): Observable<any> {
    return this.http.post(`${this.apiUrl}/addDeliveryAgence`, deliveryAgence);
  }

  // Méthode pour supprimer une agence de livraison
  deleteDeliveryAgence(agenceUri: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/deleteDeliveryAgence/${agenceUri}`);
  }

  // Méthode pour mettre à jour une agence de livraison
  updateDeliveryAgence(deliveryAgence: DeliveryAgence): Observable<any> {
    return this.http.put(`${this.apiUrl}/updateDeliveryAgence`, deliveryAgence);
  }
}