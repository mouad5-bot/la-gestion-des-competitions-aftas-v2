import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule} from '@angular/common/http';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { MemberComponent } from './components/member/member-list/member.component';
import { CompetitionComponent } from './components/competition/competition-list/competition.component';
import { FishComponent } from './components/fish/fish-list/fish.component';
import { HuntingComponent } from './components/hunting/hunting-list/hunting.component';
import { CompetitionAddComponent } from './components/competition/competition-add/competition-add.component';
import { FishAddComponent } from './components/fish/fish-add/fish-add.component';
import { HuntingAddComponent } from './components/hunting/hunting-add/hunting-add.component';
import { MemberAddComponent } from './components/member/member-add/member-add.component';
import {CommonModule} from "@angular/common";
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatPaginatorModule} from '@angular/material/paginator';
import {MatSelectModule} from "@angular/material/select";
import { RankingListComponent } from './components/ranking/ranking-list/ranking-list.component';
import { RankingAddComponent } from './components/ranking/ranking-add/ranking-add.component';

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    MemberComponent,
    CompetitionComponent,
    FishComponent,
    HuntingComponent,
    CompetitionAddComponent,
    FishAddComponent,
    HuntingAddComponent,
    MemberAddComponent,
    RankingListComponent,
    RankingAddComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    CommonModule,
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatPaginatorModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
