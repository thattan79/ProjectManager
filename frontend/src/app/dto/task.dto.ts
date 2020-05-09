import {ParentTaskDto} from "./parent-task.dto";
import {UserDto} from "./user.dto";
import {ProjectDto} from "./project.dto";

export class TaskDto {
  public taskId: string;
  public taskName: number;
  public startDate: string;
  public endDate: string;
  public priority: number;
  public status: string;
  public isParentTask: boolean
  public userId: number;
  public projectId: number;
  public parentTaskId: number;
  public userDto: UserDto = new UserDto();
  public parentTaskDto: ParentTaskDto = new ParentTaskDto();
  public projectDto: ProjectDto = new ProjectDto();
}
