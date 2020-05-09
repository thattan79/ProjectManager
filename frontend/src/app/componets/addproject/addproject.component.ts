import {Component, Input, OnInit, TemplateRef, ViewChild} from '@angular/core';
import {Options} from 'ng5-slider';
import {ActivatedRoute, Router} from '@angular/router';
import {NgForm, ValidationErrors} from '@angular/forms';
import {DatePipe} from "@angular/common";
import {ProjectDto} from "../../dto/project.dto";
import {ProjectService} from '../../service/project.service';
import {BsModalRef, BsModalService} from 'ngx-bootstrap';
import {UserDto} from "../../dto/user.dto";
import {UserService} from "../../service/user.service";

@Component({
  selector: 'app-addproject',
  templateUrl: './addproject.component.html',
  styleUrls: ['./addproject.component.css']
})
export class AddprojectComponent implements OnInit {

  @Input() errors: ValidationErrors;
  @ViewChild('projectForm') projectForm: NgForm;
  projectDto: ProjectDto;
  dateFieldActive: boolean = true;
  bsModalRef: BsModalRef;
  userDtos: UserDto[] = [];
  userDto: UserDto;
  search: boolean = false;
  edit: boolean = false;
  setStartAndEndDate: boolean = false;

  value: number = 100;
  options: Options = {
    floor: 0,
    ceil: 30
  };

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

  constructor(private router: Router,
              private route: ActivatedRoute,
              private  datePipe: DatePipe,
              private projectService: ProjectService,
              private bsModalService: BsModalService,
              private userService: UserService) {
    this.projectDto = new ProjectDto();
    this.userDto = new UserDto();
  }

  ngOnInit() {
    this.userService.findAllUser().subscribe(
      (userDtos: UserDto[]) => {
        this.userDtos = userDtos;
      }
    );

    this.projectService.projectEmitter.subscribe(
      (projectDto: ProjectDto) => {
        this.projectDto = projectDto;
        this.edit = true;
        this.dateFieldActive = false;
        this.projectForm.control.get('projectTitle').setValue(projectDto.projectTitle);
        this.projectForm.control.get('startDate').setValue(projectDto.startDate);
        this.projectForm.control.get('endDate').setValue(projectDto.endDate);
        this.projectForm.control.get('priority').setValue(projectDto.priority);
        this.projectForm.control.get('projectId').setValue(projectDto.projectId);
      }
    )
  }

  setDateField() {
    if (this.projectForm.control.get('startEndDate').value) {
      this.dateFieldActive = false;
      const startDate = this.datePipe.transform(new Date(), 'yyyy-MM-dd');
      const date = new Date();
      date.setDate(date.getDate() + 1);
      const endDate = this.datePipe.transform(date, 'yyyy-MM-dd');
      this.projectForm.control.get('startDate').setValue(startDate);
      this.projectForm.control.get('endDate').setValue(endDate);
    }else{
      this.dateFieldActive = true;
      this.projectForm.control.get('startDate').setValue('');
      this.projectForm.control.get('endDate').setValue('');

    }
  }

  onSubmit() {
    const startDateRef = this.projectForm.control.get('startDate');
    const endDateRef = this.projectForm.control.get('endDate');
    const priorityRef = this.projectForm.control.get('priority');
    const managerRef = this.projectForm.control.get('manager');

    const d1 = new Date(startDateRef.value);
    const d2 = new Date(endDateRef.value);
    const priority = priorityRef.value;

    if (d1 > d2) {
      startDateRef.setErrors({
        'invalidDate': true
      });
      endDateRef.setErrors({
        'invalidDate': true
      });
      return;
    }

    if (priority <= 0) {
      priorityRef.setErrors({
        'invalidPriority': true
      });
      return;
    }
    if (managerRef.value === '') {
      managerRef.setErrors({
        'inValidManager': true
      });
      return;
    }
    this.projectDto = new ProjectDto();
    this.projectDto.projectTitle = this.projectForm.control.get('projectTitle').value;
    this.projectDto.startDate = startDateRef.value;
    this.projectDto.endDate = endDateRef.value;
    this.projectDto.priority = priorityRef.value;
    this.projectDto.userId = this.userDto.userId;
    this.projectDto.projectId = this.projectForm.control.get('projectId').value;
    this.projectService.createProject(this.projectDto).subscribe(
      (projectDto: ProjectDto) => {
        this.projectDto = projectDto;
        this.projectForm.reset();
      }
    );
  }

  removeBorderColor() {
    const startDate = this.projectForm.control.get('startDate');
    const endDate = this.projectForm.control.get('endDate');
    endDate.setErrors(null);
    startDate.setErrors(null);
  }

  openModal(template: TemplateRef<any>) {
    this.bsModalRef = this.bsModalService.show(template);
  }

  selectUser(userDto: UserDto) {
    this.userDto = userDto;
    this.projectForm.control.get('manager').setValue(userDto.firstName);
  }

}
