import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { TokenStorageService } from '../../_services/token-storage.service';
import { HttpClient } from '@angular/common/http';

import { User } from '../user';
import { ProfilePost } from '../profilepost';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html',
  styleUrls: ['./user-detail.component.css']
})
export class UserDetailComponent implements OnInit {

  user: User;
  status: any;
  errorMessage: any;
  userPost = new ProfilePost();
  postcontent: string;
  currentUser: any;
  posts: ProfilePost;

  constructor(
    private token: TokenStorageService,
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.getUserPosts()
    this.getUser();
    this.currentUser = this.token.getUser();
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id).subscribe(user => this.user = user[0]);
  }
  getUserPosts(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUserPosts(id).subscribe(posts => this.posts = posts);

  }

  goBack(): void {
    this.location.back();
  }

  createPost(id, post){
    this.userPost.authorId = this.currentUser.id;
    this.userPost.text = post
    this.userPost.recieverId = id;
    this.userPost.authorName = this.currentUser.username
    this.userPost.recieverName = this.user.username
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

}
