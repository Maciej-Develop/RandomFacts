import { Component, inject, OnInit, signal } from '@angular/core';
import { FactsService } from '../services/facts.service';
import { catchError } from 'rxjs';
import { FactComponent } from "../components/fact/fact.component";
import { Fact } from '../model/fact.type';
import { LoadingComponent } from '../components/loading/loading.component';

@Component({
  selector: 'app-home',
  imports: [FactComponent, LoadingComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit{
  service = inject(FactsService);
  ready = signal(false);
  fact = signal<Fact>({id:0, description:""});

  ngOnInit(): void {
    this.service.getFactFromApi()
      .pipe(catchError((err) => {
        console.log(err);
        throw err;
      })).subscribe((fact) => {
        this.fact.set(fact);
        this.ready.set(true);
      })
  }
}
