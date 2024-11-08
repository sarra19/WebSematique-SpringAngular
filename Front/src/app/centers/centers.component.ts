import { Component, OnInit } from '@angular/core';
import { Center } from '../models/center';
import { CenterService } from '../service/center.serive';


@Component({
  selector: 'app-centers',
  templateUrl: './centers.component.html',
  styleUrls: ['./centers.component.css']
})
export class CentersComponent implements OnInit {
  centers: Center[] = [];  // List of centers
  newCenter: Center = new Center();  // New center to add
  selectedCenter: Center | null = null;  // Center selected for editing

  constructor(private centerService: CenterService) { }

  ngOnInit(): void {
    this.getCenters();  // Fetch centers when the component initializes
  }

  // Fetch centers
  getCenters(): void {
    this.centerService.getCenters().subscribe({
      next: (centers) => this.centers = centers,
      error: (error) => console.error('Error fetching centers:', error)
    });
  }

  // Add a new center
  addCenter(): void {
    this.centerService.addCenter(this.newCenter).subscribe({
      next: (response) => {
        console.log('Center added:', response);
        this.getCenters();  // Refresh the center list
        this.newCenter = new Center();  // Reset the form
      },
      error: (error) => {
        console.error('Error adding center:', error);
      }
    });
  }



  // Delete a center
 // centers.component.ts
 deleteCenter(centerName: string): void {
  if (centerName) {
    this.centerService.deleteCenter(centerName).subscribe({
      next: (response) => {
        console.log('Center deleted:', response);
        this.getCenters();  // Refresh the list of centers
      },
      error: (error) => {
        console.error('Error deleting center:', error);
      }
    });
  } else {
    console.error('Center name is invalid');
  }
}



  // Select a center for editing
  editCenter(center: Center): void {
    this.selectedCenter = { ...center };  // Clone the center for editing
  }

  // Update a center
  updateCenter(): void {
    if (this.selectedCenter) {
      this.centerService.updateCenter(this.selectedCenter).subscribe({
        next: (response) => {
          console.log('Center updated:', response);
          this.getCenters();  // Refresh the center list
          this.selectedCenter = null;  // Reset the selected center
        },
        error: (error) => console.error('Error updating center:', error)
      });
    }
  }

  // Cancel editing and reset the form
  cancelEdit(): void {
    this.selectedCenter = null;  // Reset the selected center
  }
}