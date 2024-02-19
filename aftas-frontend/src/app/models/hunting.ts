export class HuntingClass implements IHunting{
  constructor(
    public fishWeight?: number,
    public fishId?: number,
    public memberNum?: number,
    public competitionCode?: string,
    public memberName?: string,
    public numberOfFish?: number
  ) { }
}

export interface IHunting{
   fishWeight?: number,
   fishId?: number,
   memberNum?: number,
   competitionCode?: string,
    memberName?: string,
    numberOfFish?: number
}
