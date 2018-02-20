import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'pokeapi',
    templateUrl: './pokeapi.component.html'
})

export class PokeapiComponent {
    public pokeId;
    public pkmn = {
        name: '',
        id: '',
        weight: '',
        sprite: '',
        sprites: [],
        spriteIndex: 0
    };

    constructor(private http: HttpClient) {

    }

    public fetchData() {
        console.log('Enter function');
        this.pkmn.name = 'Pending';
        this.pkmn.id = 'Pending';
        this.pkmn.weight = 'Pending';
        this.pkmn.sprite = 'https://media.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif';

        this.pkmn.sprites = [];
        this.pkmn.spriteIndex = 0;

        this.http.get('https://pokeapi.co/api/v2/pokemon/' + this.pokeId + '/').subscribe(
            data => { // data represents the object of a successful REST call to the url.
                this.pkmn.name = data['name'];
                this.pkmn.id = data['id'];
                this.pkmn.weight = data['weight'];

                let dataSprites = data['sprites'];
                for(let index in dataSprites) {
                    if (dataSprites[index] != null) {
                        this.pkmn.sprites.push(dataSprites[index]);
                        if (index=='front_default') {
                            this.pkmn.sprite = dataSprites[index];
                            this.pkmn.spriteIndex = this.pkmn.sprites.length - 1;
                        }
                    }
                }
            },
            error => {
                this.pkmn.name = 'MissingNo';
                this.pkmn.id = '-1';
                this.pkmn.weight = 'What?';
                this.pkmn.sprite = 'N/A';
            }
        )
    }

    public changePicture() {
        let sprites = this.pkmn.sprites;
        let index = this.pkmn.spriteIndex;
        if(sprites[index+1] == undefined) {
            this.pkmn.sprite = sprites[0];
            this.pkmn.spriteIndex = 0;
        } else {
            this.pkmn.sprite = sprites[++index];
            this.pkmn.spriteIndex = index;
        }
    }
}
