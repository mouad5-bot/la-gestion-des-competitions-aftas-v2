export class RankingClass implements IRanking{
  constructor(
    public member_id?: number,
    public competition_code?: string,
    public rank?: number,
    public score?: number,
  ) { }
}
export interface IRanking{
  member_id?: number,
  competition_code?: string,
  rank?: number,
  score?: number,
}
