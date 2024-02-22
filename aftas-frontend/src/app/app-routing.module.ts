import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {DashboardComponent} from "./pages/dashboard/dashboard.component";
import {MemberComponent} from "./components/member/member-list/member.component";
import {CompetitionAddComponent} from "./components/competition/competition-add/competition-add.component";
import {CompetitionComponent} from "./components/competition/competition-list/competition.component";
import {HuntingComponent} from "./components/hunting/hunting-list/hunting.component";
import {FishComponent} from "./components/fish/fish-list/fish.component";
import {MemberAddComponent} from "./components/member/member-add/member-add.component";
import {HuntingAddComponent} from "./components/hunting/hunting-add/hunting-add.component";
import {FishAddComponent} from "./components/fish/fish-add/fish-add.component";
import {RankingListComponent} from "./components/ranking/ranking-list/ranking-list.component";
import {RankingAddComponent} from "./components/ranking/ranking-add/ranking-add.component";
import {LoginComponent} from "./pages/auth/login/login.component";
import {RegisterComponent} from "./pages/auth/register/register.component";

const routes: Routes = [
  {
    path: "",
    component: DashboardComponent,
    children: [
      { path: 'member/list', component: MemberComponent },
      { path: 'member/add', component: MemberAddComponent},

      { path: 'competition/list', component: CompetitionComponent},
      { path: "competition/add", component: CompetitionAddComponent},

      { path: 'ranking/list', component: RankingListComponent},
      { path: "ranking/add", component: RankingAddComponent},

      { path: 'hunting/list', component: HuntingComponent },
      { path: 'hunting/add', component: HuntingAddComponent },

      { path: 'fish/list', component: FishComponent },
      { path: 'fish/add', component: FishAddComponent },
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
