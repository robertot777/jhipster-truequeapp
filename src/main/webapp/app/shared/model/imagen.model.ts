import { Moment } from 'moment';

export interface IImagen {
    id?: number;
    fecha?: Moment;
    descripcion?: string;
    imagenContentType?: string;
    imagen?: any;
    productoId?: number;
    userLogin?: string;
    userId?: number;
}

export class Imagen implements IImagen {
    constructor(
        public id?: number,
        public fecha?: Moment,
        public descripcion?: string,
        public imagenContentType?: string,
        public imagen?: any,
        public productoId?: number,
        public userLogin?: string,
        public userId?: number
    ) {}
}
