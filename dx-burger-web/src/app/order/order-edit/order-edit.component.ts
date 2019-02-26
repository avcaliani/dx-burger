import { Component, OnInit } from '@angular/core';
import { OrderService } from '../order.service';
import { Router, ActivatedRoute, Params } from '@angular/router';
import { FormGroup, FormArray, Validators, FormControl, AbstractControl } from '@angular/forms';
import { BurgerIngredient, Burger, OrderItem, Order } from '../order.model';
import { Response } from 'src/app/common/response.model';

@Component({
  selector: 'app-order-edit',
  templateUrl: './order-edit.component.html',
  styleUrls: ['./order-edit.component.scss']
})
export class OrderEditComponent implements OnInit {

  form: FormGroup;
  burgers: Burger[] = [];
  currItem: OrderItem = null;
  order: Order = null;
  reading = false;
  constructor(
    private service: OrderService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.route.params.subscribe((params: Params) => {
      this.reading = +params['id'] ? true : false;
      if (this.reading)
        this.service.getOrder(+params['id']).subscribe(
          (response: Response) => {
            if (response.error) return alert(response.error);
            this.fillForm(response.data);
          },
          err => console.log('OrderGet.Error', err)
        );
    });

    this.route.data.subscribe((data: any) => this.burgers = data['burgers']);
    this.form = new FormGroup({
      'total': new FormControl(),
      'discount': new FormControl(),
      'user': new FormGroup({
        'name': new FormControl('', Validators.required),
        'phone': new FormControl('', Validators.required),
      }),
      'items': new FormArray([])
    });
  }

  submit(): void {
    if (this.reading) return;
    this.service.save(this.form.value).subscribe(
      (response: Response) => {
        if (response.error)
          return alert(response.error);
        this.service.notify();
        this.router.navigate(['..'], { relativeTo: this.route });
      },
      err => console.error('OrderPrice.Error', err)
    );
  }

  cancel(): void {
    this.router.navigate(['..'], { relativeTo: this.route });
  }

  getItems(): AbstractControl[] {
    return (<FormArray>this.form.get('items')).controls;
  }

  deleteItem(index: number): void {
    (<FormArray>this.form.get('items')).removeAt(index);
    this.updatePrice();
  }

  editItem(index: number): void {
    this.currItem = (<FormGroup>(<FormArray>this.form.get('items')).at(index)).value;
  }

  addItem(orderItem?: OrderItem): void {
    const item = new FormGroup({
      'burger': new FormGroup({
        'id': new FormControl(orderItem ? orderItem.burger.id : this.burgers[0].id),
        'name': new FormControl(orderItem ? orderItem.burger.name : this.burgers[0].name)
      }),
      'ingredients': this.parseIngredients(orderItem ? orderItem.ingredients : this.burgers[0].ingredients)
    });
    (<FormArray>this.form.get('items')).push(item);
    this.updatePrice();
  }

  onChangeBurger(index: number, value: number): void {
    const burgerFg = (<FormGroup>(<FormArray>this.form.get('items')).at(index));
    const burger = this.getBurgerById(+value);
    burgerFg.patchValue({
      burger: {
        id: +value, name: burger.name
      }
    });
    burgerFg.setControl(
      'ingredients', this.parseIngredients(burger.ingredients)
    );
    this.updatePrice();
  }

  onUpdate(event: any): void {
    this.currItem = null;
    this.updatePrice();
  }

  private updatePrice(): void {
    if (this.reading) return;
    this.form.patchValue({ total: null, discount: null });
    this.service.calculatePrice(this.form.value).subscribe(
      (response: Response) => {
        if (response.error)
          return alert(response.error);
        this.form.patchValue({ total: response.data.total, discount: response.data.discount });
      },
      err => console.log('OrderPrice.Error', err)
    );
  }

  private getBurgerById(id: number): Burger {
    for (const item of this.burgers)
      if (item.id === id) return item;
  }

  private parseIngredients(items: BurgerIngredient[]): FormArray {
    if (!items || items.length <= 0)
      return null;

    const list = new FormArray([]);
    for (const item of items)
      list.push(new FormGroup({
        'quantity': new FormControl(item.quantity, [Validators.required, Validators.pattern(/^[0-9]+[0-9]*$/)]),
        'ingredient': new FormGroup({
          'id': new FormControl(item.ingredient.id),
          'name': new FormControl(item.ingredient.name)
        })
      }));
    return list;
  }

  private fillForm(data: Order): void {
    this.order = data;
    this.form.reset();
    this.form.setControl('items', new FormArray([]));
    this.form.patchValue(this.order);
    this.order.items.forEach((item: OrderItem) => this.addItem(item));
  }
}
