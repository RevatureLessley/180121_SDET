import { Component } from '@angular/core'; 
import { HttpClient } from '@angular/common/http';

@Component({
    selector: 'pokeapi',
    templateUrl: './pokeapi.component.html'
})

export class PokeapiComponent{
    public pokeId;
    public pkmn = {
        name: "",
        id: "",
        weight: "",
        sprite: "",
        sprites: [],
        spriteIndex: 0
    }

    constructor(private http: HttpClient){

    }

    /*
        Promise Vs Observable

        Side note: Observable is a kind of a replacement for Promise itroduced in ES7

        A Promise is a type of object where when a user sends data,
        we are gauranteed to get something back. In the case of sending a request
        we either get back an object representing the data we received, or we get an object
        representing an error with details on why we didnt get what we expected.
        This flexibility, along side callback functions, allows us to create asynchronous
        function calls.
        Promises can only listen on one event at a time, on top of which, a user
        cannot cancel the event once it is stared. IT, I cant halt a request sent to
        a server, I must wait for some kind of response.

        Obserbables are the same as promises, except with more features. Observables
        send the data back, essentially, as a stream. With that, Observables can provide
        support for 0-many events at a time. Observable also supports the canceling of 
        a current event's stream of data.
    */

    public fetchData(){
        console.log("EnterFunction")
        this.pkmn.name = "Pending";
        this.pkmn.id = "Pending";
        this.pkmn.weight = "Pending";
        this.pkmn.sprite = "https://media.giphy.com/media/3oEjI6SIIHBdRxXI40/giphy.gif";

        this.pkmn.sprites = [];
        this.pkmn.spriteIndex = 0;

        this.http.get("https://pokeapi.co/api/v2/pokemon/" + this.pokeId + "/").subscribe(
            data => { //data represents the object of a successful REST call to the url.
                this.pkmn.name = data["name"];
                this.pkmn.id = data["id"];
                this.pkmn.weight = data["weight"];

                let dataSprites = data["sprites"];
                for(let index in dataSprites){
                    if(dataSprites[index]!=null){
                        this.pkmn.sprites.push(dataSprites[index]);
                        if(index=="front_default"){
                            this.pkmn.sprite = dataSprites[index];
                            this.pkmn.spriteIndex = this.pkmn.sprites.length-1;
                        }
                    }
                }
            },
            error => {
                this.pkmn.name = "MissingNo";
                this.pkmn.id = "-1";
                this.pkmn.weight = "What?";
                this.pkmn.sprite = "N/A";

            }
        )
    }

    public changePicture(){
        let sprites = this.pkmn.sprites;
        let index = this.pkmn.spriteIndex;
        if(sprites[index+1]==undefined){
            this.pkmn.sprite = sprites[0];
            this.pkmn.spriteIndex = 0;
        }else{
            this.pkmn.sprite = sprites[++index];
            this.pkmn.spriteIndex= index;
        }
    }
}
