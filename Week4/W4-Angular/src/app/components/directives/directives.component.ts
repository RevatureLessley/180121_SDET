import { Component } from '@angular/core';

@Component({
    selector: 'directives-component',
    templateUrl: './directives.component.html'
})

export class DirectivesComponent {
    public buttonValue = 'SHOW ANSWER';
    public visible = false;

    emp = {
        id: 1,
        name: 'Bobbert'
    };
 
    emps = [{
        id: 1,
        name: 'Bobbert'
    },
           {
        id: 2,
        name: 'Robert'
    },
           {
        id: 3,
        name: 'Dogbert'
    },
           {
        id: 4,
        name: 'Herbert'
    },
           {
        id: 5,
        name: 'Lobert'
    }];

    public toggleAnswer() {
        if ((this.visible = !this.visible)) {
            this.buttonValue = 'HIDE ANSWER';
        } else {
            this.buttonValue = 'SHOW ANSWER';
        }
    }
}
