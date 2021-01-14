import { Component, OnInit} from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';
import { UserPost } from '../../models/userpost'
import { HttpClient } from '@angular/common/http';

import { User } from '../user';
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
  userPost = new UserPost();

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
    private location: Location,
    private http: HttpClient
  ) {}

  ngOnInit(): void {
    this.getUser();
  }

  getUser(): void {
    const id = +this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id)
      .subscribe(user => this.user = user);
  }

  goBack(): void {
    this.location.back();
  }

  // createPost(id, post){
  //   this.userPost.userId = id;
  //   this.userPost.text = post;
  //   console.log(this.userPost)
  //   this.http.post('http://localhost:8082/api/create/', this.userPost)
  //   .subscribe(
  //     (val) => {
  //       console.log("Post erghjdsrgjhk",
  //       val);
  //     },
  //     response => {
  //       console.log("POST call in error", response);
  //     },
  //     () => {
  //       console.log("The post observable is now conpleted");
  //     }
  //   )
  // };

}
