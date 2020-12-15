import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.css']
})
export class BoardUserComponent implements OnInit {

  allPosts: any[]

  constructor(private http: HttpClient) { }

  ngOnInit() {
    this.http.get<any>('http://localhost:8082/api/all').subscribe(data => {
        this.allPosts = data;
    })        
  }

}
