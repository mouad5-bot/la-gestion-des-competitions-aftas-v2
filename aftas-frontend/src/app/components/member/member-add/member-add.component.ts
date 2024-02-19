import { Component } from '@angular/core';
import {MemberClass} from "../../../models/member";
import {MemberService} from "../../../services/member/member.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-member-add',
  templateUrl: './member-add.component.html',
  styleUrls: ['./member-add.component.css']
})
export class MemberAddComponent {
  documentTypes = ['IDENTITY_CARD', 'PASSPORT', 'DRIVING_LICENSE'];
  selectedDocumentType: string | undefined;

  public addMember = new MemberClass();

  constructor(private memberService: MemberService,
              private router: Router) {}
  onSubmit() {

    this.addMember.identityDocumentType = this.selectedDocumentType;

      this.memberService.addMember(this.addMember).subscribe(
          (member) => {
          console.log("Member added by success !", member);
          this.router.navigate(['/member/list']);
        },
        (error) => {
          alert("error occured")
        }
      )

  }

}
