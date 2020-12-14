import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  status: any;
  errorMessage: any;

  constructor(private token: TokenStorageService,
              private http: HttpClient) { }
            
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
  }

  deleteAccount(id){
  this.http.delete('http://localhost:8080/api/auth/deleteUser/' + id)
  .subscribe({
      next: data => {
          this.status = 'Delete successful';
          this.token.signOut();
          window.location.reload();
      },
      error: error => {
          this.errorMessage = error.message;
          console.error('There was an error!', error);
      }
  });
}
}
