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
        (user1: UserDto, user2: UserDto) => {
          if (user1.firstName > user2.firstName) {
            return 1;
          }
          if (user1.firstName < user2.firstName) {
            return -1;
          }
          return 0;
        });
    } else if ("lastName" === field) {
      return this.userDtos.sort(
        (user1: UserDto, user2: UserDto) => {
          if (user1.lastName > user2.lastName) {
            return 1;
          }
          if (user1.lastName < user2.lastName) {
            return -1;
          }
          return 0;
        });
    } else {
      return this.userDtos.sort(
        (user1: UserDto, user2: UserDto) => {
          let user1EmployeeID = +user1.employeeId;
          let user2EmployeeID = +user2.employeeId;
          if (user1EmployeeID > user2EmployeeID) {
            return 1;
          }
          if (user1EmployeeID < user2EmployeeID) {
            return -1;
          }
          return 0;
        });
    }
  }
}
