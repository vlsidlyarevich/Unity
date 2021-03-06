import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import { TopnavbarComponent } from './components/menu/topnavbar/topnavbar.component';
import { SidebarComponent } from './components/menu/sidebar/sidebar.component';
import { FooterComponent } from './components/footer/footer.component';
import { NotFoundPageComponent } from './pages/not-found-page/not-found-page.component';
import { AccessDeniedPageComponent } from './pages/access-denied-page/access-denied-page.component';
import { InternalServerErrorPageComponent } from './pages/internal-server-error-page/internal-server-error-page.component';
import { BrowserModule } from '@angular/platform-browser';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { Routing } from './app.routing';
import { LoginFormComponent } from './components/login/login-form/login-form.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { AuthenticationService } from './services/authentication.service';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SocialLoginPageComponent } from './pages/social-login-page/social-login-page.component';
import { Ng2Webstorage } from 'ngx-webstorage';
import { CookieService } from 'ngx-cookie-service';
import { ProfilePageComponent } from './pages/profile-page/profile-page.component';
import { ProfileComponent } from './components/profile/profile.component';
import { ProfileService } from './services/profile.service';
import { ImageUploadModule } from 'angular2-image-upload';
import { NotificationService } from './services/notification.service';
import { NotificationComponent } from './components/notification/notification.component';
import { LoadingComponent } from './components/loading/loading.component';
import { LoaderService } from './services/loader.service';
import { PipeModule } from './pipes/pipe-module/pipe.module';
import { ImageService } from './services/image.service';
import { ProfileStoreService } from './services/store/profile-store.service';
import { AuthenticationTokenInterceptor } from './interceptors/authentication-token.interceptor';
import { TokenService } from "./services/token.service";
import { UsersPageComponent } from './pages/users-page/users-page.component';
import { UsersComponent } from './components/admin/users/users.component';
import { UserService } from "./services/user.service";
import { NgxPaginationModule } from "ngx-pagination";
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { OrderModule } from 'ngx-order-pipe';
import { UserDeleteDialogComponent } from './dialogs/user-delete-dialog/user-delete-dialog.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { UserShowDialogComponent } from "./dialogs/user-show-dialog/user-show-dialog.component";
import { UserEditPageComponent } from './pages/user-edit-page/user-edit-page.component';
import { UserEditComponent } from './components/admin/user-edit/user-edit.component';
import { UserAddPageComponent } from './pages/user-add-page/user-add-page.component';
import { UserAddComponent } from "./components/admin/user-add/user-add.component";
import { AnalyticsResultsPageComponent } from './pages/analytics-results-page/analytics-results-page.component';
import { AnalyticsReportsComponent } from './components/analytics/analytics-reports/analytics-reports.component';
import { AnalyticsReportsService } from "./services/analytics-reports.service";
import { ReportDeleteDialogComponent } from './dialogs/report-delete-dialog/report-delete-dialog.component';
import { AnalyticsNewModalComponent } from './modals/analysis-new-modal/analytics-new-modal.component';
import { AnalyticsNewLinkedinPageComponent } from './pages/analytics-new-linkedin-page/analytics-new-linkedin-page.component';
import { AnalyticsNewTwitterPageComponent } from './pages/analytics-new-twitter-page/analytics-new-twitter-page.component';
import { AnalyticsNewGithubPageComponent } from './pages/analytics-new-github-page/analytics-new-github-page.component';
import { AnalyticsNewGithubComponent } from './components/analytics/analytics-new-github/analytics-new-github.component';
import { AnalyticsNewLinkedinComponent } from './components/analytics/analytics-new-linkedin/analytics-new-linkedin.component';
import { AnalyticsNewTwitterComponent } from './components/analytics/analytics-new-twitter/analytics-new-twitter.component';
import { GitAnalyticsService } from "./services/git-analytics.service";
import { TwitterAnalysisService } from "./services/twitter-analytics.service";
import { LinkedinAnalysisService } from "./services/linkedin-analytics.service";
import { AnalyticsReportGithubComponent } from './components/analytics/analytics-report-github/analytics-report-github.component';
import { AnalyticsReportLinkedinComponent } from './components/analytics/analytics-report-linkedin/analytics-report-linkedin.component';
import { AnalyticsReportTwitterComponent } from './components/analytics/analytics-report-twitter/analytics-report-twitter.component';
import { AnalyticsReportPageComponent } from './pages/analytics-report-page/analytics-report-page.component';
import { GithubRepositoriesComponent } from "./components/analytics/analytics-report-github/components/github-repositories/github-repositories.component";
import { GithubProfileComponent } from './components/analytics/analytics-report-github/components/github-profile/github-profile.component';
import { ProgressbarModule } from 'ngx-bootstrap';
import { GithubTechnologiesComponent } from './components/analytics/analytics-report-github/components/github-technologies/github-technologies.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';
import { GithubForkedTechnologiesComponent } from './components/analytics/analytics-report-github/components/github-forked-technologies/github-forked-technologies.component';
import { TwitterProfileComponent } from './components/analytics/analytics-report-twitter/components/twitter-profile/twitter-profile.component';
import { TwitterSubscriptionsComponent } from './components/analytics/analytics-report-twitter/components/twitter-subscriptions/twitter-subscriptions.component';
import { TwitterTechnologiesComponent } from './components/analytics/analytics-report-twitter/components/twitter-technologies/twitter-technologies.component';
import { AuthGuard } from "./guards/auth.guard";
import { AuthAdminGuard } from "./guards/auth.admin.guard";


