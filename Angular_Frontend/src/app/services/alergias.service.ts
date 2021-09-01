import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AlergiasService {

  private API_SERVER = "http://localhost:8080/v1/alergia";

  constructor(private httpClient: HttpClient) { }
  
  public crearAlergia(alergia: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", alergia);
  }

  public listarAlergias(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarAlergia(id: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/" + id);
  }

  public editarAlergia(alergia: any, id: any): Observable<any>{
    return this.httpClient.put(this.API_SERVER + "/update/" + id, alergia);
  }

  public borrarAlergia(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }
}
