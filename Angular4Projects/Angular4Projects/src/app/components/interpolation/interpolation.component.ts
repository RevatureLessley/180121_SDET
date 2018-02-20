import {Component} from '@angular/core';

/*
    Angular components represent a section of your app. Typically multiple components are brought together in a module
    to made a full app.
    The component helps bind a view with a controller.
*/

@Component({
    
    selector: 'app-interpolation',
    templateUrl: './interpolation.component.html',
    styleUrls: ['interpolation.component.css']

})
export class InterpolationComponent{
    componentVariable = "Bobbert";
    componentObject = {
        author: 'Ryan Lessley',
        age: 78
    }; 
    public typescriptStyle = "color:blue";
    styleObject = {
        color:"blue",
        fontfamily: "Comic Sans MS"

    }
    public visible = false;

    public changeStyle(){
        if(this.styleObject.color=='blue'){
            this.styleObject.color='green'
        }
        if(this.styleObject.color=='green'){
            this.styleObject.color='yellow'
        }
        if(this.styleObject.color=='yellow'){
            this.styleObject.color='red'
        }
        if(this.styleObject.color=='red'){
            this.styleObject.color='blue'
        }
    }


    public toggleVisibility(){
        this.visible= !this.visible;
    }
}