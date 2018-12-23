import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITrueque } from 'app/shared/model/trueque.model';

type EntityResponseType = HttpResponse<ITrueque>;
type EntityArrayResponseType = HttpResponse<ITrueque[]>;

@Injectable({ providedIn: 'root' })
export class TruequeService {
    public resourceUrl = SERVER_API_URL + 'api/trueques';

    constructor(protected http: HttpClient) {}

    create(trueque: ITrueque): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trueque);
        return this.http
            .post<ITrueque>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(trueque: ITrueque): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(trueque);
        return this.http
            .put<ITrueque>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http
            .get<ITrueque>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<ITrueque[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(trueque: ITrueque): ITrueque {
        const copy: ITrueque = Object.assign({}, trueque, {
            fecha: trueque.fecha != null && trueque.fecha.isValid() ? trueque.fecha.format(DATE_FORMAT) : null
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
            res.body.forEach((trueque: ITrueque) => {
                trueque.fecha = trueque.fecha != null ? moment(trueque.fecha) : null;
            });
        }
        return res;
    }
}
