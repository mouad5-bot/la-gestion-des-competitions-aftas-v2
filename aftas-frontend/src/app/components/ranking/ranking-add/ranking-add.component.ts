import { Component } from '@angular/core';
import {Router} from "@angular/router";
import {RankingClass} from "../../../models/ranking";
import {MemberClass, IMember} from "../../../models/member";
import {RankingService} from "../../../services/ranking/ranking.service";
import {Observable} from "rxjs";
import {MemberService} from "../../../services/member/member.service";
import {CompetitionService} from "../../../services/competition/competition.service";

@Component({
  selector: 'app-ranking-add',
  templateUrl: './ranking-add.component.html',
  styleUrls: ['./ranking-add.component.css']
})
export class RankingAddComponent {

  public addRanking = new RankingClass();

  public memberList  = this.memberService.getMembers();
  public selectedMember: number | undefined;

  public competitionList = this.competitionService.getCompetitions();
  public selectedCompetition :string | undefined;


  constructor(private rankingService: RankingService,
              private memberService: MemberService,
              private competitionService: CompetitionService,
              private router: Router) {}
  onSubmit() {

    this.addRanking.competition_code = this.selectedCompetition;
    this.addRanking.member_id = this.selectedMember;
    this.addRanking.rank = 0;
    this.addRanking.score = 0;

     console.log('Request Payload :', this.addRanking);

    this.rankingService.addRanking(this.addRanking).subscribe(
      (ranking) => {
        console.log("Ranking added by success !", ranking);
        this.router.navigate(['/ranking/list']);
      },
      (error) => {
        alert("error occured")
      }
    )
  }

}
