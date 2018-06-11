export class User {

  constructor(
    public username: string,
    public password: string,
    public authorities: Authoruty[]
  ) {}
}

export class Authoruty {
  public name: string;
  public id: number;

  constructor(id: number) {
    this.id = id;
  }
}
