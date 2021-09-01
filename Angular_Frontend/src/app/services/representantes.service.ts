import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RepresentantesService {

  private API_SERVER = "http://localhost:8080/v1/representante/";

  constructor(private httpClient: HttpClient) { }

  public listarRepresentantes(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarRepresentante(identificacion: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/identificacion/" + identificacion);
  }

  public crearRepresentantes(representante: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", representante);
  }

  public borrarRepresentante(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }
}
