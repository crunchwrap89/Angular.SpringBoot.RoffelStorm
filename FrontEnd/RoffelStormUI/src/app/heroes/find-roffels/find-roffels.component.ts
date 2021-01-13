import { Component, OnInit } from '@angular/core';

import { User } from '../user';
import { UserService } from '../user.service';
import { MessageService } from '../../message.service';

@Component({
  selector: 'app-find-roffels',
  templateUrl: './find-roffels.component.html',
  styleUrls: ['./find-roffels.component.css']
})
export class FindRoffelsComponent implements OnInit {

  selectedUser: User;

  users: User[];

  constructor(private userService: UserService, private messageService: MessageService) { }

  ngOnInit() {
    this.getUsers();
  }

  onSelect(user: User): void {
    this.selectedUser = user;
    this.messageService.add(`FindRoffelsComponent: Selected user id=${user.id}`);
  }

  getUsers(): void {
    this.userService.getUsers()
        .subscribe(users => this.users = users);
  }

}
