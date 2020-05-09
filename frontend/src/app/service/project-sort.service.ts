import {Injectable} from '@angular/core';
import {ProjectDto} from "../dto/project.dto";

@Injectable({
  providedIn: 'root'
})
export class ProjectSortService {

  constructor() {
  }

  projectDtos: ProjectDto[] = [];

  setProjectDtos(projectDtos: ProjectDto[]) {
    this.projectDtos = projectDtos;
  }

  sortByProjectField(field): ProjectDto[] {
    if ("startDate" === field) {
      return this.projectDtos.sort(function (a, b): any {
        const d1: Date = new Date(a.startDate);
        const d2: Date = new Date(b.startDate);
        return (d1.getTime() - d2.getTime());
      });
    } else if ("endDate" === field) {
      return this.projectDtos.sort(function (a, b): any {
        const d1: Date = new Date(a.startDate);
        const d2: Date = new Date(b.startDate);
        return (d2.getTime() - d1.getTime());
      });
    } else if ("priority" === field) {
      return this.projectDtos.sort(function (a, b): any {
        const d1: number = a.priority
        const d2: number = b.priority
        return (d1 - d2)
      })
    }else if ("completed" === field) {
      return this.projectDtos.sort(function (a, b): any {
        const d1: number = a.totalNoOfCompletedTasks
        const d2: number = b.totalNoOfCompletedTasks
        return (d1 - d2)
      })
    }
  }
}
