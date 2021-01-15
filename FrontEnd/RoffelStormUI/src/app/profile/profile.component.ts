import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { UserPost } from '../models/userpost'

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  status: any;
  errorMessage: any;
  userPost = new UserPost();
  postcontent: string;

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

  createPost(currentuser, post){
    this.userPost.userId = currentuser.id;
    this.userPost.username = currentuser.username;
    this.userPost.text = post;
    this.http.post('http://localhost:8082/api/create/', this.userPost)
    .subscribe(
      (val) => {
        console.log("Post made",
        val);
      },
      response => {
        console.log("POST call made error", response);
      },
      () => {
        console.log("The post observable is now conpleted");
      }
    )
  };
}
