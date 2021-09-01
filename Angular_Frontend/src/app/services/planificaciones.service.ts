import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PlanificacionesService {

  private API_SERVER = "http://localhost:8080/v1/planificacion";

  constructor(private httpClient: HttpClient) { }

  public crearPlanificacion(planificacion: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", planificacion);
  }

  public listarPlanificaciones(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarPlanificaciones(id: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/" + id);
  }

  public editarPlanificacion(planificacion: any, id: any): Observable<any>{
    return this.httpClient.put(this.API_SERVER + "/update/" + id, planificacion);
  }

  public borrarPlanificacion(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }

}
