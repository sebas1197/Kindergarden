import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class MenoresService {

  private API_SERVER = "http://localhost:8080/v1/menor";

  constructor(private httpClient: HttpClient) { }
  
  public crearMenor(menor: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", menor);
  }

  public listarMenores(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarMenor(identificacion: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/identificacion/" + identificacion);
  }

  public editarMenor(menor: any, id: any): Observable<any>{
    return this.httpClient.put(this.API_SERVER + "/update/" + id, menor);
  }

  public borrarMenor(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }
}
