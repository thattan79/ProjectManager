import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {TaskService} from "../../service/task.service";
import {TaskDto} from "../../dto/task.dto";
import {Options} from "ng5-slider";
import {NgForm, ValidationErrors} from "@angular/forms";
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {UserDto} from "../../dto/user.dto";
import {UserService} from "../../service/user.service";
import {ProjectService} from "../../service/project.service";
import {ProjectDto} from "../../dto/project.dto";
import {ParentTaskDto} from "../../dto/parent-task.dto";
import {Router} from "@angular/router";

@Component({
  selector: 'app-edittask',
  templateUrl: './edittask.component.html',
  styleUrls: ['./edittask.component.css']
})
export class EdittaskComponent implements OnInit {

  taskDto: TaskDto;
  //nTaskDto: TaskDto;
  bsModalRef: BsModalRef;
  userDto: UserDto;
  projectDto: ProjectDto;
  //userId: number = 0;
  //projectId: number = 0;
  parentTaskDtos: ParentTaskDto[] = [];
  parentTaskDto: ParentTaskDto;
  projectName: string;

  @ViewChild('taskForm') taskForm: NgForm;
  @Input() errors: ValidationErrors;

  options: Options = {
    floor: 0,
    ceil: 30
  };

  constructor(private taskService: TaskService,
              private userService: UserService,
              private projectService: ProjectService,
              private bsModalService: BsModalService,
              private router: Router
  ) {
    this.taskDto = new TaskDto();
    // this.nTaskDto = new TaskDto();
    this.userDto = new UserDto();
    this.projectDto = new ProjectDto();
    this.parentTaskDto = new ParentTaskDto();
  }

  ngOnInit() {
    this.taskDto = this.taskService.getTaskDto();
    this.userDto = this.taskDto.userDto;
    this.projectDto = this.taskDto.projectDto;
    this.parentTaskDto = this.taskDto.parentTaskDto;
  }

  onSubmit() {
    const startDateRef = this.taskForm.control.get('startDate');
    const endDateRef = this.taskForm.control.get('endDate');
    const priorityRef = this.taskForm.control.get('priority');
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

    if ((d1 > d2) || (priority <= 0)) {
      return;
    }
    // this.nTaskDto = new TaskDto();
    //this.nTaskDto.taskId = this.taskDto.taskId;
    //this.nTaskDto.startDate = this.taskForm.control.get('startDate').value;
    //this.nTaskDto.endDate = this.taskForm.control.get('endDate').value;
    //this.nTaskDto.priority = this.taskForm.control.get('priority').value;
    //this.nTaskDto.userId = this.userDto.userId;
    //this.nTaskDto.projectId = this.projectDto.projectId;
    //this.nTaskDto.parentTaskId = this.taskForm.control.get('parentId').value;
    //this.nTaskDto.taskName = this.taskForm.control.get('taskName').value;
    this.projectName = this.projectDto.projectTitle;
    this.taskDto.userId = this.userDto.userId;
    this.taskDto.projectId = this.projectDto.projectId;
    this.taskDto.parentTaskId = this.taskForm.control.get('parentId').value;
    this.taskService.updateTask(this.taskDto).subscribe(
      (response: TaskDto) => {
        this.router.navigate(['/viewtask'], {queryParams: {projectName: this.projectName}});
        this.taskForm.reset();
      }
    );
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

  openModal(template: TemplateRef<any>) {
    this.bsModalRef = this.bsModalService.show(template);
  }

  loadParentTask() {
    this.taskService.findAllParent().subscribe(
      (parentTaskDtos: ParentTaskDto[]) => {
        this.parentTaskDtos = parentTaskDtos;
      }
    );
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

  selectParentTask(parentTaskDto: ParentTaskDto) {
    this.parentTaskDto = parentTaskDto;
    this.taskForm.control.get('parentTaskName').setValue(parentTaskDto.parentTaskName);
  }
}
