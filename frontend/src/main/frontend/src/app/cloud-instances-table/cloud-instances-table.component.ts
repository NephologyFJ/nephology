import { Component } from '@angular/core';
import { CloudInstanceComponent } from '../cloud-instance/cloud-instance.component';
import { CloudsService } from '../clouds.service';
import { Response } from '@angular/http';

@Component({
  selector: 'app-cloud-instances-table',
  templateUrl: './cloud-instances-table.component.html',
})

export class CloudInstancesTableComponent {

  clouds: CloudInstanceComponent[];

  constructor(private cloudsService: CloudsService) {
    this.getCloudsInstances();
  }

  getCloudsInstances() { this.cloudsService.getClouds().subscribe(
      (response) => {
        const data = response.json();
        this.clouds = data._embedded.awsEC2InstanceDetailsDatas;
      },
      (error) => console.log(error)
    );
  }
}
