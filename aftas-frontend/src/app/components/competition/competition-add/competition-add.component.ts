import { Component } from '@angular/core';
import {CompetitionClass} from "../../../models/competition";
import {CompetitionService} from "../../../services/competition/competition.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-competition-add',
  templateUrl: './competition-add.component.html',
  styleUrls: ['./competition-add.component.css']
})
export class CompetitionAddComponent {

  public addCompetition = new CompetitionClass();

  constructor(private competitionService: CompetitionService,
              private router: Router) {}
  onSubmit() {

    console.log(this.addCompetition);

    this.competitionService.addCompetition(this.addCompetition).subscribe(
      (competition) => {
        this.router.navigate(['/competition/list']);
      },
      (error) => {
        alert("error occurred")
      }
    )

  }

}
