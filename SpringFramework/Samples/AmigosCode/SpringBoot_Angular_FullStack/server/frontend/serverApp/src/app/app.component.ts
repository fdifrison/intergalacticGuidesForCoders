import {ChangeDetectionStrategy, Component, OnInit} from '@angular/core';
import {ServerService} from "./service/server.service";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "./interface/app-state";
import {CustomResponse} from "./interface/custom-response";
import {DataState} from "./enum/data-state.enum";
import {catchError} from "rxjs/operators";
import {Status} from "./enum/status.enum";
import {NgForm} from "@angular/forms";
import {Server} from "./interface/server";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush //improve performance
})
export class AppComponent implements OnInit {
  appState$: Observable<AppState<CustomResponse>>;

  readonly DataState = DataState;
  readonly Status = Status;
  private filterSubject = new BehaviorSubject<string>('');
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  filterStatus$ = this.filterSubject.asObservable();
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();
  // $ define an observable that can be used in the ui waiting for instructions from the backend

  // dependency injection
  constructor(private serverService: ServerService) {
  }

  // Code executed at initialization
  ngOnInit(): void {
    this.appState$ = this.serverService.servers$
      .pipe(
        map(response => {
          this.dataSubject.next(response)
          return {
            dataState: DataState.LOADED_STATE,
            appData: {...response, data: {servers: response.data.servers.reverse()}} //sort servers from newer to older
          }
        }),
        startWith({
          dataState: DataState.LOADING_STATE
        }),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  pingServer(ip: string): void {
    this.filterSubject.next(ip);
    this.appState$ = this.serverService.ping$(ip)
      .pipe(
        map(response => {
          const index = this.dataSubject.value.data.servers.findIndex(server =>
            server.id === response.data.server.id);
          this.dataSubject.value.data.servers[index] = response.data.server
          this.filterSubject.next('')
          return {
            dataState: DataState.LOADED_STATE,
            appData: this.dataSubject.value
          }
        }),
        startWith({
          dataState: DataState.LOADED_STATE,
          appData: this.dataSubject.value
        }),
        catchError((error: string) => {
          this.filterSubject.next('')
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  filterServer(status: Status): void {
    this.appState$ = this.serverService.filter$(status, this.dataSubject.value)
      .pipe(
        map(response => {
          return {
            dataState: DataState.LOADED_STATE,
            appData: response
          }
        }),
        startWith({
          dataState: DataState.LOADING_STATE,
          appData: this.dataSubject.value
        }),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  saveServer(serverForm: NgForm): void {
    this.isLoading.next(true);
    this.appState$ = this.serverService.save$(serverForm.value as Server)
      .pipe(
        map(response => {
          this.dataSubject.next(
            {...response, data: {servers: [response.data.server, ...this.dataSubject.value.data.servers]}}
          );
          document.getElementById('closeModal').click();
          this.isLoading.next(false);
          serverForm.resetForm({status: this.Status.SERVER_DOWN}) // default in the form
          return {
            dataState: DataState.LOADED_STATE,
            appData: this.dataSubject.value
          }
        }),
        startWith({
          dataState: DataState.LOADING_STATE,
          appData: this.dataSubject.value
        }),
        catchError((error: string) => {
          this.isLoading.next(false);
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  deleteServer(server: Server): void {
    this.appState$ = this.serverService.delete$(server.id)
      .pipe(
        map(response => {
         this.dataSubject.next(
           {...response, data:
               { servers: this.dataSubject.value.data.servers.filter(
                 s => s.id !== server.id)}}
         );
          return {
            dataState: DataState.LOADED_STATE,
            appData: this.dataSubject.value
          }
        }),
        startWith({
          dataState: DataState.LOADING_STATE,
          appData: this.dataSubject.value
        }),
        catchError((error: string) => {
          return of({dataState: DataState.ERROR_STATE, error: error})
        })
      );
  }

  printReport(): void {
    // window.print() // print the page, e.g. in pdf format
    let dataType = 'application/vnd.ms-excel';
    let tableSelect = document.getElementById('servers');
    let tableHtml = tableSelect.outerHTML.replace(/ /g, '%20');
    let downloadLink = document.createElement('a');
    document.body.appendChild(downloadLink);
    downloadLink.href = 'data:' + dataType + ', ' + tableHtml;
    downloadLink.download = 'server-report.xls';
    downloadLink.click();
    document.body.removeChild(downloadLink);

  }


}
