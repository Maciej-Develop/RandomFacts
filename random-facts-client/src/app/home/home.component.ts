import { Component, inject, OnInit } from '@angular/core';
import { FactsService } from '../services/facts.service';
import { catchError } from 'rxjs';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  service = inject(FactsService);

  ngOnInit(): void {
    this.service.getFactFromApi()
      .pipe(catchError((err) => {
        console.log(err);
        throw err;
      })).subscribe((fact) => {
        console.log(fact);
      })
  }
}
