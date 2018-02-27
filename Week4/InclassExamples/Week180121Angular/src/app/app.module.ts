//Modules
/*
  A module is a collection of components, services, directives, etc, that are bundled
  together as a separate piece of application that can be plugged or removed from 
  main application with no problems.
*/
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';

//Components
import { AppComponent } from './app.component';
import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { appRoutes } from './routing';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';

//Custom Directives
import { CustomDirective } from './directives/custom.directive';


@NgModule({
  declarations: [
    AppComponent,
    InterpolationComponent,
    DirectivesComponent,
    HomeComponent,
    NavbarComponent,
    PokeapiComponent,

    //Directives
    CustomDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(appRoutes),
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent] //used to identify the root component(s) for a modules
})
export class AppModule { }
