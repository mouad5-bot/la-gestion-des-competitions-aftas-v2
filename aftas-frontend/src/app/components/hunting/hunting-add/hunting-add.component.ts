import { Component } from '@angular/core';
import {MemberService} from "../../../services/member/member.service";
import {CompetitionService} from "../../../services/competition/competition.service";
import {Router} from "@angular/router";
import {HuntingService} from "../../../services/hunting/hunting.service";
import {HuntingClass} from "../../../models/hunting";
import {FishService} from "../../../services/fish/fish.service";

@Component({
  selector: 'app-hunting-add',
  templateUrl: './hunting-add.component.html',
  styleUrls: ['./hunting-add.component.css']
})
export class HuntingAddComponent {

  public addHunt = new HuntingClass();

  public memberList  = this.memberService.getMembers( );
  public selectedMember: number | undefined;

  public competitionList = this.competitionService.getCompetitions();
  public selectedCompetition :string | undefined;

  public fishList = this.fishService.getFish();
  public selectedFish: number | undefined;

  constructor(
        private huntingService: HuntingService,
        private memberService: MemberService,
        private competitionService: CompetitionService,
        private fishService: FishService,
        private router: Router
  ) {}

  onSubmit() {
console.log("data is here :" + this.addHunt);
    this.addHunt.competitionCode = this.selectedCompetition;
    this.addHunt.fishId = this.selectedFish;
    this.addHunt.memberNum = this.selectedMember;
console.log("data is here :" + this.addHunt);

    this.huntingService.addHunting(this.addHunt).subscribe(
      (hunt) => {
        console.log("Hunting added by success !", hunt);
        this.router.navigate(['/hunting/list']);
      },
      (error) => {
        alert("error occurred")
      }
    )
  }
}
