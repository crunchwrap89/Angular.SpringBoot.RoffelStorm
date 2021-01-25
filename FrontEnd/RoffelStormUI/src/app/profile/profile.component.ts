import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { HttpClient, HttpEvent } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { ProfilePost } from '../heroes/profilepost'
import { UserService } from '../heroes/user.service';
import { Observable } from 'rxjs';
import { User } from '../heroes/user';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  theUser: any;
  status: any;
  errorMessage: any;
  userPost = new ProfilePost();
  postcontent: string;
  posts: ProfilePost;
  selectedPics: FileList;

  constructor(
    private token: TokenStorageService,
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    private http: HttpClient
  ) {}
            
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.getUser(this.currentUser.id);
    this.getUserPosts();
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

createPost(post){
  this.userPost.authorId = this.currentUser.id;
  this.userPost.text = post;
  this.userPost.recieverId = this.currentUser.id
  this.userPost.authorName = this.currentUser.username
  this.userPost.recieverName = this.currentUser.username
  this.http.post('http://localhost:8082/api/createuserprofilepost', this.userPost)
  .subscribe(
    (val) => {
      console.log("Post erghjdsrgjhk",
      val);
    },
    response => {
      console.log("POST call in error", response);
    },
    () => {
      console.log("The post observable is now conpleted");
      this.postcontent = ''; 
      this.getUserPosts();
    }
  )
};

getUserPosts(): void {
  const id = this.currentUser.id;
  this.userService.getUserPosts(id).subscribe(posts => this.posts = posts);

}

onSelectPic(event): void {
  this.selectedPics = event.target.files;
  this.userService.putProfilePic(this.selectedPics[0], this.currentUser.id);
}

getUser(id): void {
  this.userService.getUser(id)
      .subscribe(user => this.theUser = user[0]);
}

}
