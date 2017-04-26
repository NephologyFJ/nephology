import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
//rxjs/RX required for .map
import 'rxjs/RX';

@Injectable()
export class CloudsService {
  constructor(private http: Http) {}
  getClouds() {
    return this.http.get('/api/awsEC2InstanceDetailsDatas')
  }

}
