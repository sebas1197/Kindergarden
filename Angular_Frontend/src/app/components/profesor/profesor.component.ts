import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ProfesoresService } from 'src/app/services/profesores.service';

@Component({
  selector: 'app-profesor',
  templateUrl: './profesor.component.html',
  styleUrls: ['./profesor.component.css'],
})
export class ProfesorComponent implements OnInit {
  formularioProfesor!: FormGroup;
  profesores: any;

  constructor(
    public fb: FormBuilder,
    public profesoresServices: ProfesoresService
  ) {}

  ngOnInit(): void {
    this.formularioProfesor = this.fb.group({
      id: [''],
      tipoIdentificacion: ['', Validators.required],
      identificacion: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      telefono: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      direccion: ['', Validators.required],
      genero: ['', Validators.required],
    });

    this.profesoresServices.listarProfesores().subscribe(
      (response) => {
        this.profesores = response;
        console.log(response);
      }
      ,
      (error) => console.log(error)
    );

    // console.log("Profes: ", this.profesores);
  }

  crearProfesor(): void {
    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.remove('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.add('none');

    this.profesoresServices
      .crearProfesor(this.formularioProfesor?.value)
      .subscribe(
        (response) => {
          this.formularioProfesor?.reset();
          this.profesores.push(response);
          this.cerrarModal();
        },
        (error) => console.log(error)
      );
    location.reload();
    alert('Exito!');
  }

  editarProfesor(profesor: any): void {
    this.abrirModal();

    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.add('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.remove('none');

    this.formularioProfesor.setValue({
      id: profesor.id,
      tipoIdentificacion: profesor.tipoIdentificacion,
      identificacion: profesor.identificacion,
      nombre: profesor.nombre,
      apellido: profesor.apellido,
      telefono: profesor.telefono,
      correo: profesor.correo,
      direccion: profesor.direccion,
      genero: profesor.genero,
    });
  }

  editar(): void {
    this.profesoresServices
      .editarProfesor(this.formularioProfesor.value, this.formularioProfesor.value.id)
      .subscribe(
        (response) => {
          this.formularioProfesor.reset();
          this.profesores = this.profesores.filter(
            (profesor: { id: any }) =>
              response.id != profesor.id
          );
          this.cerrarModal();
        },
        (error) => console.log(error)
      );

    location.reload();
    alert('Exito!');
  }

  borrarProfesor(profesor: any): void {
    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.profesoresServices.borrarProfesor(profesor.id).subscribe(
        (response) => {
          if (response) {
            this.profesores.pop(profesor);
            alert('Profesor eliminado!');
          }
        },
        (error) => console.log(error)
      );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_profesor')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_profesor')?.classList.remove('none');
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

  soloNumeros(e: any): any {
    var code = e.which ? e.which : e.keyCode;

    if (code == 8) {
      return true;
    } else if (code >= 48 && code <= 57) {
      return true;
    } else {
      return false;
    }
  }

  busqueda(): any {

    let buscar: any = document.querySelector("#buscarProfesor");

    this.profesoresServices.buscarProfesor(buscar.value).subscribe(
      (response) => {
        this.profesores = this.profesores.filter(
          (profesor: { identificacion: any }) =>
            response.identificacion == profesor.identificacion
        );
      },
      (error) => console.log(error)
    );
  }
}
