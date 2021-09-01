import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { HorariosService } from 'src/app/services/horarios.service';

@Component({
  selector: 'app-horario',
  templateUrl: './horario.component.html',
  styleUrls: ['./horario.component.css'],
})
export class HorarioComponent implements OnInit {
  formularioHorario!: FormGroup;
  horarios: any;

  constructor(
    public fb: FormBuilder,
    public horariosServices: HorariosService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.horariosServices.listarHorarios().subscribe(
      (response) => (this.horarios = response),
      (error) => console.log(error)
    );
  }

  busqueda(e: any): any {
    this.horariosServices.buscarHorario(e.key).subscribe(
      (response) => {
        this.horarios = this.horarios.filter(
          (horario: { id: any }) =>
            response.id == horario.id
        );
      },
      (error) => console.log(error)
    );
  }
}
