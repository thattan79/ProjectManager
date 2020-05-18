import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserDto} from '../dto/user.dto';
import {Observable} from "rxjs";
import {environment} from "../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  projectHttpUrl: string = environment.apiUrl + '/user/';
  public usersEmitter: EventEmitter<UserDto> = new EventEmitter<UserDto>()

  constructor(private http: HttpClient) {
  }

  createUser(userDto: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>(this.projectHttpUrl + 'create', userDto);
  }

  deleteUser(id: number): Observable<UserDto> {
    return this.http.post<UserDto>(this.projectHttpUrl + 'delete', id);
  }

  findAllUser(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>(this.projectHttpUrl + 'findAll');
  }

  findAllUserByInput(input: string): Observable<UserDto[]> {
    return this.http.get<UserDto[]>(this.projectHttpUrl + 'findAllUserByInput/' + input);
  }

  findUserById(id: number): Observable<UserDto> {
    return this.http.get<UserDto>(this.projectHttpUrl + 'findUserById/' + id);
  }
}
