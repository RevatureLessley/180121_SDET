import { Component } from '@angular/core';

/*
   Angular components represent a section of your application. Typically multiple
   components are brought together in a module to made a full application.
   The component helps bind a view with a controller. 
*/

//Decorator
/*
    Any Annotated words are called "decorators" in Angular.
    They serve to provide metadata for the angular application.
    For instance this tells angular that its contents are to be used for
    the entire component bundle.
*/
@Component({
    //Selector
    /*
        The selector is needed in order to inject the views. The selector indicates what
        the tag name should be for the injection site of this component.
    */
    selector: 'app-interpolation',
    //Template URL
    /*
        The template URL is used to point at a specific file to be used as the front end
        for the specific component.
    */
    templateUrl: './interpolation.component.html',

    //StyleUrls
    /*
        Not a required key to have, but the StylesURL key will allow you to bring
        in 0 - many different css files to be applied to the contents of THIS component
        specifically.
    */
    styleUrls: ['interpolation.component.css']
})
export class InterpolationComponent{
    public componentVariable = "BOBBERT";
    public componentObject = {
        author: 'Ryan Lessley',
        age: 78
    };
    public typescriptStyle = "color:blue";
    styleObject = {
        color: 'blue',
        fontFamily: 'Comic Sans MS'
    }
}