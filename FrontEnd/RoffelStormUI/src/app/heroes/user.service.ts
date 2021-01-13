import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { Observable, of } from 'rxjs';

import { User } from './user';
import { USERS } from './mock-users';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  //private usersUrl = 'api/user';  // URL to web api
  private AUTH_API_URL = 'http://localhost:8080/api';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, 
              private messageService: MessageService) { }

  getUsers(): Observable<User[]> {
    // TODO: send the message _after_ fetching the heroes
    this.messageService.add('UserService: fetched heroes');
    return of(USERS);
  }

  // getUser(id: number): Observable<User> {
  //   const url = `${this.usersUrl}/${id}`;
  //   return this.http.get<User>(url);
  // }

  getUser(id: number): Observable<any> {
    return this.http.get(this.AUTH_API_URL + `/auth/${id}`, { responseType: 'text' });
  }

}