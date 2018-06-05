export class Employee {

  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public recruitment: Date,
    public type: string,
    public department: Department) { }
}

export class Department{

  constructor(
    public id: number,
    public type: string
  ) {}
}
