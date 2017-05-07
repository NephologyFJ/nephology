import { Injectable } from '@angular/core';

@Injectable()
export class CloudInstanceService {
  instanceId: string;
  imageId: string;
  keyName: string;
  instanceType: string;
  subnetId: string;
}
