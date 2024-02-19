import { Component } from '@angular/core';
import {FishClass} from "../../../models/fish";
import {FishService} from "../../../services/fish/fish.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-fish-add',
  templateUrl: './fish-add.component.html',
  styleUrls: ['./fish-add.component.css']
})
export class FishAddComponent {
  public addFish = new FishClass();

  constructor(private fishService:FishService,
              private router:Router
  ){}

  onSubmit(){
    this.fishService.addFish(this.addFish).subscribe(
      (fish) => {
        this.router.navigate(['/fish/list']);
      },
      (error) => {
        alert("error occurred")
      }
    )
  }
}
