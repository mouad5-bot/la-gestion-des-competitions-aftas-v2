export class CompetitionClass implements ICompetition{
  constructor(
       public code?: string,
       public date?: Date,
       public startTime?: string,
       public endTime?: string,
       public numberOfParticipants?: number,
       public location?: string,
       public amountOfFish?: number
  ) { }
}

export interface ICompetition{
  code?: string,
  date?: Date,
  startTime?: string,
  endTime?: string,
  numberOfParticipants?: number,
  location?: string,
  amountOfFish?: number

}
