import { Component, OnInit } from "@angular/core";
import { AuthenticationService } from "../services/AuthenticationService";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) {
  }

  ngOnInit() {
  }

  isUserLoggedIn(): boolean {
    return this.authenticationService.isLoggedIn();
  }
}
