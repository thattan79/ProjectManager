import {Injectable} from '@angular/core';
import {UserDto} from "../dto/user.dto";

@Injectable({
  providedIn: 'root'
})
export class UserSortService {

  constructor() {
  }

  userDtos: UserDto[] = [];

  setUserDtos(userDtos: UserDto[]) {
    this.userDtos = userDtos;
  }

  sortByUserField(field): UserDto[] {
    if ("firstName" === field) {
      return this.userDtos.sort(
        (n1: UserDto, n2: UserDto) => {
          if (n1.firstName > n2.firstName) {
            return 1;
          }
          if (n1.firstName < n2.firstName) {
            return -1;
          }
          return 0;
        });
    } else if ("lastName" === field) {
      return this.userDtos.sort(
        (n1: UserDto, n2: UserDto) => {
          if (n1.lastName > n2.lastName) {
            return 1;
          }
          if (n1.lastName < n2.lastName) {
            return -1;
          }
          return 0;
        });
    } else {
      return this.userDtos.sort(
        (n1: UserDto, n2: UserDto) => {
          if (n1.employeeId > n2.employeeId) {
            return 1;
          }
          if (n1.employeeId < n2.employeeId) {
            return -1;
          }
          return 0;
        });
    }
  }
}
