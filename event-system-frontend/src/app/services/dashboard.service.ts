import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, BehaviorSubject } from 'rxjs';
import { switchMap } from 'rxjs/operators';

interface Config {
  totalTickets: number;
  maxTicketCapacity: number;
  ticketReleaseRate: number;
  customerRetrievalRate: number;
}

interface Status {
  availableTickets: number;
  releasedTickets: number;
  maxTicketCapacity: number;
}

@Injectable({
  providedIn: 'root',
})
export class TicketService {
  private readonly configUrl = 'http://localhost:8080/config/get-config';
  private readonly statusUrl = 'http://localhost:8080/tickets/status';

  private dashboardDataSubject = new BehaviorSubject<{
    totalTickets: number;
    maxTicketCapacity: number;
    releasedTickets: number;
    availableTickets: number;
    ticketReleaseRate: number;
    customerRetrievalRate: number;
  } | null>(null);

  constructor(private http: HttpClient) {}

  // Fetch configuration
  getConfig(): Observable<Config> {
    return this.http.get<Config>(this.configUrl);
  }

  // Fetch ticket status
  getStatus(): Observable<Status> {
    return this.http.get<Status>(this.statusUrl);
  }

  // Polling to fetch real-time updates and combine both results
  startPolling(interval: number = 1000) {
    setInterval(() => {
      this.getConfig().subscribe((configData) => {
        this.getStatus().subscribe((statusData) => {
          // Combine the results into a single object
          const combinedData = {
            totalTickets: configData.totalTickets,
            maxTicketCapacity: statusData.maxTicketCapacity,
            releasedTickets: statusData.releasedTickets,
            availableTickets: statusData.availableTickets,
            ticketReleaseRate: configData.ticketReleaseRate,
            customerRetrievalRate: configData.customerRetrievalRate,
          };
          this.dashboardDataSubject.next(combinedData);
        });
      });
    }, interval);
  }

  // Get latest combined data
  getDashboardData(): Observable<{
    totalTickets: number;
    maxTicketCapacity: number;
    releasedTickets: number;
    availableTickets: number;
    ticketReleaseRate: number;
    customerRetrievalRate: number;
  } | null> {
    return this.dashboardDataSubject.asObservable();
  }
}
