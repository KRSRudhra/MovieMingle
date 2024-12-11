import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ConfigurationService {
  private readonly apiUrl = 'http://localhost:8080/config/set-config'; // Update to match your backend endpoint

  constructor(private http: HttpClient) {}

  /**
   * Sends configuration data to the backend.
   * @param config The configuration object to send.
   * @returns Observable of the HTTP response.
   */
  sendConfiguration(config: any): Observable<any> {
    return this.http.post(this.apiUrl, config);
  }
}
