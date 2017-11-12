import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { TopnavbarComponent } from "./components/menu/topnavbar/topnavbar.component";
import { SidebarComponent } from "./components/menu/sidebar/sidebar.component";
import { FooterComponent } from "./components/footer/footer.component";
import { NotFoundPageComponent } from "./pages/not-found-page/not-found-page.component";
import { AccessDeniedPageComponent } from "./pages/access-denied-page/access-denied-page.component";
import { InternalServerErrorPageComponent } from "./pages/internal-server-error-page/internal-server-error-page.component";
import { HomePageComponent } from "./pages/home-page/home-page.component";
import { BrowserModule } from "@angular/platform-browser";
import { LoginPageComponent } from "./pages/login-page/login-page.component";
import { Routing } from "./app.routing";
import { LoginFormComponent } from './components/login/login-form/login-form.component';
import { HttpClientModule } from "@angular/common/http";
import { AuthenticationService } from "./services/authentication.service";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    TopnavbarComponent,
    SidebarComponent,
    FooterComponent,
    LoginPageComponent,
    HomePageComponent,
    NotFoundPageComponent,
    AccessDeniedPageComponent,
    InternalServerErrorPageComponent,
    HomePageComponent,
    LoginFormComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Routing
  ],
  providers: [AuthenticationService],
  bootstrap: [AppComponent]
})
export class AppModule {
}