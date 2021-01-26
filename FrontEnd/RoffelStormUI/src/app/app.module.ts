import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { BoardAdminComponent } from './board-admin/board-admin.component';
import { BoardModeratorComponent } from './board-moderator/board-moderator.component';
import { BoardUserComponent } from './board-user/board-user.component';

import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { UploadFilesComponent } from './components/upload-files/upload-files.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { HeroesModule } from './heroes/heroes.module';
import { OrderModule } from 'ngx-order-pipe';
import { ThemeSwitchComponent } from './theme-switch/theme-switch.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AnimationSwitchComponent } from './animation-switch/animation-switch.component';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    HeroesModule,
    AppRoutingModule,
    OrderModule,
    NgbModule,
  ],
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    BoardUserComponent,
    UploadFilesComponent,
    PageNotFoundComponent,
    ThemeSwitchComponent,
    AnimationSwitchComponent
  ],
   providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
