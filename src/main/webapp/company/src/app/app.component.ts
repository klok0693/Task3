import { Component, OnInit } from '@angular/core';
import {Employee} from "./employee";
import {AppService} from "./app.service";
import {TemplateRef, ViewChild} from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  @ViewChild('readOnlyTemplate') readonlyTemplate: TemplateRef<any>;
  @ViewChild('editTemplate') editTemplate: TemplateRef<any>;

  editedEmployee: Employee;
  employees: Employee[];
  isNewRecord: boolean;
  message: string;


  constructor(private service: AppService) { }


  ngOnInit(): void {
    this.loadEmployees();
  }

  /*getEmployees(): void {
    this.service.getEmployees().subscribe(employees => this.employees = employees);
  }*/

  loadEmployees(): void {
    this.service.getEmployees().subscribe((data: Employee[]) => this.employees = data);
  }


  addEmployee(): void {
    this.editedEmployee = new Employee(0, "","", null, "", 0);
    this.employees.push(this.editedEmployee);
    this.isNewRecord = true;
  }

  editEmployee(employee: Employee): void {
    this.editedEmployee = new Employee(
                               employee.id,
                               employee.firstName,
                               employee.lastName,
                               employee.recruitment,
                               employee.type,
                               employee.departmentId
    );
  }

  loadTemplate(employee: Employee): TemplateRef<any> {
    if (this.editedEmployee && this.editedEmployee.id == employee.id) {
      return this.editTemplate;
    }
    else {
      return this.readonlyTemplate;
    }
  }

  saveEmployee(): void {
    if (this.isNewRecord) {
      this.service.createEmployee(this.editedEmployee).subscribe(data => {
        this.reloadEmployees();
      });
      this.isNewRecord = false;
    }
    else {
      this.service.updateEmployee(this.editedEmployee.id, this.editedEmployee).subscribe(data => {
        this.reloadEmployees();
      });
    }
    this.editedEmployee = null;
  }

  private reloadEmployees() {
    this.message = "Success";
    this.loadEmployees();
  }

  cancel(): void {
    if (this.isNewRecord) {
      this.employees.pop();
      this.isNewRecord = false;
    }
    this.editedEmployee = null;
  }

  deleteEmployee(employee: Employee): void {
    this.service.deleteEmployee(employee.id).subscribe(data => {
      this.reloadEmployees();
    })
  }
}