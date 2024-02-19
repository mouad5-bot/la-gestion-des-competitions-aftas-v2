export class FishClass implements IFish{
  constructor(
    public id?:number,
    public name?:string,
    public averageWeight?: number
  ) { }
}

export interface IFish{
   id?:number,
   name?:string,
   averageWeight?: number
}
