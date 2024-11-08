import { Component, OnInit } from '@angular/core';
import { Waste } from '../models/waste';  // Import the Waste model
import { WasteService } from '../service/waste.service';  // Import the Waste service

@Component({
  selector: 'app-waste',
  templateUrl: './waste.component.html',
  styleUrls: ['./waste.component.css']
})
export class WasteComponent implements OnInit {
  wastes: Waste[] = [];  // List of waste items
  newWaste: Waste = new Waste();  // New waste to add
  selectedWaste: Waste | null = null;  // Waste selected for editing

  constructor(private wasteService: WasteService) { }

  ngOnInit(): void {
    this.getWastes();  // Fetch waste items when the component initializes
  }

  // Fetch waste items
  getWastes(): void {
    this.wasteService.getWastes().subscribe(wastes => {
      this.wastes = wastes;
    }, error => {
      console.error('Error fetching wastes:', error);
    });
  }

  // Add a new waste item
  addWaste(): void {
    this.wasteService.addWaste(this.newWaste).subscribe({
      next: (response) => {
        console.log('Waste added:', response);
        this.getWastes();  // Refresh the waste list
        this.newWaste = new Waste();  // Reset the form
      },
      error: (error) => {
        console.error('Error adding waste:', error);
      }
    });
  }

  // Delete a waste item
  deleteWaste(wasteUri: string): void {
    this.wasteService.deleteWaste(wasteUri).subscribe(response => {
      console.log('Waste deleted:', response);
      this.getWastes();  // Refresh the waste list
    }, error => {
      console.error('Error deleting waste:', error);
    });
  }

  // Select a waste item for editing
  editWaste(waste: Waste): void {
    this.selectedWaste = { ...waste };  // Clone the waste item for editing
  }

  // Update a waste item
  updateWaste(): void {
    if (this.selectedWaste) {
      this.wasteService.updateWaste(this.selectedWaste).subscribe(response => {
        console.log('Waste updated:', response);
        this.getWastes();  // Refresh the waste list
        this.selectedWaste = null;  // Reset the selected waste item
      }, error => {
        console.error('Error updating waste:', error);
      });
    }
  }

  // Cancel editing and reset the form
  cancelEdit(): void {
    this.selectedWaste = null;  // Reset the selected waste item
  }
}
