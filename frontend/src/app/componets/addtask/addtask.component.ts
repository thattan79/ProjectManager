import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Options} from 'ng5-slider';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {UserDto} from "../../dto/user.dto";
import {UserService} from "../../service/user.service";
import {NgForm, ValidationErrors} from "@angular/forms";
import {ProjectDto} from "../../dto/project.dto";
import {ProjectService} from "../../service/project.service";
import {TaskDto} from "../../dto/task.dto";
import {DatePipe} from '@angular/common';
import {TaskService} from "../../service/task.service";
import {ParentTaskDto} from "../../dto/parent-task.dto";
import {Router} from "@angular/router";
import {Location} from "@angular/common";

@Component({
  selector: 'app-addtask',
  templateUrl: './addtask.component.html',
  styleUrls: ['./addtask.component.css']
})
export class AddtaskComponent implements OnInit {

  @ViewChild('taskForm') taskForm: NgForm;
  @Input() errors: ValidationErrors;

  bsModalRef: BsModalRef;
  _searchVal: string;
  search: boolean = false;
  userDtos: UserDto[] = [];
  projectDtos: ProjectDto[] = [];
  parentTaskDtos: ParentTaskDto[] = [];
  userDto: UserDto;
  taskDto: TaskDto;
  parentTaskDto: ParentTaskDto;
  projectDto: ProjectDto
  projectName: string = '';
  parentTaskName: string = '';
  firstName: string = '';
  parentTaskRef: boolean = false;

  value: number = 100;
  options: Options = {
    floor: 0,
    ceil: 30
  };

  constructor(private bsModalService: BsModalService,
              private userService: UserService,
              private projectService: ProjectService,
              private taskService: TaskService,
              private  datePipe: DatePipe,
              private router: Router,
              private location: Location
  ) {
    this.userDto = new UserDto();
    this.projectDto = new ProjectDto();
    this.taskDto = new TaskDto();
    this.parentTaskDto = new ParentTaskDto();
  }

  ngOnInit() {
    const startDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
    const date = new Date();
    date.setDate(date.getDate() + 1);
    const endDate = this.datePipe.transform(date, 'yyyy-MM-dd');
    this.taskDto.startDate = startDate;
    this.taskDto.endDate = endDate;

    this.userService.findAllUser().subscribe(
      (userDtos: UserDto[]) => {
        this.userDtos = userDtos;
      }
    );

    this.projectService.findAllProjects().subscribe(
      (projectDtos: ProjectDto[]) => {
        this.projectDtos = projectDtos;
      }
    );

    this.taskService.findAllParent().subscribe(
      (parentTaskDtos: ParentTaskDto[]) => {
        this.parentTaskDtos = parentTaskDtos;
      }
    );
  }

  _projectSearchVal: string;
  get projectSearchVal(): string {
    return this._projectSearchVal;
  }

  @Input('projectSearchVal')
  set projectSearchVal(input: string) {
    if (input === '') {
      input = 'default';
    }
    this.search = true;
    this.projectService.findAllProjectByInput(input).subscribe(
      (projectDtos: ProjectDto[]) => {
        this.projectDtos = projectDtos;
      }
    )
    if (input === 'default') {
      input = '';
    }
    this._projectSearchVal = input;
  }


  _parentTaskSearchVal: string;
  get parentTaskSearchVal(): string {
    return this._parentTaskSearchVal;
  }

  @Input('parentTaskSearchVal')
  set parentTaskSearchVal(input: string) {
    if (input === '') {
      input = 'default';
    }
    this.search = true;
    this.taskService.findAllParentTasksByInput(input).subscribe(
      (parentTaskDtos: ParentTaskDto[]) => {
        this.parentTaskDtos = parentTaskDtos;
      }
    )
    if (input === 'default') {
      input = '';
    }
    this._parentTaskSearchVal = input;
  }


  get searchVal(): string {
    return this._searchVal;
  }

