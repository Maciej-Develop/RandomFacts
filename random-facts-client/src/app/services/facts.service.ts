import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Fact } from '../model/fact.type';

@Injectable({
  providedIn: 'root'
})
export class FactsService {
  http = inject(HttpClient);
  private url = `http://localhost:8080/api`;

  constructor() { }

  getFactFromApi() {
    return this.http.get<Fact>(this.url + `/fact`);
  }
}
