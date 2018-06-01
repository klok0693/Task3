export class Employee {
  /*id:           number;
  firstName:    string;
  lastName:     string;
  recruitment:  Date;
  type:         string;*/

  constructor(
    public id: number,
    public firstName: string,
    public lastName: string,
    public recruitment: Date,
    public type: string,
    public departmentId: number) { }
}
