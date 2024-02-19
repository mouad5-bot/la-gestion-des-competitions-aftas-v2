import {Component, OnInit} from '@angular/core';
import {IRanking} from "../../../models/ranking";
import {RankingService} from "../../../services/ranking/ranking.service";

@Component({
  selector: 'app-ranking-list',
  templateUrl: './ranking-list.component.html',
  styleUrls: ['./ranking-list.component.css']
})
export class RankingListComponent implements OnInit{
  public RankingList : IRanking[] = [];

  constructor( private rankingService:RankingService) {
  }

  ngOnInit(): void {
      this.getRankings();
    }

  private getRankings() {
    this.rankingService.getRankings().subscribe(
      (response: IRanking[]) => {
        this.RankingList = response;
      },
      (error) => {
        console.error("Error fetching ranking List: ", error);
      }
    );
  }
}
