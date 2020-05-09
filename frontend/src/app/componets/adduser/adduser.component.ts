import {Component, OnInit, ViewChild} from '@angular/core';
import {NgForm} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {UserService} from '../../service/user.service';
import {UserDto} from '../../dto/user.dto';

@Component({
  selector: 'app-adduser',
  templateUrl: './adduser.component.html',
  styleUrls: ['./adduser.component.css']
})
export class AdduserComponent implements OnInit {

  @ViewChild('userForm') userForm: NgForm;
  userDto: UserDto;
  edit: boolean = false;

  constructor(private router: Router,
              private route: ActivatedRoute,
              private userService: UserService) {
    this.userDto = new UserDto();
  }

  ngOnInit() {
    this.userService.usersEmitter.subscribe(
      (userDto: UserDto) => {
        this.userDto = userDto;
      }
    )
  }

  onSubmit() {
    this.userService.createUser(this.userDto).subscribe(
      (userDto: UserDto) => {
        this.userDto = userDto;
        this.userForm.reset();
        this.edit = false;
      }
    );
  }

  onReset() {
    this.userForm.reset();
  }
}
