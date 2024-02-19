import {Component, OnInit} from '@angular/core';
import {ICompetition} from "../../../models/competition";
import {CompetitionService} from "../../../services/competition/competition.service";

@Component({
  selector: 'app-competition',
  templateUrl: './competition.component.html',
  styleUrls: ['./competition.component.css']
})
export class CompetitionComponent implements OnInit{
  public competitions: ICompetition[] = [];

  constructor(private competitionService: CompetitionService) {}

  ngOnInit() {
    this.getCompetitions();
  }

  private getCompetitions(): void {
    this.competitionService.getCompetitions().subscribe(
      ( res: ICompetition[] ) => {
        this.competitions = res;
      },
      (error) => {
        console.log('Error fetching competitions :', error);
      }
    );
  }
}
