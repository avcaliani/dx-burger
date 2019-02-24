import { Component, Input } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';

@Component({
  selector: 'app-loader',
  templateUrl: './loader.component.html',
  styleUrls: ['./loader.component.scss'],
  animations: [
    trigger('fade', [
      state('in', style({ 'opacity': 1 })),
      transition('void => *', [
        style({ 'opacity': 0 }),
        animate(500)
      ]),
      transition('* => void', [
        animate(500, style({ 'opacity': 0 }))
      ])
    ])
  ]
})
export class LoaderComponent {
  @Input() loading = false;
}
