import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProjectDto} from '../dto/project.dto';
import {Observable} from "rxjs";
import {UserDto} from "../dto/user.dto";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  constructor(private http: HttpClient) {
  }

  createProject(projectDto: ProjectDto): Observable<ProjectDto> {
    return this.http.post<ProjectDto>('http://localhost:8081/project/create', projectDto);
  }

  findAllProjects(): Observable<ProjectDto[]> {
    return this.http.get<ProjectDto[]>('http://localhost:8081/project/findAllProjects');
  }

  findAllProjectByInput(input: string): Observable<ProjectDto[]> {
    return this.http.get<ProjectDto[]>('http://localhost:8081/project/findAllProjectByInput/' + input);
  }

  findProjectById(id: number): Observable<ProjectDto> {
    return this.http.get<ProjectDto>("http://localhost:8081/project/findProjectById/" + id);
  }

  deleteProject(id: number): Observable<ProjectDto> {
    return this.http.post<ProjectDto>('http://localhost:8081/project/delete', id);
  }
}
