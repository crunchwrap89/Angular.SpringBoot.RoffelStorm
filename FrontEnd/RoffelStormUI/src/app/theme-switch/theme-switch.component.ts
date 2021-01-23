import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-theme-switch',
  templateUrl: './theme-switch.component.html',
  styleUrls: ['./theme-switch.component.css']
})
export class ThemeSwitchComponent implements OnInit {

  themeName = 'theme-light';
  constructor() { }

  ngOnInit(): void {
    localStorage.setItem('theme', this.themeName);
    document.documentElement.className = this.themeName;
  }

  setTheme() {
    if (this.themeName === 'theme-dark') {
        this.themeName = "theme-light";
        localStorage.setItem('theme', this.themeName);
        document.documentElement.className = this.themeName;
    } else {
        this.themeName = "theme-dark";
        localStorage.setItem('theme', this.themeName);
        document.documentElement.className = this.themeName;
    }
}

}
