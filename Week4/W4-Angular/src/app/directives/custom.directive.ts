import { Directive, ElementRef, AfterViewInit } from '@angular/core';

@Directive({
    selector: '[custom-dir]'
})

export class CustomDirective implements AfterViewInit {

    constructor(private el: ElementRef) {
    }
    ngAfterViewInit() {
        this.el.nativeElement.style.background = 'red';
    }
}
