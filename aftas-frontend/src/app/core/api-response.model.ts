
export interface ApiResponseModel<T>{
  status:string;
  message:string;
  data: T;
  errors: Map<string, string>;
}
