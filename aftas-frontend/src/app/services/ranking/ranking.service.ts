import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {IRanking, RankingClass} from "../../models/ranking";
import {ApiResponseModel} from "../../core/api-response.model";
import {CompetitionClass, ICompetition} from "../../models/competition";
import {MemberService} from "../member/member.service";

@Injectable({
  providedIn: 'root'
})
export class RankingService {

  private api = "http://localhost:8080/api/ranking/";

  constructor( private http: HttpClient, private memberService:MemberService) { }

  public members = this.memberService.getMembers();
  public getRankings(): Observable<IRanking[]>{
    return this.http.get<ApiResponseModel<IRanking[]>>(this.api + "all")
      .pipe(
        map(res => res.data)
      );
  }

  public addRanking(ranking: RankingClass): Observable<IRanking>{
    return this.http.post(this.api + "add", ranking);
  }

}
