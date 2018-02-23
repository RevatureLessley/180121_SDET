/*
    Routing is a feature than Angular uses to achieve true
    Single Page Application format. (SPA)

    Routing is where we set up a tag that will take injections of separate html
    code/pages dynamically. This lets the user stay on the same page, while new content
    is replaced throughout navigation. 
    The pages and content get cached client side so that the user does not need to hit
    the server for new pages or old ones.

    Worth noting: The caching utilizes browser bookmarks to maintain page history.
    This allows us to use the back/forward buttons on the page even though, technically,
    the page is never actually changed.
*/

import { Routes } from '@angular/router';

//Import all components you wish to be route candidates
import { HomeComponent } from './components/home/home.component';
import { InterpolationComponent } from './components/interpolation/interpolation.component';
import { DirectivesComponent } from './components/directives/directives.component';
import { PokeapiComponent } from './components/pokeapi/pokeapi.component';

export const appRoutes: Routes = [
    {
        path: 'interpolation', //The URL that will trigger the html injection
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
        path: '', //For landing/home page preference
        component: HomeComponent
    },
    {
        path: '**', //For page not found preference
        redirectTo: '/home',
        pathMatch: 'full'
    }
]