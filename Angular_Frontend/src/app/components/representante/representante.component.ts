import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RepresentantesService } from 'src/app/services/representantes.service';

@Component({
  selector: 'app-representante',
  templateUrl: './representante.component.html',
  styleUrls: ['./representante.component.css'],
})
export class RepresentanteComponent implements OnInit {
  formularioRepresentante!: FormGroup;
  representantes: any;

  constructor(
    public fb: FormBuilder,
    public representantesServices: RepresentantesService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formularioRepresentante = this.fb.group({
      id: [''],
      tipoIdentificacion: ['', Validators.required],
      identificacion: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      relacion: ['', Validators.required],
      telefono: ['', Validators.required],
      correo: ['', [Validators.required, Validators.email]],
      direccion: ['', Validators.required],
      ocupacion: ['', Validators.required],
    });

    this.representantesServices.listarRepresentantes().subscribe(
      (response) => (this.representantes = response),
      (error) => console.log(error)
    );
  }

  crearRepresentante(): void {
    if (this.validarCampos()) {
      this.representantesServices
        .crearRepresentantes(this.formularioRepresentante?.value)
        .subscribe(
          (response) => {
            this.formularioRepresentante?.reset();
            this.representantes = this.representantes.filter(
              (representante: { id: any }) =>
                response.id !=
                representante.id
            );
            this.representantes.push(response);
            this.cerrarModal();
          },
          (error) => console.log(error)
        );

        location.reload();
        alert('Exito!');
    }
  }

  editarRepresentante(representante: any): void {
    const btn: any = document.querySelector('#btn_modal');
    if (btn) btn.innerHTML = 'Modificar';

    this.formularioRepresentante.setValue({
      id: representante.id,
      tipoIdentificacion: representante.tipoIdentificacion,
      identificacion: representante.identificacion,
      nombre: representante.nombre,
      apellido: representante.apellido,
      relacion: representante.relacion,
      telefono: representante.telefono,
      correo: representante.correo,
      direccion: representante.direccion,
      ocupacion: representante.ocupacion,
    });

    this.abrirModal();
  }

  borrarRepresentante(representante: any): void {

    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.representantesServices
        .borrarRepresentante(representante.id)
        .subscribe(
          (response) => {
            if (response) {
              this.representantes.pop(representante);
              alert('Representante eliminado!');
            }
          },
          (error) => console.log(error)
        );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_representante')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_representante')?.classList.remove('none');
  }

  validarCampos(): boolean {
    let fidentificacion: any = document.querySelector('#identificacion');
    let fnombre: any = document.querySelector('#nombre');
    let fapellido: any = document.querySelector('#apellido');
    let frelacion: any = document.querySelector('#relacion');
    let ftelefeno: any = document.querySelector('#telefono');
    let fcorreo: any = document.querySelector('#correo');
    let focupacion: any = document.querySelector('#ocupacion');
    let fdireccion: any = document.querySelector('#direccion');
    let expresionCorreo: any =
      /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    if (fidentificacion.value.length != 10) {
      alert('La identificacion debe tener 10 digitos');
      return false;
    }
    if (ftelefeno.value.length < 8 || ftelefeno.value.length > 10) {
      alert('Datos incorrectos en el campo telefono');
      return false;
    }

    if (!expresionCorreo.test(fcorreo.value)) {
      alert('Correo incorrecto!');
      return false;
    }

    if (
      fidentificacion.value == '' ||
      fnombre.value == '' ||
      fapellido.value == '' ||
      frelacion.value == '' ||
      ftelefeno.value == '' ||
      fcorreo.value == '' ||
      focupacion.value == '' ||
      fdireccion.value == ''
    ) {
      alert('TODOS LOS CAMPOS SON OBLIGATORIOS!');
      return false;
    } else {
      return true;
    }
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

  let buscar: any = document.querySelector('#buscarRepresentante');

    this.representantesServices.buscarRepresentante(buscar.value).subscribe(
      (response) => {
        this.representantes = this.representantes.filter(
          (representante: { identificacion: any }) =>
            response.identificacion == representante.identificacion
        );
      },
      (error) => console.log(error)
    );
  }
}
