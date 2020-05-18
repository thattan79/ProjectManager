import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {TaskDto} from "../dto/task.dto";
import {ParentTaskDto} from "../dto/parent-task.dto";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  projectHttpUrl: string = environment.apiUrl + '/task/';
  taskDto: TaskDto;

  constructor(private http: HttpClient) {
  }

  createTask(taskDto: TaskDto): Observable<TaskDto> {
    return this.http.post<TaskDto>(this.projectHttpUrl + 'create', taskDto);
  }

  updateTask(taskDto: TaskDto): Observable<TaskDto> {
    return this.http.post<TaskDto>(this.projectHttpUrl + 'update', taskDto);
  }

  updateTaskStatus(taskDto: TaskDto): Observable<TaskDto> {
    return this.http.post<TaskDto>(this.projectHttpUrl + 'updateTaskStatus', taskDto);
  }

  createParentTask(parentTaskDto: ParentTaskDto): Observable<ParentTaskDto> {
    return this.http.post<ParentTaskDto>(this.projectHttpUrl + 'createParent', parentTaskDto);
  }

  findAllParent(): Observable<ParentTaskDto[]> {
    return this.http.get<ParentTaskDto[]>(this.projectHttpUrl + 'findAllParent');
  }

  findAllParentTasksByInput(input: string): Observable<ParentTaskDto[]> {
    return this.http.get<ParentTaskDto[]>(this.projectHttpUrl + 'findAllParentTasksByInput/' + input);
  }

  setTaskDto(taskDto: TaskDto) {
    this.taskDto = taskDto;
  }

  getTaskDto(): TaskDto {
    return this.taskDto;
  }
}
