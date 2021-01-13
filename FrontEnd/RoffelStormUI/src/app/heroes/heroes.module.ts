import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

import { FindRoffelsComponent } from './find-roffels/find-roffels.component';
import { UserDetailComponent } from './user-detail/user-detail.component';

import { HeroesRoutingModule } from './heroes-routing.module';


@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HeroesRoutingModule
  ],
  declarations: [
    FindRoffelsComponent,
    UserDetailComponent
  ]
})
export class HeroesModule {}
