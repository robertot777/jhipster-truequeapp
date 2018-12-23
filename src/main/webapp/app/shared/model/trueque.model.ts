import { Moment } from 'moment';

export const enum Estado {
    OFERTA = 'OFERTA',
    CONCRETADO = 'CONCRETADO'
}

export interface ITrueque {
    id?: number;
    fecha?: Moment;
    estado?: Estado;
    entregaId?: number;
    recibeId?: number;
}

export class Trueque implements ITrueque {
    constructor(public id?: number, public fecha?: Moment, public estado?: Estado, public entregaId?: number, public recibeId?: number) {}
}
