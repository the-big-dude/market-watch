import {Injectable} from '@angular/core';
import {Page} from './taharokat.model';
import {Observable} from 'rxjs';
import {catchError, map} from 'rxjs/operators';
import {HttpClient} from "@angular/common/http";
import {TaharokatModel} from "./taharokat.model";
import {RestService} from "../rest-service";


@Injectable()
export class TaharokatService extends RestService {

  constructor(public http: HttpClient) {
    super();
    this.serviceUrl = '/api/taharokat';
  }

  public showTaharokatModelOverviewPage(q: string, pageSize: number, pageNumber: number): Observable<Page<TaharokatModel>> {
    const url = this.serviceUrl
      + '?q=' + encodeURIComponent(q)
      + '&size=' + pageSize
      + '&page=' + pageNumber;
    console.log("going to get taharokat");
    console.log(url);
    return this.http.get(url)
      .pipe(
        map(response => this.extractData(response)),
        catchError(error => this.throwError(error))
      );
  }

  public findTaharokatModelById(id: string): Observable<TaharokatModel> {
    const url = this.serviceUrl + '/' + encodeURIComponent(id);
    return this.http.get(url)
      .pipe(
        map(response => this.extractData(response)),
        catchError(error => this.throwError(error))
      );
  }

  public createTaharokatModel(article: TaharokatModel): Observable<any> {
    const url = this.serviceUrl;
    return this.http.post(url, article)
      .pipe(
        catchError(error => this.throwError(error))
      );
  }

  public findTaharokatModelByName(name: string): Observable<TaharokatModel[]> {
    const url = this.serviceUrl + '/name'
      + '?name=' + encodeURIComponent(name);
    return this.http.get(url)
      .pipe(
        map(response => this.extractData(response)),
        catchError(error => this.throwError(error))
      );
  }

  public deleteTaharokatModel(id: string): Observable<any> {
    const url = this.serviceUrl + '/' + encodeURIComponent(id);
    return this.http.delete(url)
      .pipe(
        catchError(error => this.throwError(error))
      );
  }
}
