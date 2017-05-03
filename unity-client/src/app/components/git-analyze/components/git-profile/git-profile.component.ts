import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { GitProfileService } from "../../../../services/GitProfileService";

@Component({
  selector: 'app-git-profile',
  templateUrl: './git-profile.component.html',
  styleUrls: ['./git-profile.component.css']
})
export class GitProfileComponent implements OnInit {
  error = '';
  gitProfile: Object;
  loading = false;

  constructor(private gitProfileService: GitProfileService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.loading = true;

    //FIXME take from store
    this.route.parent.params.subscribe(params => {
      if (params['login']) {
        this.gitProfileService.getGitProfileData(params['login'], params['id'])
          .subscribe(
            result => {
              this.gitProfile = result.result;
              this.loading = false;
            },
            error => {
              this.error = error;
              this.loading = false;
            }
          )
      }
    });
    // this.profile = this.gitProfileService.profile;
  }
}
