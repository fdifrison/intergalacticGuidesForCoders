import {Server} from "./server";

export interface CustomResponse {
  timeStamp: Date;
  statusCode: number;
  status: string;
  reason: string;
  message: string;
  developerMessage: string;
  // ? after the variable means that it is optional
  data: {servers?: Server[], server?: Server}
}
