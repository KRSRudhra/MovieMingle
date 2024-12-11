import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable, timer} from 'rxjs';
import { switchMap } from 'rxjs/operators';


@Injectable({
  providedIn: 'root',
})
export class StatusService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) {}

  // Fetch status from API
  getTicketPoolStatus(): Observable<any> {
    return this.http.get(`${this.baseUrl}/tickets/status`);
  }

  // Poll the status every 10 seconds
  pollTicketPoolStatus(): Observable<any> {
    return timer(0, 10000).pipe(switchMap(() => this.getTicketPoolStatus()));
  }

  addTickets(payload: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/tickets/add-tickets`, payload, { responseType: 'text' });
  }

  purchaseTickets(payload: any): Observable<string> {
    return this.http.post(`${this.baseUrl}/tickets/purchase-tickets`, payload, { responseType: 'text' });
  }

  stopSystem(): Observable<void> {
    return this.http.post<void>(`${this.baseUrl}/system/stop`, {});
  }
}
