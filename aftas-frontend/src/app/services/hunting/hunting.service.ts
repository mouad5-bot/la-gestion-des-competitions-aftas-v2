import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {MemberService} from "../member/member.service";
import {CompetitionService} from "../competition/competition.service";
import {FishService} from "../fish/fish.service";
import {map, Observable} from "rxjs";
import {HuntingClass, IHunting} from "../../models/hunting";
import {ApiResponseModel} from "../../core/api-response.model";
import {IRanking, RankingClass} from "../../models/ranking";

@Injectable({
  providedIn: 'root'
})
export class HuntingService {

  private api = "http://localhost:8080/api/hunt/";

  constructor(private http: HttpClient,
              private memberService: MemberService,
              private competitionService: CompetitionService,
              private fishService: FishService
  ) {}

  public getHuntings(): Observable<IHunting[]>{
    return this.http.get<ApiResponseModel<IHunting[]>>(this.api + "all")
                    .pipe(
                      map(res => res.data)
                    );
  }
  public addHunting(hunting: HuntingClass): Observable<IHunting>{
    return this.http.post(this.api + "add", hunting);
  }
}
