import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {Ng5SliderModule} from 'ng5-slider';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {HeaderComponent} from './componets/header/header.component';
import {AdduserComponent} from './componets/adduser/adduser.component';
import {ListuserComponent} from './componets/listuser/listuser.component';
import {AddprojectComponent} from './componets/addproject/addproject.component';
import {AddtaskComponent} from './componets/addtask/addtask.component';
import {AppRoutingModule} from './app-routing.module';
import {ListprojectComponent} from './componets/listproject/listproject.component';
import {ViewtaskComponent} from './componets/viewtask/viewtask.component';
import {DatePipe} from "@angular/common";
import {ModalModule} from 'ngx-bootstrap/modal';
import {RefreshComponent} from './componets/refresh/refresh.component';
import {EdittaskComponent} from './componets/edittask/edittask.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    AdduserComponent,
    ListuserComponent,
    AddprojectComponent,
    AddtaskComponent,
    ListprojectComponent,
    ViewtaskComponent,
    RefreshComponent,
    EdittaskComponent
  ],
  imports: [
    BrowserModule, FormsModule, AppRoutingModule, Ng5SliderModule, HttpClientModule, ModalModule.forRoot()],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule {
}
