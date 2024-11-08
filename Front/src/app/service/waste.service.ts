import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Waste } from '../models/waste';  // Import the Waste model

@Injectable({
  providedIn: 'root'
})
export class WasteService {
  private apiUrl = 'http://localhost:8085';  // URL de l'API (Assuming you have a Spring Boot backend for waste as well)

  constructor(private http: HttpClient) { }

  // Fetch all waste items
  getWastes(): Observable<Waste[]> {
    return this.http.get<Waste[]>(`${this.apiUrl}/getAllWaste`);  // Change to your backend API endpoint
  }

  // Add a new waste item
  addWaste(waste: Waste): Observable<any> {
    return this.http.post(`${this.apiUrl}/addWaste`, waste);  // Adjust based on your backend API endpoint
  }

  // Delete a waste item by ID
  deleteWaste(wasteId: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/deleteWaste`, { body: { wasteId: wasteId } });  // Adjust for backend endpoint
  }

  // Update an existing waste item
  updateWaste(waste: Waste): Observable<any> {
    return this.http.put(`${this.apiUrl}/updateWaste`, waste);  // Adjust for backend endpoint
  }
}

