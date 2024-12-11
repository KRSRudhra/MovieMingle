import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { StatusService } from '../services/status.service';
import {Router} from "@angular/router";

@Component({
  selector: 'app-add-purchase',
  templateUrl: './add-purchase.html',
  styleUrls: ['./add-purchase.scss'],
})
export class AddPurchaseComponent implements OnInit, OnDestroy {
  isRunning = true;
  ticketStatus = {
    availableTickets: 0,
    releasedTickets: 0,
    totalTickets: 0,
    maxTicketCapacity: 0,
  };
  addTicketCount: number = 0;
  purchaseTicketCount: number = 0;
  backendResponse: string = '';
  private statusSubscription!: Subscription;

  constructor(private statusService: StatusService, private router: Router) {}

  ngOnInit(): void {
    this.startSystem();
  }

  addTickets(): void {
    if (!this.isRunning) {
      this.backendResponse = 'System is stopped. Cannot add tickets.';
      return;
    }

    if (this.addTicketCount <= 0) {
      this.backendResponse = 'Please enter a valid ticket count.';
      return;
    }

    const payload = {
      type: 'add',
      count: this.addTicketCount,
      vendorId: 1,
      customerId: 0,
    };

    this.statusService.addTickets(payload).subscribe(
      (response) => {
        this.backendResponse = `Success: ${response}`;
      },
      (error) => {
        console.error('Error adding tickets:', error);
        this.backendResponse = 'Failed to add tickets.';
      }
    );
  }

  purchaseTickets(): void {
    if (!this.isRunning) {
      this.backendResponse = 'System is stopped. Cannot purchase tickets.';
      return;
    }

    if (this.purchaseTicketCount <= 0) {
      this.backendResponse = 'Please enter a valid ticket count.';
      return;
    }

    const payload = {
      type: 'purchase',
      count: this.purchaseTicketCount,
      vendorId: 0,
      customerId: 1,
    };

    this.statusService.purchaseTickets(payload).subscribe(
      (response) => {
        this.backendResponse = `Success: ${response}`;
      },
      (error) => {
        console.error('Error purchasing tickets:', error);
        this.backendResponse = 'Failed to purchase tickets.';
      }
    );
  }

  startSystem(): void {
    this.isRunning = true;
    this.backendResponse = 'System has started. Running...';
    this.statusSubscription = this.statusService.pollTicketPoolStatus().subscribe(
      (status) => {
        this.ticketStatus = status;
      },
      (error) => {
        console.error('Error fetching ticket pool status:', error);
        this.backendResponse = 'Failed to fetch status. Please try again.';
      }
    );
  }

  stopSystem(): void {
    this.isRunning = false;
    this.backendResponse = 'System has stopped. No transactions will be made.';
    if (this.statusSubscription) {
      this.statusSubscription.unsubscribe();
    }
  }

  // Method to navigate to the Dashboard component
  goToDashboard() {
    this.router.navigate(['/dashboard']); // Replace with your desired route
  }

  ngOnDestroy(): void {
    if (this.statusSubscription) {
      this.statusSubscription.unsubscribe();
    }
  }
}
