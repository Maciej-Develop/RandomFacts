import { Component, input, signal } from '@angular/core';
import { Fact } from '../../model/fact.type';

@Component({
  selector: 'app-fact',
  imports: [],
  templateUrl: './fact.component.html',
  styleUrl: './fact.component.css'
})
export class FactComponent {
  fact = input.required<Fact>();
  img = signal("http://localhost:8080/api/img?id=");
}
