import {Component, Input, OnChanges, SimpleChanges} from '@angular/core';
import {ProjectDto} from "../../dto/project.dto";
import {ProjectService} from "../../service/project.service";
import {ProjectSortService} from "../../service/project-sort.service";

@Component({
  selector: 'app-listproject',
  templateUrl: './listproject.component.html',
  styleUrls: ['./listproject.component.css']
})
export class ListprojectComponent implements OnChanges {

  @Input('projectDto') projectDto: ProjectDto
  projectDtos: ProjectDto[] = [];
  search: boolean = false;
  noOfTasks: number = 0;
  taskCompleted: number = 0;

  _searchVal: string;
  get searchVal(): string {
    return this._searchVal;
  }

  @Input('searchVal')
  set searchVal(input: string) {
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
    this._searchVal = input;
  }

  constructor(private projectService: ProjectService,
              private projectSortService: ProjectSortService) {
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.projectService.findAllProjects().subscribe(
      (projectDtos: ProjectDto[]) => {
        this.projectDtos = projectDtos;
        /*        this.projectDtos.forEach(
                  (projectDto) => {
                    this.taskCompleted = 0;
                    this.noOfTasks = projectDto.taskDtos.length;
                    projectDto.totalNoOfTasks = this.noOfTasks;
                    projectDto.taskDtos.forEach(
                      (taskDto: TaskDto) => {
                        if (taskDto.status === 'COMPLETED') {
                          this.taskCompleted = this.taskCompleted + 1;
                        }
                      }
                    )
                    projectDto.totalNoOfCompletedTasks = this.taskCompleted;
                  }
                )*/
      }
    )
  }

  onSort(field) {
    this.projectSortService.setProjectDtos(this.projectDtos);
    this.projectSortService.sortByProjectField(field);
  }

  onUpdate(projectId: number) {
    this.projectService.findProjectById(projectId).subscribe(
      (projectDto: ProjectDto) => {
        this.projectService.projectEmitter.emit(projectDto);
      }
    );
  }

  onDelete(id: number) {
    this.projectService.deleteProject(id).subscribe();
  }
}
