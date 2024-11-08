import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Publication } from '../models/publication';  // Importation du modèle Publication

@Injectable({
  providedIn: 'root'
})
export class PublicationService {
  private apiUrl = 'http://localhost:8085';  // URL de l'API Spring Boot

  constructor(private http: HttpClient) { }

  // Méthode pour récupérer toutes les publications
  getPublications(): Observable<Publication[]> {
    return this.http.get<Publication[]>(`${this.apiUrl}/getAllPublications`);  // Route pour récupérer les publications
  }

  // Méthode pour ajouter une nouvelle publication
  addPublication(publication: Publication): Observable<any> {
    return this.http.post(`${this.apiUrl}/addPublication`, publication);  // Route pour ajouter une publication
  }

  // Méthode pour supprimer une publication
  deletePublication(publicationUri: string): Observable<any> {
    // Passe l'URI directement dans l'URL de la requête
    return this.http.delete(`${this.apiUrl}/deletePublication/${encodeURIComponent(publicationUri)}`);
  }
  

  // Méthode pour mettre à jour une publication
  updatePublication(publication: Publication): Observable<any> {
    return this.http.put(`${this.apiUrl}/updatePublication`, publication);  // Route pour mettre à jour une publication
  }
}


