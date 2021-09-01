import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ComedoresService } from 'src/app/services/comedores.service';
import { MenoresService } from 'src/app/services/menores.service';

@Component({
  selector: 'app-comedor',
  templateUrl: './comedor.component.html',
  styleUrls: ['./comedor.component.css'],
})
export class ComedorComponent implements OnInit {
  formularioComedor!: FormGroup;
  comedores: any;
  menores: any;

  constructor(
    public fb: FormBuilder,
    public comedoresServices: ComedoresService,
    public menoresServices: MenoresService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.menoresServices.listarMenores().subscribe(
      (response) => {
        this.menores = response;
      },
      (error) => console.log(error)
    );

    this.formularioComedor = this.fb.group({
      id: [''],
      nombreComida: ['', Validators.required],
      apellidoMenor: ['', Validators.required],
    });

    this.comedoresServices.listarComedores().subscribe(
      (response) => {
        this.comedores = response;
      },
      (error) => console.log(error)
    );
  }

  crearComedor(): void {
    if (this.validarCampos()) {
      this.comedoresServices
        .crearComedor(this.formularioComedor?.value)
        .subscribe(
          (response) => {
            this.formularioComedor?.reset();
            this.comedores = this.comedores.filter(
              (comedor: { id: any }) =>
                response.id != comedor.id
            );
            this.comedores.push(response);
            this.cerrarModal();
          },
          (error) => console.log(error)
        );
      location.reload();
      alert('Exito');
    }
  }

  editarComedor(comedor: any): void {
    const btn: any = document.querySelector('#btn_modal');
    if (btn) btn.innerHTML = 'Modificar';

    this.formularioComedor.setValue({
      id: comedor.id,
      nombreComida: comedor.nombreComida,
      apellidoMenor: comedor.apellidoMenor,
    });

    this.abrirModal();
  }

  borrarComedor(comedor: any): void {
    if (confirm('Se eliminará el registro, ¿Desea continuar?')) {
      this.comedoresServices.borrarComedor(comedor.id).subscribe(
        (response) => {
          if (response) {
            this.comedores.pop(comedor);
            alert('Comedor eliminado!');
          }
        },
        (error) => console.log(error)
      );
    }

    location.reload();
  }

  abrirModal(): void {
    document.querySelector('#form_comedor')?.classList.add('none');
    document.querySelector('#formulario')?.classList.remove('none');
  }

  cerrarModal(): void {
    document.querySelector('#formulario')?.classList.add('none');
    document.querySelector('#form_comedor')?.classList.remove('none');
  }

  validarCampos(): boolean {
    let fnombreComida: any = document.querySelector('#nombreComida');

    if (fnombreComida.value == '') {
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

  busqueda(e: any): any {
  console.log(e.key);

    this.comedoresServices.buscarComedor(e.key).subscribe(
      (response) => {
        this.comedores = this.comedores.filter(
          (comedor: { id: any }) =>
            response.id == comedor.id
        );
      },
      (error) => console.log(error)
    );
  }
}
