import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ActividadesService } from 'src/app/services/actividades.service';
import { PlanificacionesService } from 'src/app/services/planificaciones.service';
import { ProfesoresService } from 'src/app/services/profesores.service';

@Component({
  selector: 'app-planificacion',
  templateUrl: './planificacion.component.html',
  styleUrls: ['./planificacion.component.css'],
})
export class PlanificacionComponent implements OnInit {
  formularioPlanificacion!: FormGroup;
  planificaciones: any;
  actividades: any;
  profesores: any;

  constructor(
    public fb: FormBuilder,
    public planificacionesServices: PlanificacionesService,
    public actividadesServices: ActividadesService,
    public profesoresServices: ProfesoresService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.profesoresServices.listarProfesores().subscribe(
      (response) => {
        this.profesores = response;
      },
      (error) => console.log(error)
    );

    this.actividadesServices.listarActividades().subscribe(
      (response) => {
        this.actividades = response;
        
      },
      (error) => console.log(error)
    );

    this.formularioPlanificacion = this.fb.group({
      id: [''],
      lugar: ['', Validators.required],
      nombreActividad: ['', Validators.required],
      apellidoProfesor: ['', Validators.required],
    });

    this.planificacionesServices.listarPlanificaciones().subscribe(
      (response) => (this.planificaciones = response),
      (error) => console.log(error)
    );

    console.log("planificaciones: ", this.formularioPlanificacion.value);
  }

  crearPlanificacion(): void {
    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.remove('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.add('none');

     console.log("Esta planificacion va: ", this.formularioPlanificacion.value);

    this.planificacionesServices
      .crearPlanificacion(this.formularioPlanificacion.value)
      .subscribe(
        (response) => {
          this.formularioPlanificacion?.reset();
          this.planificaciones.push(response);
          this.cerrarModal();
          console.log(response);
        },
        (error) => console.log(error)
      );

    location.reload();
    alert('Exito');
  }

  editarPlanificacion(planificacion: any): void {
    this.abrirModal();

    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.add('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.remove('none');

    this.formularioPlanificacion.setValue({
      id: planificacion.id,
      lugar: planificacion.lugar,
      nombreActividad: planificacion.nombreActividad,
      apellidoProfesor: planificacion.apellidoProfesor,
    });

    this.abrirModal();
  }

  editar(): void {
    this.planificacionesServices
      .editarPlanificacion(this.formularioPlanificacion.value, this.formularioPlanificacion.value.id)
      .subscribe(
        (response) => {
          this.formularioPlanificacion.reset();
          this.planificaciones = this.planificaciones.filter(
            (planificacion: { id: any }) =>
              response.id != planificacion.id
          );
          this.cerrarModal();
        },
        (error) => console.log(error)
      );

    location.reload();
    alert('Exito!');
  }

  borrarPlanificacion(planificacion: any): void {
    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.planificacionesServices
        .borrarPlanificacion(planificacion.id)
        .subscribe(
          (response) => {
            if (response) {
              this.planificaciones.pop(planificacion);
              alert('planificacion eliminada!');
            }
          },
          (error) => console.log(error)
        );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_planificacion')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_planificacion')?.classList.remove('none');
  }

  soloLetras(e: any): any {
    let key = e.keyCode || e.which;
    let tecla = String.fromCharCode(key).toLowerCase();
    let letras = ' áéíóúabcdefghijklmnñopqrstuvwxyz';
    let especiales = [8, 37, 39, 46];

    let tecla_especial = false;
    for (var i in especiales) {
      if (key == especiales[i]) {
        tecla_especial = true;
        break;
      }
    }
    if (letras.indexOf(tecla) == -1 && !tecla_especial) return false;
  }

  busqueda(e: any): any {
    this.planificacionesServices
      .buscarPlanificaciones(e.key)
      .subscribe(
        (response) => {
          this.planificaciones = this.planificaciones.filter(
            (planificacion: { id: any }) =>
              response.id == planificacion.id
          );
        },
        (error) => console.log(error)
      );
  }
}
