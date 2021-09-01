import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AlergiasService } from 'src/app/services/alergias.service';
import { ComedoresService } from 'src/app/services/comedores.service';
import { GruposnivelesService } from 'src/app/services/gruposniveles.service';
import { HorariosService } from 'src/app/services/horarios.service';
import { MenoresService } from 'src/app/services/menores.service';
import { RepresentantesService } from 'src/app/services/representantes.service';

@Component({
  selector: 'app-menor',
  templateUrl: './menor.component.html',
  styleUrls: ['./menor.component.css'],
})
export class MenorComponent implements OnInit {
  formularioMenor!: FormGroup;
  menores: any;
  representantes: any;
  horarios: any;
  Alergias: any;
  gruposNiveles: any;

  constructor(
    public fb: FormBuilder,
    public representantesServices: RepresentantesService,
    public horariosServices: HorariosService,
    public alergiasServices: AlergiasService,
    public gruposNivelesServices: GruposnivelesService,
    public menoresServices: MenoresService
  ) {}

  ngOnInit(): void {
    this.representantesServices.listarRepresentantes().subscribe(
      (response) => {
        this.representantes = response;
      },
      (error) => console.log(error)
    );

    this.horariosServices.listarHorarios().subscribe(
      (response) => {
        this.horarios = response;
      },
      (error) => console.log(error)
    );

    this.alergiasServices.listarAlergias().subscribe(
      (response) => {
        this.Alergias = response;
      },
      (error) => console.log(error)
    );

    this.gruposNivelesServices.listarGruposNiveles().subscribe(
      (response) => {
        this.gruposNiveles = response;
      },
      (error) => console.log(error)
    );

    this.formularioMenor = this.fb.group({
      id: [''],
      tipoIdentificacion: ['', Validators.required],
      identificacion: ['', Validators.required],
      nombre: ['', Validators.required],
      apellido: ['', Validators.required],
      fechaNacimiento: ['', Validators.required],
      genero: ['', Validators.required],
      direccion: ['', Validators.required],
      nombreAlergias: [''],
      nombreGrupoNivel: [''],
      horarioJornada: [''],
      apellidoRepresentante: ['']
    });

    this.menoresServices.listarMenores().subscribe(
      (response) => (this.menores = response),
      (error) => console.log(error)
    );
  }

  crearMenor(): void {
    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.remove('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.add('none');

    this.menoresServices.crearMenor(this.formularioMenor?.value).subscribe(
      (response) => {
        this.formularioMenor?.reset();
        this.menores.push(response);
        this.cerrarModal();
      },
      (error) => console.log(error)
    );

    location.reload();
    alert('Exito!');
  }

  editarMenor(menor: any): void {
    this.abrirModal();

    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.add('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.remove('none');

    this.formularioMenor.setValue({
      id: menor.id,
      tipoIdentificacion: menor.tipoIdentificacion,
      identificacion: menor.identificacion,
      nombre: menor.nombre,
      apellido: menor.apellido,
      fechaNacimiento: menor.fechaNacimiento,
      genero: menor.genero,
      direccion: menor.direccion,
      nombreAlergias: menor.nombreAlergias,
      nombreGrupoNivel: menor.nombreGrupoNivel,
      horarioJornada: menor.horarioJornada,
      apellidoRepresentante: menor.apellidoRepresentante
    });

    this.formularioMenor = this.fb.group({
      ...this.formularioMenor.controls,
      representante: this.representantes[this.representantes.length - 1],
    });

    this.abrirModal();
  }

  editar(): void {
    this.menoresServices.editarMenor(this.formularioMenor.value, this.formularioMenor.value.id).subscribe(
      (response) => {
        this.formularioMenor.reset();
        this.menores = this.menores.filter(
          (menor: { id: any }) =>
            response.id != menor.id
        );
        this.cerrarModal();
      },
      (error) => console.log(error)
    );

    location.reload();
    alert('Exito!');
  }

  borrarMenor(menor: any): void {
    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.menoresServices.borrarMenor(menor.id).subscribe(
        (response) => {
          if (response) {
            this.menores.pop(menor);
            alert('Menor eliminado!');
          }
        },
        (error) => console.log(error)
      );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_menor')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_menor')?.classList.remove('none');
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

  let buscar: any = document.querySelector('#buscarMenor');

    this.menoresServices.buscarMenor(buscar.value).subscribe(
      (response) => {
        this.menores = this.menores.filter(
          (menor: { identificacion: any }) =>
            response.identificacion == menor.identificacion
        );
      },
      (error) => console.log(error)
    );
  }
}
