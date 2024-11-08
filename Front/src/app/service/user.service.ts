import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private apiUrl = 'http://localhost:8085';  // URL of your Spring Boot API

  constructor(private http: HttpClient) { }

  // Method to get all users
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.apiUrl}/getAllUsers`);
  }

  // Method to add a new user
  addUser(user: User): Observable<any> {
    return this.http.post(`${this.apiUrl}/addUser`, user);
  }

  // Method to update an existing user
  updateUser(updatedUser: User): Observable<any> {
    return this.http.put(`${this.apiUrl}/updateUser`, updatedUser);
  }

  // Method to delete a user by URI (or ID)
  deleteUser(userUri: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/deleteUser/${encodeURIComponent(userUri)}`);
  }

  // Method to get users based on some specific filter (e.g., role, status, etc.)
  getUsersByRole(role: string): Observable<User[]> {
    const url = `${this.apiUrl}/getUsersByRole/${role}`;  // Assuming a filter endpoint
    return this.http.get<User[]>(url);
  }
}