@NgModule({
  declarations: [
    AppComponent,
    TopnavbarComponent,
    SidebarComponent,
    FooterComponent,
    LoginPageComponent,
    NotFoundPageComponent,
    AccessDeniedPageComponent,
    InternalServerErrorPageComponent,
    LoginFormComponent,
    SocialLoginPageComponent,
    ProfilePageComponent,
    ProfileComponent,
    NotificationComponent,
    LoadingComponent,
    UsersPageComponent,
    UsersComponent,
    UserDeleteDialogComponent,
    UserShowDialogComponent,
    UserEditPageComponent,
    UserEditComponent,
    UserAddComponent,
    UserAddPageComponent,
    AnalyticsResultsPageComponent,
    AnalyticsReportsComponent,
    ReportDeleteDialogComponent,
    AnalyticsNewModalComponent,
    AnalyticsNewLinkedinPageComponent,
    AnalyticsNewTwitterPageComponent,
    AnalyticsNewGithubPageComponent,
    AnalyticsNewGithubComponent,
    AnalyticsNewLinkedinComponent,
    AnalyticsNewTwitterComponent,
    AnalyticsReportGithubComponent,
    AnalyticsReportLinkedinComponent,
    AnalyticsReportTwitterComponent,
    AnalyticsReportPageComponent,
    GithubRepositoriesComponent,
    GithubProfileComponent,
    GithubTechnologiesComponent,
    GithubForkedTechnologiesComponent,
    TwitterProfileComponent,
    TwitterSubscriptionsComponent,
    TwitterTechnologiesComponent
  ],
  imports: [
    PipeModule.forRoot(),
    ProgressbarModule.forRoot(),
    BrowserModule,
    BrowserAnimationsModule,
    NgxChartsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    Routing,
    Ng2Webstorage,
    ImageUploadModule.forRoot(),
    NgxPaginationModule,
    Ng2SearchPipeModule,
    OrderModule
  ],
  providers: [AuthenticationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationTokenInterceptor,
      multi: true
    },
    TokenService,
    CookieService,
    ProfileStoreService,
    ProfileService,
    UserService,
    ImageService,
    AnalyticsReportsService,
    GitAnalyticsService,
    TwitterAnalysisService,
    LinkedinAnalysisService,
    NotificationService,
    LoaderService,
    AuthGuard,
    AuthAdminGuard
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
