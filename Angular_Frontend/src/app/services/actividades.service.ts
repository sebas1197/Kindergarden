import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ActividadesService {
  private API_SERVER = "http://localhost:8080/v1/actividad";

  constructor(private httpClient: HttpClient) { }

  public crearActividad(actividad: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", actividad);
  }

  public listarActividades(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarActividad(nombre: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/" + nombre);
  }

  public editarActividad(actividad: any, id: any): Observable<any>{
    return this.httpClient.put(this.API_SERVER + "/update/" + id, actividad);
  }

  public borrarActividad(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }

}
