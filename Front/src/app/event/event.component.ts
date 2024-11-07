// event.component.ts
import { Component, OnInit } from '@angular/core';
import { Event } from '../models/event';
import { EventService } from '../service/event.service';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.css']
})
export class EventComponent implements OnInit {
  events: Event[] = [];  // List of events
  newEvent: Event = new Event();  // New event to add
  selectedEvent: Event | null = null;  // Event selected for editing

  constructor(private eventService: EventService) { }

  ngOnInit(): void {
    this.getEvents();  // Fetch events when the component initializes
  }

  // Fetch events
  getEvents(): void {
    this.eventService.getEvents().subscribe(events => {
      this.events = events;
    }, error => {
      console.error('Error fetching events:', error);
    });
  }

  // Add a new event
  addEvent(): void {
    this.eventService.addEvent(this.newEvent).subscribe({
      next: (response) => {
        console.log('Event added:', response);
        this.getEvents();  // Refresh the event list
        this.newEvent = new Event();  // Reset the form
      },
      error: (error) => {
        console.error('Error adding event:', error);
      }
    });
  }

  // Delete an event
  deleteEvent(eventUri: string): void {
    this.eventService.deleteEvent(eventUri).subscribe(response => {
      console.log('Event deleted:', response);
      this.getEvents();  // Refresh the event list
    }, error => {
      console.error('Error deleting event:', error);
    });
  }

  // Select an event for editing
  editEvent(event: Event): void {
    this.selectedEvent = { ...event };  // Clone the event for editing
  }

  // Update an event
  updateEvent(): void {
    if (this.selectedEvent) {
      this.eventService.updateEvent(this.selectedEvent).subscribe(response => {
        console.log('Event updated:', response);
        this.getEvents();  // Refresh the event list
        this.selectedEvent = null;  // Reset the selected event
      }, error => {
        console.error('Error updating event:', error);
      });
    }
  }

  // Cancel editing and reset the form
  cancelEdit(): void {
    this.selectedEvent = null;  // Reset the selected event
  }
}
