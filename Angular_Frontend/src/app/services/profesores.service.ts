import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfesoresService {

  private API_SERVER = "http://localhost:8080/v1/profesor";

  constructor(private httpClient: HttpClient) { }

  public crearProfesor(profesor: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", profesor);
  }

  public listarProfesores(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarProfesor(identificacion: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/identificacion/" + identificacion);
  }

  public editarProfesor(profesor: any, id: any): Observable<any>{
    return this.httpClient.put(this.API_SERVER + "/update/" + id, profesor);
  }

  public borrarProfesor(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }

}
