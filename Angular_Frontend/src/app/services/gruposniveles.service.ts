import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GruposnivelesService {

  private API_SERVER = "http://localhost:8080/v1/gruponivel";

  constructor(private httpClient: HttpClient) { }

  public crearGrupoNivel(grupoNivel: any): Observable<any>{
    return this.httpClient.post(this.API_SERVER + "/save", grupoNivel);
  }

  public listarGruposNiveles(): Observable<any>{
    return this.httpClient.get(this.API_SERVER);
  }

  public buscarGrupoNivel(id: any): Observable<any>{
    return this.httpClient.get(this.API_SERVER + "/" + id);
  }

  public editarGrupoNivel(grupoNivel: any, id: any): Observable<any>{
    return this.httpClient.put(this.API_SERVER + "/update/" + id, grupoNivel);
  }

  public borrarGrupoNivel(id: any): Observable<any>{
    return this.httpClient.delete(this.API_SERVER + "/delete/" + id);
  }
}
