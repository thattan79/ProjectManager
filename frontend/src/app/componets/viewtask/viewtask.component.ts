import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {ProjectService} from "../../service/project.service";
import {ProjectDto} from "../../dto/project.dto";
import {TaskDto} from "../../dto/task.dto";
import {TaskService} from "../../service/task.service";
import {NgForm} from '@angular/forms';
import {Router, ActivatedRoute, Params} from "@angular/router";
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {UserDto} from "../../dto/user.dto";

@Component({
  selector: 'app-viewtask',
  templateUrl: './viewtask.component.html',
  styleUrls: ['./viewtask.component.css']
})
export class ViewtaskComponent implements OnInit {

  projectDtos: ProjectDto[] = [];
  taskDtos: TaskDto[] = [];
  bsModalRef: BsModalRef;
  projectDto: ProjectDto;
  projectName: string;

  @ViewChild('viewTaskForm') viewTaskForm: NgForm;

  constructor(private projectService: ProjectService,
              private router: Router,
              private activatedRoute: ActivatedRoute,
              private taskService: TaskService,
              private bsModalService: BsModalService) {
    this.projectDto = new ProjectDto();
  }

  ngOnInit() {
    this.projectDtos = [];
    this.taskDtos = [];

    this.projectName = this.activatedRoute.snapshot.queryParamMap.get("projectName")
    if (this.projectName != null) {
      this.projectService.findAllProjectByInput(this.projectName).subscribe(
        (projectDtos: ProjectDto[]) => {
          this.projectDtos = projectDtos;
          if (this.projectDtos != null) {
            this.projectDtos.forEach(
              (projectDto => {
                projectDto.taskDtos.forEach(
                  (taskDto) => {
                    taskDto.projectId = projectDto.projectId
                    //taskDto.userId = projectDto.userDto.userId;
                    taskDto.userId = projectDto.userId;
                    this.taskDtos.push(taskDto);
                  })
              }))
          }
        })
    }
  }

  onEditTask(taskDto: TaskDto) {
    this.taskService.setTaskDto(taskDto)
    this.router.navigate(['edittask'])
  }

  updateTaskStatus(taskDto: TaskDto) {
    this.taskService.updateTaskStatus(taskDto).subscribe();
  }

  displayTasks() {
    this.taskDtos = this.projectDto.taskDtos;
  }

  openModal(template: TemplateRef<any>) {
    this.bsModalRef = this.bsModalService.show(template);
  }

  _searchVal: string;

  get searchVal(): string {
    return this._searchVal;
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

  selectProject(projectDto: ProjectDto) {
    this.projectDto = projectDto;
    this.projectName = this.projectDto.projectTitle;
  }


  listAllProjects() {
    this.projectService.findAllProjects().subscribe(
      (projectDtos: ProjectDto[]) => {
        this.projectDtos = projectDtos;
      }
    );
  }
}
