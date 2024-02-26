export class UserClass implements IUser{
  id?: number;
  token?: string;
  name?: string;
  email?: string;
  authorities?: string[];
}
export interface IUser{
  id?:number,
  token?: string;
  name?: string;
  email?: string;
  authorities?: string[];
}
