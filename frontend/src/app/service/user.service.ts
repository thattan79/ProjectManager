import {EventEmitter, Injectable, Output} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserDto} from '../dto/user.dto';
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  public usersEmitter: EventEmitter<UserDto> = new EventEmitter<UserDto>()

  constructor(private http: HttpClient) {
  }

  createUser(userDto: UserDto): Observable<UserDto> {
    return this.http.post<UserDto>('http://localhost:8081/user/create', userDto);
  }

  deleteUser(id: number): Observable<UserDto> {
    return this.http.post<UserDto>('http://localhost:8081/user/delete/', +id);
  }

  findAllUser(): Observable<UserDto[]> {
    return this.http.get<UserDto[]>('http://localhost:8081/user/findAll');
  }

  findAllUserByInput(input: string): Observable<UserDto[]> {
    return this.http.get<UserDto[]>('http://localhost:8081/user/findAllUserByInput/' + input);
  }

  findUserById(id: number): Observable<UserDto> {
    return this.http.get<UserDto>("http://localhost:8081/user/findUserById/" + id);
  }
}
