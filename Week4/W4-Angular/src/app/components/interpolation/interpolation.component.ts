import { Component } from '@angular/core';

@Component({
    selector: 'app-interpolation',
    templateUrl: './interpolation.component.html',
    styleUrls: ['interpolation.component.css']
})
export class InterpolationComponent {
    public componentVariable = 'Bobbert';
    public componentObject = {
        author: 'Lyn',
        age: 22
    };

    tsStyleObj = {
        color: 'blue',
        fontFamily: 'Comic Sans MS'
    };
    public visible = false;
    public input = 'Type something~';

    public changeStyle() {
        if (this.tsStyleObj.color === 'blue') {
            this.tsStyleObj.color = 'green';
        } else if (this.tsStyleObj.color === 'green') {
            this.tsStyleObj.color = 'yellow';
        } else if (this.tsStyleObj.color === 'yellow') {
            this.tsStyleObj.color = 'red';
        } else if (this.tsStyleObj.color === 'red') {
            this.tsStyleObj.color = 'blue';
        }
    }

    public toggleVisibility() {
        this.visible = !this.visible;
    }
}
