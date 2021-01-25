import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-theme-switch',
  templateUrl: './theme-switch.component.html',
  styleUrls: ['./theme-switch.component.css']
})
export class ThemeSwitchComponent implements OnInit {

  checkers: boolean;

  constructor() { }

  ngOnInit(): void {

      if (localStorage.getItem('theme') === 'theme-dark') {
          this.setTheme('theme-dark');
          this.checkers = false;
      } else {
          this.setTheme('theme-light');
          this.checkers = true;
      }
  }

  setTheme(tName) {
    localStorage.setItem('theme', tName);
    document.documentElement.className = tName;
}

  toggleTheme() {
  if (localStorage.getItem('theme') === 'theme-dark'){
      this.setTheme('theme-light');
  } else {
      this.setTheme('theme-dark');
  }
}

}
