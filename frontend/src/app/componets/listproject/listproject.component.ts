import {Component, EventEmitter, Input, Output, OnChanges, SimpleChanges} from '@angular/core';
import {ProjectDto} from "../../dto/project.dto";
import {ProjectService} from "../../service/project.service";
import {ProjectSortService} from "../../service/project-sort.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-listproject',
  templateUrl: './listproject.component.html',
  styleUrls: ['./listproject.component.css']
})
export class ListprojectComponent implements OnChanges {

  @Input('projectDto') projectDto: ProjectDto
  projectDtos: ProjectDto[] = [];
  search: boolean = false;
  @Output()
  public projectEmitter: EventEmitter<ProjectDto> = new EventEmitter<ProjectDto>()
  @Output()
  public projectActionEmitter: EventEmitter<string> = new EventEmitter<string>()

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

  placeholder: string;

  constructor(private projectService: ProjectService,
              private projectSortService: ProjectSortService,
              private translate: TranslateService) {
    translate.get('placeholder.search').subscribe(
      (placeholder: string) => this.placeholder = placeholder,
    );
  }


  ngOnChanges(changes: SimpleChanges): void {
    this.projectService.findAllProjects().subscribe(
      (projectDtos: ProjectDto[]) => {
        this.projectDtos = projectDtos;
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
        this.projectActionEmitter.emit('update');
        this.projectEmitter.emit(projectDto);
      }
    );
  }

  onDelete(id: number) {
    this.projectService.deleteProject(id).subscribe(
      (projectDto: ProjectDto) => {
        this.projectActionEmitter.emit('delete');
        this.projectService.findAllProjects().subscribe(
          (projectDtos: ProjectDto[]) => {
            this.projectDtos = projectDtos;
          }
        )
      }
    );
  }
}
