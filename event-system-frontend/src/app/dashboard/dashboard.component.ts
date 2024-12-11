import { Component, OnInit } from '@angular/core';
import { TicketService } from '../services/dashboard.service';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {
  dashboardData = {
    totalTickets: 0,
    maxTicketCapacity: 0,
    releasedTickets: 0,
    availableTickets: 0,
    ticketReleaseRate: 0,
    customerRetrievalRate: 0,
  };

  chart: any;

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.ticketService.startPolling(); // Start polling for real-time updates

    // Subscribe to combined data
    this.ticketService.getDashboardData().subscribe((data) => {
      if (data) {
        this.dashboardData = data;
      }
    });
  }
}
