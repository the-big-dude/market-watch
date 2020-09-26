
export class TaharokatModel {

  constructor(public id: string,
              public sahmName: string,
              public kharidarBForooshandehList: number[],
              public saranehKharidList: number[],
              public jumpNesbat: number,
              public jumpSaraneh: number,
              public plusD: number,
              public plusN: number,
              public minusN: number,
              public m: number,
              public mBd: number) {
  }
}

export class Page<T>
{
  constructor(public content: T[], public totalElements: number) {
  }
}
