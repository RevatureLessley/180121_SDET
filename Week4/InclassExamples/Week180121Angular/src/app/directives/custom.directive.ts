import { Directive, ElementRef, AfterViewInit } from '@angular/core';

@Directive({
    //By encasing the selector with square brackets, we tell angular to use
    //it as an element property instead of an element itself.
    selector: '[custom-dir]'
})

export class CustomDirective implements AfterViewInit{
    /*
        Note: Access Modifiers in typescript. 
        Public is accessible outside of typescript file.
        Private is for use within tyepscript file only.
        No modifier defaults to public.

        Note: Constructors with private parameters.
    */
    constructor(private el: ElementRef){
        
    }
    ngAfterViewInit(){
        this.el.nativeElement.style.background="red";
    }
}