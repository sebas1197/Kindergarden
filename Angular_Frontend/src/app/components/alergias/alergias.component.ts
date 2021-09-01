import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AlergiasService } from 'src/app/services/alergias.service';

@Component({
  selector: 'app-alergias',
  templateUrl: './alergias.component.html',
  styleUrls: ['./alergias.component.css'],
})
export class AlergiasComponent implements OnInit {
  formularioAlergia!: FormGroup;
  alergias: any;

  constructor(
    public fb: FormBuilder,
    public alergiasServices: AlergiasService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.formularioAlergia = this.fb.group({
      id: [''],
      nombre: ['', Validators.required],
      detalle: ['', Validators.required],
    });

    this.alergiasServices.listarAlergias().subscribe(
      (response) => (this.alergias = response),
      (error) => console.log(error)
    );
  }

  crearAlergia(): void {
    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.remove('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.add('none');

    this.alergiasServices.crearAlergia(this.formularioAlergia?.value).subscribe(
      (response) => {
        this.formularioAlergia?.reset();
        this.alergias.push(response);
        this.cerrarModal();
      },
      (error) => console.log(error)
    );

    location.reload();
    alert('Exito!');
  }

  editarAlergia(alergia: any): void {
    this.abrirModal();

    const btn_agregar: any = document
      .querySelector('#btn_agregar')
      ?.classList.add('none');
    const btn_editar: any = document
      .querySelector('#btn_editar')
      ?.classList.remove('none');

    this.formularioAlergia.setValue({
      id: alergia.id,
      nombre: alergia.nombre,
      detalle: alergia.detalle,
    });

    this.abrirModal();
  }

  editar(): void {
    this.alergiasServices.editarAlergia(this.formularioAlergia.value, this.formularioAlergia.value.id).subscribe(
      (response) => {
        this.formularioAlergia.reset();
        this.alergias = this.alergias.filter(
          (alergia: { id: any }) =>
            response.id != alergia.id
        );
        this.cerrarModal();
      },
      (error) => console.log(error)
    );

    location.reload();
    alert('Exito!');
  }

  borrarAlergia(alergia: any): void {
    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.alergiasServices.borrarAlergia(alergia.id).subscribe(
        (response) => {
          if (response) {
            this.alergias.pop(alergia);
            alert('alergia eliminada!');
          }
        },
        (error) => console.log(error)
      );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_alergia')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_alergia')?.classList.remove('none');
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
    this.alergiasServices.buscarAlergia(e.key).subscribe(
      (response) => {
        this.alergias = this.alergias.filter(
          (alergia: { id: any }) =>
            response.id == alergia.id
        );
      },
      (error) => console.log(error)
    );
  }
  
}
