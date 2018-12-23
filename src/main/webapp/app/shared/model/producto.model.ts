import { Moment } from 'moment';
import { IImagen } from 'app/shared/model//imagen.model';

export const enum TipoOferta {
    ALIMENTACION = 'ALIMENTACION',
    BEBIDAS = 'BEBIDAS',
    ARTE = 'ARTE',
    ANTIGUEDADES = 'ANTIGUEDADES',
    VEHICULOS = 'VEHICULOS',
    CASAHOGAR = 'CASAHOGAR',
    COLECCION = 'COLECCION',
    DEPORTES = 'DEPORTES',
    EDUCACION = 'EDUCACION',
    IMAGEN = 'IMAGEN',
    SONIDO = 'SONIDO',
    INFORMATICA = 'INFORMATICA',
    ELECTRONICA = 'ELECTRONICA',
    INMOBILIARIA = 'INMOBILIARIA',
    JOYERIA = 'JOYERIA',
    JUEGOS = 'JUEGOS',
    JUGUETES = 'JUGUETES',
    LIBROS = 'LIBROS',
    REVISTAS = 'REVISTAS',
    COMICS = 'COMICS',
    MUSICA = 'MUSICA',
    ROPA = 'ROPA',
    CALZADO = 'CALZADO',
    SALUD = 'SALUD',
    BELLEZA = 'BELLEZA',
    SERVICIOS = 'SERVICIOS',
    MANOOBRA = 'MANOOBRA',
    TELEFONIA = 'TELEFONIA',
    TIEMPOLIBRE = 'TIEMPOLIBRE'
}

export interface IProducto {
    id?: number;
    fecha?: Moment;
    descripcion?: string;
    tipo?: TipoOferta;
    detalle?: any;
    valor?: number;
    userLogin?: string;
    userId?: number;
    imagenProductos?: IImagen[];
}

export class Producto implements IProducto {
    constructor(
        public id?: number,
        public fecha?: Moment,
        public descripcion?: string,
        public tipo?: TipoOferta,
        public detalle?: any,
        public valor?: number,
        public userLogin?: string,
        public userId?: number,
        public imagenProductos?: IImagen[]
    ) {}
}
