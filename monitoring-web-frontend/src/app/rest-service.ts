
import {throwError as observableThrowError, Observable} from 'rxjs';
import {HttpErrorResponse} from '@angular/common/http';
import {_throw} from 'rxjs-compat/observable/throw';


export class RestService {

  protected serviceUrl: string;
  protected extractData(response) {
    return response || {};
  }

  //use this for download methods
  parseErrorBlob(err: HttpErrorResponse): Observable<any> {
    const reader: FileReader = new FileReader();

    const obs = Observable.create((observer: any) => {
      reader.onloadend = (e) => {
        observer.error(JSON.parse(<string>reader.result));
        observer.complete();
      }
    });
    reader.readAsText(err.error);
    return obs;
  }

  protected throwError(error): Observable<String> {
    const errorResponse = 'error ist null.';
    return observableThrowError(errorResponse);
  }

  protected handleError(error) {
    // In a real world app, we might use a remote logging infrastructure
    let errMsg: string;
    if (error instanceof HttpErrorResponse
    ) {
      const err = error.error.errorCode + ' ' + error.error.errorMessage;
      errMsg = `${error.status} - ${error.statusText || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return _throw(error.error);
  }

}
