import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from "rxjs";
import {TaskDto} from "../dto/task.dto";
import {ParentTaskDto} from "../dto/parent-task.dto";

@Injectable({
  providedIn: 'root'
})
export class TaskService {

  public taskEmitter: EventEmitter<string> = new EventEmitter<string>();

  taskDto: TaskDto;

  constructor(private http: HttpClient) {
  }

  createTask(taskDto: TaskDto): Observable<TaskDto> {
    return this.http.post<TaskDto>('http://localhost:8081/task/create', taskDto);
  }

  updateTask(taskDto: TaskDto): Observable<TaskDto> {
    return this.http.post<TaskDto>('http://localhost:8081/task/update', taskDto);
  }

  updateTaskStatus(taskDto: TaskDto): Observable<TaskDto> {
    return this.http.post<TaskDto>('http://localhost:8081/task/updateTaskStatus', taskDto);
  }

  createParentTask(parentTaskDto: ParentTaskDto): Observable<ParentTaskDto> {
    return this.http.post<ParentTaskDto>('http://localhost:8081/task/createParent', parentTaskDto);
  }

  findAllParent(): Observable<ParentTaskDto[]> {
    return this.http.get<ParentTaskDto[]>('http://localhost:8081/task/findAllParent');
  }

  findAllParentTasksByInput(input: string): Observable<ParentTaskDto[]> {
    return this.http.get<ParentTaskDto[]>('http://localhost:8081/task/findAllParentTasksByInput/' + input);
  }

  setTaskDto(taskDto: TaskDto) {
    this.taskDto = taskDto;
  }

  getTaskDto(): TaskDto {
    return this.taskDto;
  }
}
