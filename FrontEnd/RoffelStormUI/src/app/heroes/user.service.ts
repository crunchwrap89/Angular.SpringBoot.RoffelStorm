import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest, HttpEvent } from '@angular/common/http';

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { User } from './user';
import { MessageService } from '../message.service';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  message: any;
  private AUTH_API_URL = 'http://localhost:8080/api/auth';
  private USERPOST_API_URL = 'http://localhost:8082/api/userprofilepostbyrecieverid';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(private http: HttpClient, 
              private messageService: MessageService) { }

  getUsers(): Observable<User[]> {
    return this.http.get<User[]>(this.AUTH_API_URL + '/all')
      .pipe(
        tap(_ => this.log('fetched users')),
        catchError(this.handleError<User[]>('getUsers', []))
);
}
getUser(id): Observable<User> {
  const url = `${this.AUTH_API_URL}/user/${id}`;
  return this.http.get<User>(url).pipe(
    tap(_ => this.log(`fetched user id=${id}`)),
    catchError(this.handleError<User>(`getUser id=${id}`))
  );
}
getUserPosts(id): Observable<any> {
  const url = `${this.USERPOST_API_URL}/${id}`;
  return this.http.get<any>(url).pipe(
    tap(_ => this.log(`fetched userposts for id=${id}`)),
    catchError(this.handleError<User>(`getUserPosts id=${id}`))
  );
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

  // putProfilePic(file: File, userId): Observable<HttpEvent<any>> {
  //   const formData: FormData = new FormData();
  //   formData.append('file', file);
  //   formData.append('userId', userId);
  //   const req = new HttpRequest('PUT', `${this.AUTH_API_URL}/uploadrofilepic`, formData, {
  //     reportProgress: true,
  //     responseType: 'json'
  //   });
  //   return this.http.request(req);
  // }

  putProfilePic(file: File, userId) {
    const formData: FormData = new FormData();
    formData.append('file', file);
    formData.append('userId', userId);
    this.http.put<any>(`${this.AUTH_API_URL}/uploadrofilepic`, formData)
      .subscribe(response => this.message = response.message);
      console.log(this.message);
  }

}