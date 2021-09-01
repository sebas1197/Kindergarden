import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-principal',
  templateUrl: './principal.component.html',
  styleUrls: ['./principal.component.css'],
})
export class PrincipalComponent implements OnInit {
  constructor(private router: Router) {}

  ngOnInit(): void {}

  student(): void {
    this.router.navigate(['representante']);
    setTimeout(
      () => alert('Por favor presione el botón agregar para continuar'),
      1000
    );
  }

  teacher(): void {
    this.router.navigate(['profesor']);
    setTimeout(
      () => alert('Por favor presione el botón agregar para continuar'),
      1000
    );
  }

  actividades(): void {
    this.router.navigate(['actividad']);
  }

  planificaciones(): void {
    this.router.navigate(['planificacion']);
  }

  comedores(): void {
    this.router.navigate(['comedor']);
  }

  gruposNivel(): void {
    this.router.navigate(['gruponivel']);
  }

  representantes(): void {
    this.router.navigate(['representante']);
  }

  profesores(): void {
    this.router.navigate(['profesor']);
  }

  horarios(): void {
    this.router.navigate(['horario']);
  }

  alergias(): void {
    this.router.navigate(['alergias']);
  }

  menores(): void {
    this.router.navigate(['menor']);
  }
}
