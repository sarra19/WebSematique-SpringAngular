import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { recycledProduct } from '../models/recycledProduct';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class RecycledProductService {
  private apiUrl = 'http://localhost:8085';  // URL de l'API Spring Boot

  constructor(private http: HttpClient) { }

  // Méthode pour récupérer tous les produits recyclés
  getProducts(): Observable<recycledProduct[]> {
    return this.http.get<recycledProduct[]>(`${this.apiUrl}/getAllRecycledProducts`);
  }

  // Ajouter un produit recyclé
  addProduct(product: recycledProduct): Observable<any> {
    return this.http.post(`${this.apiUrl}/addRecycledProduct`, product);
  }

// New method to update a product
updateProduct(updatedProduct: recycledProduct): Observable<any> {
  return this.http.put(`${this.apiUrl}/updateRecycledProduct`, {
    ...updatedProduct,
  });
}
  
  // Supprimer un produit recyclé
  deleteProduct(productUri: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteRecycledProduct/${encodeURIComponent(productUri)}`);
  }

  getProductsByPercentage(): Observable<any> {
    const url = `${this.apiUrl}/getProdByPerc`;  // API endpoint
    return this.http.get<any>(url);  // Make GET request and return observable
  }
}