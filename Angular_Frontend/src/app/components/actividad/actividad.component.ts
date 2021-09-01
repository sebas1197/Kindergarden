import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ActividadesService } from 'src/app/services/actividades.service';

@Component({
  selector: 'app-actividad',
  templateUrl: './actividad.component.html',
  styleUrls: ['./actividad.component.css'],
})
export class ActividadComponent implements OnInit {
  formularioActividad!: FormGroup;
  actividades: any;

  constructor(
    public fb: FormBuilder,
    public actividadesServices: ActividadesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formularioActividad = this.fb.group({
      id: [''],
      nombre: ['', Validators.required],
      descripcion: ['', Validators.required],
    });

    this.actividadesServices.listarActividades().subscribe(
      (response) => (this.actividades = response),
      (error) => console.log(error)
    );
  }

  crearActividad(): void {
    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.remove('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.add('none');

    this.actividadesServices
      .crearActividad(this.formularioActividad?.value)
      .subscribe(
        (response) => {
          this.formularioActividad?.reset();
          this.actividades.push(response);
          this.cerrarModal();
        },
        (error) => console.log(error)
      );
    location.reload();
    alert('Exito!');
  }

  editarActividad(actividad: any): void {
    this.abrirModal();

    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.add('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.remove('none');

    this.formularioActividad.setValue({
      id: actividad.id,
      nombre: actividad.nombre,
      descripcion: actividad.descripcion,
    });

    this.abrirModal();
  }

  editar(): void {
    this.actividadesServices
      .editarActividad(this.formularioActividad.value, this.formularioActividad.value.id)
      .subscribe(
        (response) => {
          this.formularioActividad.reset();
          this.actividades = this.actividades.filter(
            (actividad: { id: any }) =>
              response.id != actividad.id
          );
          this.cerrarModal();
        },
        (error) => console.log(error)
      );

    location.reload();
    alert('Exito!');
  }

  borrarActividad(actividad: any): void {
    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.actividadesServices
        .borrarActividad(actividad.id)
        .subscribe(
          (response) => {
            if (response) {
              this.actividades.pop(actividad);
              alert('actividad eliminada!');
            }
          },
          (error) => console.log(error)
        );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_actividad')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_actividad')?.classList.remove('none');
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

  busqueda(): any {
    let nombre: any = document.querySelector('#nombreBuscar');

    this.actividadesServices.buscarActividad(nombre.value).subscribe(
      (response) => {
        this.actividades = response;
      },
      (error) => console.log(error)
    );
  }
}
