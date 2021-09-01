import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ComedoresService {

  private API_SERVER = "http://localhost:8080/v1/comedor";

  constructor(private httpClient: HttpClient) { }

  public listarComedores(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarComedor(id: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/" + id);
  }

  public crearComedor(comedor: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", comedor);
  }

  public borrarComedor(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }
}
