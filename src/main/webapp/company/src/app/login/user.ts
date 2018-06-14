export class User {
  public type: string;

  constructor(
    public username: string,
    public password: string,
    public authorities: Authoruty[])
  {
    this.type = 'user';
  }
}

export class Authoruty {
  public name: string;
  public id: number;
  public type: string;

  constructor(id: number, name: string) {
    this.id = id;
    this.name = name;
    this.type = 'authority';
  }
}
