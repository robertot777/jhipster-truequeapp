import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IImagen } from 'app/shared/model/imagen.model';

type EntityResponseType = HttpResponse<IImagen>;
type EntityArrayResponseType = HttpResponse<IImagen[]>;

@Injectable({ providedIn: 'root' })
export class ImagenService {
    public resourceUrl = SERVER_API_URL + 'api/imagens';

    constructor(protected http: HttpClient) {}

    create(imagen: IImagen): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(imagen);
        return this.http
            .post<IImagen>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(imagen: IImagen): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(imagen);
        return this.http
            .put<IImagen>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<IImagen>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IImagen[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(imagen: IImagen): IImagen {
        const copy: IImagen = Object.assign({}, imagen, {
            fecha: imagen.fecha != null && imagen.fecha.isValid() ? imagen.fecha.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((imagen: IImagen) => {
                imagen.fecha = imagen.fecha != null ? moment(imagen.fecha) : null;
            });
        }
        return res;
    }
}
