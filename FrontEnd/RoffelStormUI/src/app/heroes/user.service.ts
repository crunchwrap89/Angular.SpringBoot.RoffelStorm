import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';


import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from './user';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {

  //private usersUrl = 'api/user';  // URL to web api
  private AUTH_API_URL = 'http://localhost:8080/api/auth';
  private USERPOST_API_URL = 'http://localhost:8082/api/userpostbyid';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, 
              private messageService: MessageService) { }

  // getUsers(): Observable<User[]> {
  //               return this.http.get<User[]>(this.AUTH_API_URL + '/all')
  //                 .pipe(
  //                   tap(_ => this.log('fetched users')),
  //                   catchError(this.handleError<User[]>('getUsers', []))
  //     );
  // }
  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.AUTH_API_URL + '/all')
      .pipe(
        tap(_ => this.log('fetched users')),
        catchError(this.handleError<User[]>('getUsers', []))
);
}


  getUser(id: number): Observable<any> {
    return this.http.get(this.USERPOST_API_URL + `/${id}`, { responseType: 'text' });
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }

  /** Log a HeroService message with the MessageService */
  private log(message: string) {
    this.messageService.add(`UserService: ${message}`);
  }

}