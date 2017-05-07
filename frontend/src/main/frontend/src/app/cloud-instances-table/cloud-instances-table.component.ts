import { Component } from '@angular/core';
import { CloudsService } from '../services/clouds.service';
import { Response } from '@angular/http';
import { CloudInstanceService } from '../services/cloud-instance.service'

@Component({
  selector: 'app-cloud-instances-table',
  templateUrl: './cloud-instances-table.component.html',
})

export class CloudInstancesTableComponent {

  constructor(private cloudsService: CloudsService, private cloudInstanceService: CloudInstanceService) {
    this.getCloudsInstances();
  }

  clouds: CloudInstanceService[];

  getCloudsInstances() { this.cloudsService.getClouds().subscribe(
      (response) => {
        const data = response.json();
        this.clouds = data._embedded.awsEC2InstanceDetailsDatas;
      },
      (error) => console.log(error)
    );
  }
}
