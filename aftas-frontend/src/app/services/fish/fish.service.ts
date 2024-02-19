import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {ApiResponseModel} from "../../core/api-response.model";
import {FishClass, IFish} from "../../models/fish";

@Injectable({
  providedIn: 'root'
})
export class FishService {  private api = "http://localhost:8080/api/fish/";

  constructor( private  http: HttpClient) { }

  public getFish(): Observable<IFish[]> {
    return this.http.get<ApiResponseModel<IFish[]>>(this.api + "all")
      .pipe(
        map(res => res.data)
      );
  }

  public addFish(fish: FishClass): Observable<IFish>{
    return this.http.post(this.api + "add", fish);
  }
}
