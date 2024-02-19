import { Component } from '@angular/core';
import {FishService} from "../../../services/fish/fish.service";
import {IFish} from "../../../models/fish";

@Component({
  selector: 'app-fish',
  templateUrl: './fish.component.html',
  styleUrls: ['./fish.component.css']
})
export class FishComponent {
  public fishList: IFish[] = [];

  constructor(private fishService: FishService) {}

  ngOnInit() {
    this.getFishes();
  }

  private getFishes(): void {
    this.fishService.getFish().subscribe(
      ( res: IFish[] ) => {
        this.fishList = res;
      },
      (error) => {
        console.log('Error fetching fishes :', error);
      }
    );
  }
}
