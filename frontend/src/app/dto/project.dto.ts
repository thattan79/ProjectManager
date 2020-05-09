import {UserDto} from "./user.dto";
import {TaskDto} from "./task.dto";

export class ProjectDto {
  public projectId: number;
  public projectTitle: string;
  public startDate: string;
  public endDate: string;
  public priority: number;
  public userId: number;
  public userDtos: UserDto[];
  public taskDtos: TaskDto[];
  public totalNoOfTasks: number;
  public totalNoOfCompletedTasks: number;
}
