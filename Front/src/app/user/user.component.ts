import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { UserService } from '../service/user.service';  // User service
import { User } from '../models/user';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  users: User[] = [];  // List of users
  newUser: User = new User();  // New user to add
  selectedUser: User | null = null;  // User selected for editing

  constructor(
    private userService: UserService,
    private cdr: ChangeDetectorRef // Inject ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.getUsers();  // Fetch users when the component initializes
  }

  // Fetch users
  getUsers(): void {
    this.userService.getUsers().subscribe({
      next: (users) => {
        this.users = users;
        console.log('Users fetched:', this.users);  // Debugging statement
        this.cdr.detectChanges(); // Manually trigger change detection
      },
      error: (error) => {
        console.error('Error fetching users:', error);
      }
    });
  }

  // Add a new user
  addUser(): void {
    this.userService.addUser(this.newUser).subscribe({
      next: (response) => {
        console.log('User added:', response);
        this.getUsers();  // Refresh the user list
        this.newUser = new User();  // Reset the form
      },
      error: (error) => {
        console.error('Error adding user:', error);
      }
    });
  }

  // Delete a user using user ID
  deleteUser(userId: string): void {
    this.userService.deleteUser(userId).subscribe({
      next: (response) => {
        console.log('User deleted:', response);
        this.getUsers();  // Refresh the user list
      },
      error: (error) => {
        console.error('Error deleting user:', error);
      }
    });
  }

  // Update user details
  updateUser(): void {
    if (this.selectedUser) {
      this.userService.updateUser(this.selectedUser).subscribe({
        next: (response) => {
          console.log('User updated:', response);
          this.getUsers();  // Refresh the user list
          this.selectedUser = null;  // Clear the selected user
        },
        error: (error) => {
          console.error('Error updating user:', error);
        }
      });
    }
  }

  // Select a user for editing
  editUser(user: User): void {
    this.selectedUser = { ...user };  // Clone the user for editing
  }

  // Cancel editing and reset the form
  cancelEdit(): void {
    this.selectedUser = null;  // Reset the selected user
  }
}
