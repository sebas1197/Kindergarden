import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ActividadComponent } from './components/actividad/actividad.component';
import { AlergiasComponent } from './components/alergias/alergias.component';
import { ComedorComponent } from './components/comedor/comedor.component';
import { ErrorComponent } from './components/error/error.component';
import { GruponivelComponent } from './components/gruponivel/gruponivel.component';
import { HorarioComponent } from './components/horario/horario.component';
import { PrincipalComponent } from './components/principal/principal.component';
import { MenorComponent } from './components/menor/menor.component';
import { PlanificacionComponent } from './components/planificacion/planificacion.component';
import { ProfesorComponent } from './components/profesor/profesor.component';
import { RepresentanteComponent } from './components/representante/representante.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';

@NgModule({
  declarations: [
    AppComponent,
    ActividadComponent,
    AlergiasComponent,
    ComedorComponent,
    ErrorComponent,
    GruponivelComponent,
    HorarioComponent,
    PrincipalComponent,
    MenorComponent,
    PlanificacionComponent,
    ProfesorComponent,
    RepresentanteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
