import { Component, OnInit } from '@angular/core';
import {Department, Employee} from "./employee";
import {TemplateRef, ViewChild} from '@angular/core';
import {EmployeeService} from "./employee.service";

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {
  @ViewChild('readOnlyTemplate') readonlyTemplate: TemplateRef<any>;
  @ViewChild('editTemplate') editTemplate: TemplateRef<any>;

  editedEmployee: Employee;
  department: Department;
  employees: Employee[];
  isNewRecord: boolean;
  message: string;


  constructor(private service: EmployeeService) { }


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
    this.department = new Department(0, "department");
    this.editedEmployee = new Employee(null, "","", new Date(), "employee", this.department);
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
                               employee.department
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
