import { Routes } from '@angular/router';

// Import all components you wish to be route candidates
import { HomeComponent } from './components/home/home.component';
import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';

export const appRoutes: Routes = [
    {
        path: 'interpolation', // The URL that will trigger the html injection
        component: InterpolationComponent
    },
    {
        path: 'home',
        component: HomeComponent
    },
    {
        path: 'directives',
        component: DirectivesComponent
    },
    {
        path: 'pokeapi',
        component: PokeapiComponent
    },
    {
        path: '', // For landing/home page preference
        component: HomeComponent
    },
    {
        path: '**', // For page not found preference
        redirectTo: '/home',
        pathMatch: 'full'
    }
];
