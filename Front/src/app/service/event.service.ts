import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Event } from '../models/event';

@Injectable({
  providedIn: 'root'
})
export class EventService {
  private apiUrl = 'http://localhost:8085';  // URL de l'API Spring Boot

  constructor(private http: HttpClient) { }

  // Méthode pour récupérer tous les événements
  getEvents(): Observable<Event[]> {
    return this.http.get<Event[]>(`${this.apiUrl}/getAllEvents`);
  }

  // Méthode pour ajouter un événement
  addEvent(event: Event): Observable<any> {
    return this.http.post(`${this.apiUrl}/addEvent`, event);
  }

  // Méthode pour supprimer un événement
 // event.service.ts
deleteEvent(eventUri: string): Observable<any> {
  return this.http.delete(`${this.apiUrl}/deleteEvent`, { body: { event: eventUri } });
}

  // Méthode pour mettre à jour un événement
  updateEvent(event: Event): Observable<any> {
    return this.http.put(`${this.apiUrl}/updateEvent`, event, { responseType: 'text' as 'json' });
  }
    // Méthode pour récupérer les événements filtrés par feedback
    getEventsByFeedback(): Observable<any> {
      return this.http.get(`${this.apiUrl}/getEventByFeedback`);
    }
}
