import { Component } from '@angular/core';
import { CloudInstanceComponent } from '../cloud-instance/cloud-instance.component';

@Component({
  selector: 'app-cloud-instances-table',
  templateUrl: './cloud-instances-table.component.html',
})

export class CloudInstancesTableComponent {
  cloudInstances = CLOUDINSTANCES;
}

const CLOUDINSTANCES: CloudInstanceComponent[] = [
  {
    instanceId: "SomeId1",
    imageId: "SomeImageId1",
    keyName: "SomeKeyName1",
    instanceType: "SomeType1",
    subnetId: "SomeSubnet1"
  },
  {
    instanceId: "SomeId2",
    imageId: "SomeImageId2",
    keyName: "SomeKeyName2",
    instanceType: "SomeType2",
    subnetId: "SomeSubnet2"
  },
  {
    instanceId: "SomeId3",
    imageId: "SomeImageId3",
    keyName: "SomeKeyName3",
    instanceType: "SomeType3",
    subnetId: "SomeSubnet3"
  },
  {
    instanceId: "SomeId4",
    imageId: "SomeImageId4",
    keyName: "SomeKeyName4",
    instanceType: "SomeType4",
    subnetId: "SomeSubnet4"
  },
  {
    instanceId: "SomeId5",
    imageId: "SomeImageId5",
    keyName: "SomeKeyName5",
    instanceType: "SomeType5",
    subnetId: "SomeSubnet5"
  },
  {
    instanceId: "SomeId6",
    imageId: "SomeImageId6",
    keyName: "SomeKeyName6",
    instanceType: "SomeType6",
    subnetId: "SomeSubnet6"
  },
  {
    instanceId: "SomeId7",
    imageId: "SomeImageId7",
    keyName: "SomeKeyName7",
    instanceType: "SomeType7",
    subnetId: "SomeSubnet7"
  },
  {
    instanceId: "SomeId8",
    imageId: "SomeImageId8",
    keyName: "SomeKeyName8",
    instanceType: "SomeType8",
    subnetId: "SomeSubnet8"
  },
  {
    instanceId: "SomeId9",
    imageId: "SomeImageId9",
    keyName: "SomeKeyName9",
    instanceType: "SomeType9",
    subnetId: "SomeSubnet9"
  },
  {
    instanceId: "SomeId10",
    imageId: "SomeImageId10",
    keyName: "SomeKeyName10",
    instanceType: "SomeType10",
    subnetId: "SomeSubnet10"
  },
];
