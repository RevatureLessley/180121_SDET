import { Employee } from '../objects/employee';
import { Injectable } from '@angular/core';

@Injectable()
export class EmpService {
    constructor() {

    }

    getEmps(): Employee[] {
        return [
            {
                id: 1,
                name: 'Bobbert',
                salary: 1212121,
                title: 'Professional Bob'
            },
            {
                id: 2,
                name: 'Robbet',
                salary: 50,
                title: 'Professional Robber'
            },
            {
                id: 3,
                name: 'Herbert',
                salary: 51,
                title: 'Professional Herb'
            }
        ];
    }
}
