import { Component } from '@angular/core';
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable} from "rxjs";
import {catchError, map} from "rxjs/operators";
import {_throw} from "rxjs-compat/observable/throw";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'frontend';
  incomingMessages='';
  messageBody: string;
  constructor(private http: HttpClient){
    console.log('init....');
    const StreamrClient = require('streamr-client');

    const streamr = new StreamrClient({
      auth: {
        privateKey: '0xe8757ed49640cb85f61a2fb89e185e5bbe90347d70ebf7354d074f49996acb9d',
      },
    })

    // Subscribe to a stream
    streamr.subscribe({
      stream: 'GMtIlLlgSJKNJUNLbGE1KQ',
    }, (message, metadata) => {
      // Do something with the message here!
      console.log('message');
      this.incomingMessages=this.incomingMessages+'\n'+message['message'];
      console.log(message['message']);
    })
  }

  private extractData(res) {
    return res || {};
  }

  private  handleError(error) {
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


  sendMessage() {
    this.sendMessageService().subscribe(result => {
      console.log("Result is:" + result);
    })
  }

  sendMessageService(): Observable<string[]> {
    console.log("going to send a message" + this.messageBody);
    return this.http.post('/api/push/v1/send', this.messageBody)
      .pipe(map(this.extractData)
        , catchError(this.handleError));
  }

}
