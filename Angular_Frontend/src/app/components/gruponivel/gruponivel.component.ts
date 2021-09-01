import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { GruposnivelesService } from 'src/app/services/gruposniveles.service';
import { MenoresService } from 'src/app/services/menores.service';
import { ProfesoresService } from 'src/app/services/profesores.service';

@Component({
  selector: 'app-gruponivel',
  templateUrl: './gruponivel.component.html',
  styleUrls: ['./gruponivel.component.css'],
})
export class GruponivelComponent implements OnInit {
  formularioGrupoNivel!: FormGroup;
  gruposNiveles: any;
  profesores: any;

  constructor(
    public fb: FormBuilder,
    public gruposNivelesServices: GruposnivelesService,
    public profesoresServices: ProfesoresService
  ) {}

  ngOnInit(): void {
    this.profesoresServices.listarProfesores().subscribe(
      (response) => {
        this.profesores = response;
      },
      (error) => console.log(error)
    );

    this.formularioGrupoNivel = this.fb.group({
      id: [''],
      nombre: ['', Validators.required],
      rangoEdad: ['', Validators.required],
      detalle: ['', Validators.required],
      apellidoProfesor: ['', Validators.required],
    });

    this.gruposNivelesServices.listarGruposNiveles().subscribe(
      (response) => {
        this.gruposNiveles = response;
      },
      (error) => console.log(error)
    );
  }

  crearGrupoNivel(): void {
    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.remove('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.add('none');

    this.gruposNivelesServices
      .crearGrupoNivel(this.formularioGrupoNivel?.value)
      .subscribe(
        (response) => {
          this.formularioGrupoNivel?.reset();
          this.gruposNiveles.push(response);
          this.cerrarModal();
        },
        (error) => console.log(error)
      );

    location.reload();
    alert('Exito!');
  }

  editarGrupoNivel(grupoNivel: any): void {
    this.abrirModal();

    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.add('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.remove('none');

    this.formularioGrupoNivel.setValue({
      id: grupoNivel.id,
      nombre: grupoNivel.nombre,
      rangoEdad: grupoNivel.rangoEdad,
      detalle: grupoNivel.detalle,
      apellidoProfesor: grupoNivel.apellidoProfesor,
    });

    this.abrirModal();
  }

  editar(): void {
    this.gruposNivelesServices
      .editarGrupoNivel(this.formularioGrupoNivel.value, this.formularioGrupoNivel.value.id)
      .subscribe(
        (response) => {
          this.formularioGrupoNivel.reset();
          this.gruposNiveles = this.gruposNiveles.filter(
            (grupoNivel: { id: any }) =>
              response.id != grupoNivel.id
          );
          this.cerrarModal();
        },
        (error) => console.log(error)
      );

    location.reload();
    alert('Exito!');
  }

  borrarGrupoNivel(grupoNivel: any): void {
    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.gruposNivelesServices
        .borrarGrupoNivel(grupoNivel.id)
        .subscribe(
          (response) => {
            if (response) {
              this.gruposNiveles.pop(grupoNivel);
              alert('Grupos del nivel eliminados!');
            }
          },
          (error) => console.log(error)
        );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_grupoNivel')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_grupoNivel')?.classList.remove('none');
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

  let e:any = document.querySelector('#buscarGrupo');

    this.gruposNivelesServices.buscarGrupoNivel(e.value).subscribe(
      (response) => {
        this.gruposNiveles = this.gruposNiveles.filter(
          (grupoNivel: { id: any }) =>
            response.id == grupoNivel.id
        );
      },
      (error) => console.log(error)
    );
  }
}
