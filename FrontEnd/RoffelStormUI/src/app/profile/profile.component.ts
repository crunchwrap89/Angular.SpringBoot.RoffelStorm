import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token-storage.service';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { Observable } from 'rxjs';
import { ProfilePost } from '../heroes/profilepost'
import { UserService } from '../heroes/user.service';
import { UploadFilesService } from 'src/app/services/upload-files.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  currentUser: any;
  status: any;
  errorMessage: any;
  userPost = new ProfilePost();
  postcontent: string;
  posts: ProfilePost;
  selectedFile: File;
  profilePics: Observable<any>;
  profilePic: any;
  editMode = false;

  constructor(
    private token: TokenStorageService,
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    private http: HttpClient,
    private uploadService: UploadFilesService
  ) {}
            
  ngOnInit(): void {
    this.currentUser = this.token.getUser();
    this.getUserPosts();
    this.profilePics = this.uploadService.getProfilePicByUserId(this.currentUser.id);
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

isEditMode() {
  this.editMode = true;
}

saveEdit() {
  this.editMode = false;
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

onSelectFile(event): void {
  if (event.target.files && event.target.files[0]) {
    this.uploadService.uploadProfilePic(event.target.files[0], this.currentUser.id);
  }

}


}
