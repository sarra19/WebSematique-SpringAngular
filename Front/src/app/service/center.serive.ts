
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Center } from '../models/center';

@Injectable({
  providedIn: 'root'
})
export class CenterService {
  private apiUrl = 'http://localhost:8085';  // URL of the Spring Boot API

  constructor(private http: HttpClient) { }

  // Method to retrieve all centers
  getCenters(): Observable<Center[]> {
    return this.http.get<Center[]>(`${this.apiUrl}/getAllCenters`);
  }

  // Method to add a new center
  addCenter(center: Center): Observable<any> {
    const headers = new HttpHeaders().set('Content-Type', 'application/json');
    
    return this.http.post(`${this.apiUrl}/addCenter`, center, { headers: headers });
  }

  // Method to delete a center
  deleteCenter(centerName: string): Observable<any> {
    return this.http.delete(`${this.apiUrl}/deleteCenter/${centerName}`);
  }

  // Method to update an existing center
  updateCenter(center: Center): Observable<any> {
    return this.http.put(`${this.apiUrl}/updateCenter`, center);
  }
}