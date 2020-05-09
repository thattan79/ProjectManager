import {Component, Input, OnChanges, SimpleChanges, ViewChild} from '@angular/core';
import {UserService} from "../../service/user.service";
import {UserDto} from "../../dto/user.dto";
import {NgForm} from "@angular/forms";
import {UserSortService} from "../../service/user-sort.service";

@Component({
  selector: 'app-listuser',
  templateUrl: './listuser.component.html',
  styleUrls: ['./listuser.component.css']
})
export class ListuserComponent implements OnChanges {

  @ViewChild('userForm') userForm: NgForm;
  @Input('userDto') userDto: UserDto
  userDtos: UserDto[] = [];
  search: boolean = false;

  constructor(private userService: UserService,
              private sortService: UserSortService) {
  }

  _searchVal: string;
  get searchVal(): string {
    return this._searchVal;
  }

  @Input('searchVal')
  set searchVal(input: string) {
    if (input === '') {
      input = 'default';
    }
    this.search = true;
    this.userService.findAllUserByInput(input).subscribe(
      (userDtos: UserDto[]) => {
        this.userDtos = userDtos;
      }
    )
    if (input === 'default') {
      input = '';
    }
    this._searchVal = input;
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.userService.findAllUser().subscribe(
      (userDtos: UserDto[]) => {
        this.userDtos = userDtos;
      }
    );
  }

  onEdit(id: number) {
    this.userService.findUserById(id).subscribe(
      (userDto: UserDto) => {
        this.userService.usersEmitter.emit(userDto)
      }
    )
  }

  onDelete(id: number) {
    this.userService.deleteUser(id).subscribe(
      (userDto: UserDto) => {
        this.userService.usersEmitter.emit(userDto)
        this.userForm.reset();
      }
    );
  }

  onSort(field) {
    this.sortService.setUserDtos(this.userDtos);
    this.userDtos = this.sortService.sortByUserField(field);
  }
}
