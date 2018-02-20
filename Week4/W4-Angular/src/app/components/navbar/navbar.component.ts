import { Component } from '@angular/core';

@Component({
    selector: 'navbar',
    templateUrl: './navbar.component.html'
})

export class NavbarComponent {
    homeActive = '';
    dirActive = '';
    interActive = '';

    activeHome() {
        this.homeActive = 'active';
        this.dirActive = '';
        this.interActive = '';
    }

    activeDir() {
        this.homeActive = '';
        this.dirActive = 'active';
        this.interActive = '';
    }

    activeInter() {
        this.homeActive = '';
        this.dirActive = '';
        this.interActive = 'active';
    }
}
