import { Component } from '@angular/core';

@Component({
  selector: 'app-cloud-instance',
  template: './cloud-instance.component.html'
})

export class CloudInstanceComponent {
  instanceId: string;
  imageId: string;
  keyName: string;
  instanceType: string;
  subnetId: string;
}
