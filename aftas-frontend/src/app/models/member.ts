  export class MemberClass implements IMember{
    constructor(
          public num?: number,
          public name?: string,
          public familyName?: string,
          public accessDate?: Date,
          public nationality?: string,
          public identityDocumentType?: string,
          public identityNumber?: string
    ) { }
  }
  export interface IMember{
    num?: number,
    name?: string,
    familyName?: string,
    accessDate?: Date,
    nationality?: string,
    identityDocumentType?: string,
    identityNumber?: string
  }
