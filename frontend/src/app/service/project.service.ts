import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ProjectDto} from '../dto/project.dto';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class ProjectService {

  projectHttpUrl: string = environment.apiUrl + '/project/';

  constructor(private http: HttpClient) {
  }

  createProject(projectDto: ProjectDto): Observable<ProjectDto> {
    return this.http.post<ProjectDto>(this.projectHttpUrl + 'create', projectDto);
  }

  findAllProjects(): Observable<ProjectDto[]> {
    return this.http.get<ProjectDto[]>(this.projectHttpUrl + 'findAllProjects');
  }

  findAllProjectByInput(input: string): Observable<ProjectDto[]> {
    return this.http.get<ProjectDto[]>(this.projectHttpUrl + 'findAllProjectByInput/' + input);
  }

  findProjectById(id: number): Observable<ProjectDto> {
    return this.http.get<ProjectDto>(this.projectHttpUrl + 'findProjectById/' + id);
  }

  deleteProject(id: number): Observable<ProjectDto> {
    return this.http.post<ProjectDto>(this.projectHttpUrl + 'delete', id);
  }
}
