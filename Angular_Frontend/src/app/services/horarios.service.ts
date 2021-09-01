import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HorariosService {

  private API_SERVER = "http://localhost:8080/v1/horario";

  constructor(private httpClient: HttpClient) { }

  public listarHorarios(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarHorario(id: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/" + id);
  }

  public crearHorario(horario: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", horario);
  }

  public borrarHorario(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }

}
