import { Injectable } from "@angular/core";
import { Headers, Http, RequestOptions, Response } from "@angular/http";
import { Observable } from "rxjs";
import { environment } from "../../environments/environment";

@Injectable()
export class GitProfileService {
  private static GIT = '/git/profile';

  constructor(private http: Http) {

  }

  getGitProfileData(gitLogin: String): Observable<Object> {
    const headers = new Headers({
      'Content-Type': 'application/json',
      'x-auth-token': JSON.parse(localStorage.getItem('currentUser')).token
    });
    const options = new RequestOptions({ headers: headers });
    // return this.http.get(environment.serverUrl + `${GitProfileService.GIT}/${gitLogin}`, options)
    return this.http.get('./www/temp.json')
      .map((response: Response) => {
        return GitProfileService.extractData(response);
      }).catch(GitProfileService.handleError);
  }

  private static extractData(res: Response) {
    let data = res.json();
    return data || {};
  }

  private static handleError(error: Response | any) {
    let errMsg: string;
    if (error instanceof Response) {
      const body = error.json() || '';
      const err = body.error || JSON.stringify(body);
      errMsg = `${error.status} - ${error.statusText} || ''} ${err}`;
    } else {
      errMsg = error.message ? error.message : error.toString();
    }
    console.error(errMsg);
    return Observable.throw(errMsg);
  }
}
