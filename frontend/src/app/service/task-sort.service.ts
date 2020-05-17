import {Injectable} from '@angular/core';
import {TaskDto} from "../dto/task.dto";
import {ProjectDto} from "../dto/project.dto";

@Injectable({
  providedIn: 'root'
})
export class TaskSortService {

  constructor() {
  }

  taskDtos: TaskDto[] = [];

  setTaskDtos(taskDtos: TaskDto[]) {
    this.taskDtos = taskDtos;
  }

  sortByTaskField(field): ProjectDto[] {
    if ("startDate" === field) {
      return this.taskDtos.sort(function (a, b): any {
        const d1: Date = new Date(a.startDate);
        const d2: Date = new Date(b.startDate);
        return (d1.getTime() - d2.getTime());
      });
    } else if ("endDate" === field) {
      return this.taskDtos.sort(function (a, b): any {
        const d1: Date = new Date(a.endDate);
        const d2: Date = new Date(b.endDate);
        return (d1.getTime() - d2.getTime());
      });
    } else if ("priority" === field) {
      return this.taskDtos.sort(function (a, b): any {
        const d1: number = a.priority
        const d2: number = b.priority
        return (d1 - d2)
      })
    } else if ("completed" === field) {
      return this.taskDtos.sort(function (a, b): any {
        const d1: string = a.status
        const d2: string = b.status;
        return (d1.localeCompare(d2));
      })
    }
  }
}
