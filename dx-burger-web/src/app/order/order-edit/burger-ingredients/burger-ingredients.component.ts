import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { trigger, state, style, transition, animate } from '@angular/animations';
import { FormGroup, FormArray, FormControl, Validators, AbstractControl } from '@angular/forms';
import { OrderItem, OrderIngredient } from '../../order.model';

@Component({
  selector: 'app-burger-ingredients',
  templateUrl: './burger-ingredients.component.html',
  styles: [`
    .modal {
      display: block;
      background: rgba(0,0,0,0.6);
    }
  `],
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
export class BurgerIngredientsComponent implements OnInit {

  @Input() orderItem: OrderItem;
  @Input() reading = false;
  @Output() updated = new EventEmitter<OrderItem>();
  form: FormArray;

  ngOnInit(): void {
    this.form = this.parse(this.orderItem.ingredients);
  }

  getIngredients(): AbstractControl[] {
    return this.form.controls;
  }

  save(): void {
    this.orderItem.ingredients = this.form.value;
    this.updated.emit(this.orderItem);
  }

  close(): void {
    this.updated.emit(null);
  }

  private parse(ingredients: OrderIngredient[]): FormArray {
    const list = new FormArray([]);
    for (const item of ingredients)
      list.push(new FormGroup({
        'quantity': new FormControl(item.quantity, [Validators.required, Validators.pattern(/^[1-9]+[0-9]*$/)]),
        'ingredient': new FormGroup({
          'id': new FormControl(item.ingredient.id),
          'name': new FormControl(item.ingredient.name)
        })
      }));
    return list;
  }
}
