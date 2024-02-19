import { Component } from '@angular/core';
import {IHunting} from "../../../models/hunting";
import {HuntingService} from "../../../services/hunting/hunting.service";

@Component({
  selector: 'app-hunting',
  templateUrl: './hunting.component.html',
  styleUrls: ['./hunting.component.css']
})
export class HuntingComponent {
  public huntList: IHunting[] = [];

  constructor(private huntingService: HuntingService) {}

  ngOnInit() {
    this.getHuntList();
  }

  private getHuntList(): void {
    this.huntingService.getHuntings().subscribe(
      ( res: IHunting[] ) => {
        console.log("data is :" + res);
        this.huntList = res;
      },
      (error) => {
        console.log('Error fetching Hunting :', error);
      }
    );
  }
}
