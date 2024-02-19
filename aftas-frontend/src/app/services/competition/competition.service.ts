import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {CompetitionClass, ICompetition} from "../../models/competition";
import {ApiResponseModel} from "../../core/api-response.model";


@Injectable({
  providedIn: 'root'
})
export class CompetitionService {

  private api = "http://localhost:8080/api/competition/";

  constructor( private  http: HttpClient) { }

  public getCompetitions(): Observable<ICompetition[]> {
    return this.http.get<ApiResponseModel<ICompetition[]>>(this.api + "all")
                    .pipe(
                      map(res => res.data)
                    );
  }

  public addCompetition(competition: CompetitionClass): Observable<ICompetition>{
    return this.http.post(this.api + "add", competition);
  }
}
