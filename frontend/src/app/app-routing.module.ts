import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AdduserComponent} from './componets/adduser/adduser.component';
import {AddprojectComponent} from './componets/addproject/addproject.component';
import {AddtaskComponent} from './componets/addtask/addtask.component';
import {ViewtaskComponent} from './componets/viewtask/viewtask.component';
import {RefreshComponent} from "./componets/refresh/refresh.component";
import {EdittaskComponent} from "./componets/edittask/edittask.component";

const appRoutes: Routes = [
  {
    path: 'adduser',
    component: AdduserComponent
  },
  {
    path: 'addproject', component: AddprojectComponent
  },
  {path: 'addtask', component: AddtaskComponent},
  {path: 'viewtask', component: ViewtaskComponent},
  {path: 'refresh', component: RefreshComponent },
  {path: 'edittask', component: EdittaskComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
