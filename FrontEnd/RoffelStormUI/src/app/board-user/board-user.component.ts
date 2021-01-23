import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserPost } from '../models/userpost'
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  allPosts: any[]
  userPost = new UserPost();
  postcontent: string;
  currentUser: any;
  order: string = 'upvotes';
  reverse: boolean = true;

  constructor(private token: TokenStorageService,
              private http: HttpClient
              ) {}

  ngOnInit() {
    this.http.get<any>('http://localhost:8082/api/all').subscribe(data => {
        this.allPosts = data;
    })
    this.currentUser = this.token.getUser();        
  }

  createPost(currentuser, post){
    this.userPost.userId = currentuser.id;
    this.userPost.username = currentuser.username
    this.userPost.text = post;
    this.postcontent = '';
    console.log(this.userPost)
    this.http.post('http://localhost:8082/api/create/', this.userPost)
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
        this.ngOnInit()
      }
    )
  };

  upVote(postid, upvotes) {
    let newUpvotes = upvotes + 1;
    this.userPost.upvotes = newUpvotes;
    console.log(this.userPost)
    console.log(postid)
    this.http.put(`http://localhost:8082/api/updateuserpostupvotes/${postid}`, this.userPost)
    .subscribe(
      (val) => {
        this.userPost = new UserPost();
        this.ngOnInit()
        console.log("Post done",
        val);
      },
      response => {
        console.log("POST call in error", response);
      },
      () => {
        console.log("The post observable is now complete");
      }
    )
  }
  downVote(postid, upvotes) {
    let newUpvotes = upvotes - 1;
    this.userPost.upvotes = newUpvotes;
    console.log(this.userPost)
    this.http.put(`http://localhost:8082/api/updateuserpostupvotes/${postid}`, this.userPost)
    .subscribe(
      (val) => {
        this.userPost = new UserPost();
        this.ngOnInit()
        console.log("Post erghjdsrgjhk",
        val);
      },
      response => {
        console.log("POST call in error", response);
      },
      () => {
        console.log("The post observable is now conpleted");
      }
    )
  }

}
