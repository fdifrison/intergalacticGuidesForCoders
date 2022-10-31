import {DataState} from "../enum/data-state.enum";

export interface AppState<T> {
  dataState: DataState;
  // either we get data or error not both
  appData?: T;
  error?: string;
}
