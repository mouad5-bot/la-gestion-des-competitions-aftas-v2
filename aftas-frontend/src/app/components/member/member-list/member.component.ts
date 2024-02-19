import { Component, OnInit } from '@angular/core';
import { MemberService } from '../../../services/member/member.service';
import {IMember} from "../../../models/member";
import {PageEvent} from "@angular/material/paginator";


@Component({
  selector: 'app-member',
  templateUrl: './member.component.html',
  styleUrls: ['./member.component.css']

})
export class MemberComponent implements OnInit {
  public members: IMember[] = [];
  public filterdMembers: IMember[] = [];
  private _listFilter: string = '';

  currentPage: number = 0;
  pageSize:number = 2;

  constructor(private memberService: MemberService) {}

  get listFilter(): string{
    return this._listFilter;
  }

  set listFilter(value: string){
    this._listFilter = value;
    console.log("In setter :", value);
      this.filterdMembers = this.performFilter(value);
  }

  ngOnInit() {
    this.listFilter = ''
    this.getMembers();
  }

  public getMembers(): void {
    this.memberService.getPaginatedMembers(this.currentPage, this.pageSize).subscribe(
      ( response: IMember[] ) => {
        this.members = response;
        this.filterdMembers = response;
      },
      (error) => {
        console.error('Error fetching members:', error);
      }
    );
  }
  onPageChange(event: PageEvent): void {
    this.currentPage = event.pageIndex !== undefined ? event.pageIndex : 0;
    this.pageSize = event.pageSize !== undefined ? event.pageSize : 2;
    this.getMembers();
  }

  private performFilter(filterBy: string): IMember[] {
    filterBy = filterBy.toLocaleLowerCase();
    return this.members.filter((member: IMember) =>
      member.name?.toLocaleLowerCase().includes(filterBy) ||
      member.familyName?.toLocaleLowerCase().includes(filterBy)
    );
  }

}
