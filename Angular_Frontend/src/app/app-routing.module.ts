import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActividadComponent } from './components/actividad/actividad.component';
import { AlergiasComponent } from './components/alergias/alergias.component';
import { ComedorComponent } from './components/comedor/comedor.component';
import { ErrorComponent } from './components/error/error.component';
import { GruponivelComponent } from './components/gruponivel/gruponivel.component';
import { HorarioComponent } from './components/horario/horario.component';
import { MenorComponent } from './components/menor/menor.component';
import { PlanificacionComponent } from './components/planificacion/planificacion.component';
import { PrincipalComponent } from './components/principal/principal.component';
import { ProfesorComponent } from './components/profesor/profesor.component';
import { RepresentanteComponent } from './components/representante/representante.component';

const routes: Routes = [
  {path: '', component: PrincipalComponent},
  {path: 'actividad', component: ActividadComponent},
  {path: 'alergias', component: AlergiasComponent},
  {path: 'comedor', component: ComedorComponent},
  {path: 'gruponivel', component: GruponivelComponent},
  {path: 'horario', component: HorarioComponent},
  {path: 'menor', component: MenorComponent},
  {path: 'planificacion', component: PlanificacionComponent},
  {path: 'profesor', component: ProfesorComponent},
  {path: 'representante', component: RepresentanteComponent},
  {path: '**', component: ErrorComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
