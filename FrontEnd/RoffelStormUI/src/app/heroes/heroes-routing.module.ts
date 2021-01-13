import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { FindRoffelsComponent } from './find-roffels/find-roffels.component';
import { UserDetailComponent } from './user-detail/user-detail.component';

const heroesRoutes: Routes = [
  { path: 'users',  component: FindRoffelsComponent },
  { path: 'user/:id', component: UserDetailComponent }
];

@NgModule({
  imports: [
    RouterModule.forChild(heroesRoutes)
  ],
  exports: [
    RouterModule
  ]
})
export class HeroesRoutingModule { }