import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-animation-switch',
  templateUrl: './animation-switch.component.html',
  styleUrls: ['./animation-switch.component.css']
})
export class AnimationSwitchComponent implements OnInit {

  checkers: boolean;

  constructor() { }

  ngOnInit(): void {

    if (localStorage.getItem('anim') === 'anim-off') {
        this.setTheme('anim-off');
        this.checkers = false;
    } else {
        this.setTheme('anim-on');
        this.checkers = true;
    }
}

setTheme(tName) {
  localStorage.setItem('anim', tName);
  document.documentElement.id = tName;
}

toggleTheme() {
if (localStorage.getItem('anim') === 'anim-off'){
    this.setTheme('anim-on');
} else {
    this.setTheme('anim-off');
}
}

}
