import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { Router } from "@angular/router";
import { ConfigurationService } from "../services/configuration.service";

@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.scss']
})
export class ConfigurationComponent {
  ticketForm: FormGroup;
  message: string = ''; // Message for the user
  messageType: 'success' | 'error' = 'success'; // To determine message style
  backendResponse: any = null; // To store and display the backend response

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private configurationService: ConfigurationService
  ) {
    this.ticketForm = this.fb.group({
      totalTickets: ['', [Validators.required, Validators.min(1)]],
      ticketReleaseRate: ['', [Validators.required, Validators.min(1)]],
      customerRetrievalRate: ['', [Validators.required, Validators.min(1)]],
      maxTicketCapacity: ['', [Validators.required, Validators.min(1)]],
    });
  }

  onSubmit() {
    if (this.ticketForm.valid) {
      this.configurationService.sendConfiguration(this.ticketForm.value).subscribe(
        (response: any) => {
          this.backendResponse = response;
          this.message = 'Configuration saved successfully!';
          this.messageType = 'success';
          console.log('Success:', response);
          this.router.navigate(['/add-purchase']); // Redirect or stay based on your requirement
        },
        (error: any) => {
          this.message = error.error?.message || 'Failed to save configuration!';
          this.messageType = 'error';
          console.error('Error:', error);
        }
      );
    } else {
      this.message = 'Form is invalid. Please correct the errors and try again.';
      this.messageType = 'error';
    }
  }

  get f() {
    return this.ticketForm.controls;
  }
}