  @Input('searchVal')
  set searchVal(input: string) {
    if (input === '') {
      input = 'default';
    }
    this.search = true;
    this.userService.findAllUserByInput(input).subscribe(
      (userDtos: UserDto[]) => {
        this.userDtos = userDtos;
      }
    )
    if (input === 'default') {
      input = '';
    }
    this._searchVal = input;
  }

  openModal(template: TemplateRef<any>) {
    this.bsModalRef = this.bsModalService.show(template);
  }

  selectUser(userDto: UserDto) {
    this.userDto = userDto;
    this.firstName = this.userDto.firstName;
  }

  selectProject(projectDto: ProjectDto) {
    this.projectDto = projectDto;
    this.projectName = this.projectDto.projectTitle;
  }

  selectParentTask(parentTaskDto: ParentTaskDto) {
    this.parentTaskDto = parentTaskDto;
    this.taskForm.control.get('parentTaskName').setValue(parentTaskDto.parentTaskName);
  }

  loadParentTask() {
    this.taskService.findAllParent().subscribe(
      (parentTaskDtos: ParentTaskDto[]) => {
        this.parentTaskDtos = parentTaskDtos;
      }
    );
  }

  onSubmit() {
    const parentTask = this.taskForm.control.get('parentTask').value;

    if (parentTask) {
      this.parentTaskDto = new ParentTaskDto();
      this.parentTaskDto.parentTaskName = this.taskForm.control.get('taskName').value;
      this.taskService.createParentTask(this.parentTaskDto).subscribe(
        () => {
          this.taskForm.reset();
        }
      );
    } else {
      const startDateRef = this.taskForm.control.get('startDate');
      const endDateRef = this.taskForm.control.get('endDate');
      const priorityRef = this.taskForm.control.get('priority');

      const projectTitleRef = this.taskForm.control.get('projectTitle');
      const parentRef = this.taskForm.control.get('parentTaskName');
      const userRef = this.taskForm.control.get('user');
      const d1 = new Date(startDateRef.value);
      const d2 = new Date(endDateRef.value);
      const priority = priorityRef.value;

      if (d1 > d2) {
        startDateRef.setErrors({
          'inValidDate': true
        });
        endDateRef.setErrors({
          'inValidDate': true
        });
      }

      if (priority <= 0) {
        priorityRef.setErrors({
          'invalidPriority': true
        });
      }

      if (projectTitleRef.value === '') {
        projectTitleRef.setErrors({
          'inValidProject': true
        });
      }

      if (userRef.value === '') {
        userRef.setErrors({
          'inValidUser': true
        });
      }

      if ((d1 > d2) || (projectTitleRef.value === '') || (priority <= 0)) {
        return;
      }
      this.taskDto.startDate = this.taskForm.control.get('startDate').value;
      this.taskDto.endDate = this.taskForm.control.get('endDate').value;
      this.taskDto.priority = this.taskForm.control.get('priority').value;
      this.taskDto.userId = this.userDto.userId;
      this.taskDto.projectId = this.projectDto.projectId;
      this.taskDto.parentTaskId = this.parentTaskDto.parentId;
      this.taskDto.taskName = this.taskForm.control.get('taskName').value;
      this.taskService.createTask(this.taskDto).subscribe(
        (response: TaskDto) => {
          this.taskForm.reset();
        }
      );
    }

    this.router.navigateByUrl('/refresh', {skipLocationChange: true}).then(() => {
      this.router.navigate([decodeURI(this.location.path())]);
    });
  }

  removeBorderColor() {
    const startDate = this.taskForm.control.get('startDate');
    const endDate = this.taskForm.control.get('endDate');
    if (startDate.value != null) {
      startDate.setErrors(null);
    }
    if (endDate.value != null) {
      endDate.setErrors(null);
    }
  }

  parentTaskConfirm() {
    this.parentTaskRef = this.taskForm.control.get('parentTask').value;
  }
}
