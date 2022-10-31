import {Injectable} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {catchError, tap} from "rxjs/operators";
import {CustomResponse} from "../interface/custom-response";
import {Server} from "../interface/server";
import {Status} from "../enum/status.enum";

@Injectable({providedIn: "root"})
export class ServerService {
  private readonly apiUrl = "http://localhost:8080";

  constructor(private http: HttpClient) {
  }

  servers$ = <Observable<CustomResponse>>this.http.get<CustomResponse>(`${this.apiUrl}/server/list`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )

  save$ = (server: Server) => <Observable<CustomResponse>>
    this.http.post<CustomResponse>(`${this.apiUrl}/server/save`, server)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      )

  ping$ = (ip: string) => <Observable<CustomResponse>>
    this.http.get<CustomResponse>(`${this.apiUrl}/server/ping/${ip}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      )

  filter$ = (status: Status, response: CustomResponse) => {
    return <Observable<CustomResponse>>
      new Observable<CustomResponse>(
        subscriber => {
          console.log(response);
          subscriber.next(
            status === Status.ALL ? {...response, message: `Server filtered by ${status} status`} :
              {
                ...response,
                message: response.data.servers.filter(server => server.status == status).length > 0 ?
                  `Server filtered by ${status === Status.SERVER_UP ? "SERVER UP" : "SERVER DOWN"} status` :
                  `No server of ${status} found`,
                data: {servers: response.data.servers.filter(server => server.status == status)}
              }
          );
          subscriber.complete();
        }
      )
        .pipe(
          tap(console.log),
          catchError(this.handleError)
        );
  }

  delete$ = (serverId: number) => <Observable<CustomResponse>>
    this.http.delete<CustomResponse>(`${this.apiUrl}/server/delete/${serverId}`)
      .pipe(
        tap(console.log),
        catchError(this.handleError)
      )

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
    return throwError(() => `An error occurred - Error code: ${error.status}`)
  }

  /*
     Procedural approach = standard
     getServers(): Observable<CustomResponse> {
         return this.http.get<CustomResponse>("http://localhost:8080/server/list");
       }
    */

}
