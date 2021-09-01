import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActividadesService } from './services/actividades.service';
import { AlergiasService } from './services/alergias.service';
import { ComedoresService } from './services/comedores.service';
import { GruposnivelesService } from './services/gruposniveles.service';
import { HorariosService } from './services/horarios.service';
import { MenoresService } from './services/menores.service';
import { PlanificacionesService } from './services/planificaciones.service';
import { ProfesoresService } from './services/profesores.service';
import { RepresentantesService } from './services/representantes.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
 
  title = 'Guarderia';

  constructor(
    public fb: FormBuilder,
    public actividadesService: ActividadesService,
    public alergiasService: AlergiasService,
    public comedoresService: ComedoresService,
    public gruposnivelesService: GruposnivelesService,
    public horariosService: HorariosService,
    public menoresService: MenoresService,
    public planificacionesService: PlanificacionesService,
    public profesoresService: ProfesoresService,
    public representantesService: RepresentantesService
  ){

  }

  ngOnInit(): void {
    
  }
}
