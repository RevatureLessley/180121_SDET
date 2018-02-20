
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HttpClientModule } from '@angular/common/http';


import { AppComponent } from './app.component';
import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';

import { appRoutes } from './routing';

// Custom Directives
import { CustomDirective } from './directives/custom.directive';

// Services
import { EmpService } from './services/emp.service';


@NgModule({
    declarations: [
        AppComponent,
        InterpolationComponent,
        DirectivesComponent,
        NavbarComponent,
        HomeComponent,
        PokeapiComponent,

        // Directives
        CustomDirective
    ],
    imports: [
        BrowserModule,
        FormsModule,
        RouterModule.forRoot(appRoutes),
        HttpClientModule
    ],
    providers: [EmpService], // Place created services here as a provider
    bootstrap: [AppComponent] // used to identify the root component(s) for a modules
})
export class AppModule { }
