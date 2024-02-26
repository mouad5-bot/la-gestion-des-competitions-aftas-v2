import { NgModule } from '@angular/core';
import {mapToCanActivate, RouterModule, Routes} from '@angular/router';
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
import {AuthGuard} from "./core/auth.guard";
import {MemberGuard} from "./core/member-guard.service";
import {ManagerGuard} from "./core/manager.guard";

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: "",
    component: DashboardComponent,
    canActivate: [AuthGuard],
    children: [
      { path: '', component: CompetitionComponent},

      { path: 'member/list',canActivate:[ManagerGuard], component: MemberComponent },
      { path: 'member/add',canActivate:[ManagerGuard], component: MemberAddComponent},

      { path: 'competition/list',canActivate:[MemberGuard], component: CompetitionComponent},
      { path: "competition/add",canActivate:[ManagerGuard], component: CompetitionAddComponent},

      { path: 'ranking/list',canActivate:[ManagerGuard], component: RankingListComponent},
      { path: "ranking/add", canActivate:[ManagerGuard],component: RankingAddComponent},

      { path: 'hunting/list', canActivate:[ManagerGuard],component: HuntingComponent },
      { path: 'hunting/add',canActivate:[ManagerGuard], component: HuntingAddComponent },

      { path: 'fish/list', canActivate:[ManagerGuard],component: FishComponent },
      { path: 'fish/add', canActivate:[ManagerGuard],component: FishAddComponent },

      { path: '**', component: CompetitionComponent },
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
