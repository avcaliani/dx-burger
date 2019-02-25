import { Component, OnInit, OnDestroy } from '@angular/core';
import { map, catchError } from 'rxjs/operators';
import { OrderService } from '../order.service';
import { Order } from '../order.model';
import { Response } from 'src/app/common/response.model';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-order-list',
  templateUrl: './order-list.component.html',
  styleUrls: ['./order-list.component.scss']
})
export class OrderListComponent implements OnInit, OnDestroy {

  orders: Order[] = [];
  private newOrderSubs: Subscription;
  constructor(
    private service: OrderService
  ) { }

  ngOnInit(): void {
    this.update();
    this.newOrderSubs = this.service.newOrder.subscribe(
      () => this.update()
    );
  }

  ngOnDestroy(): void {
    this.newOrderSubs.unsubscribe();
  }

  private update(): void {
    this.service.getOrders().subscribe(
      (response: Response) => this.orders = response.data,
      (error: any) => console.error('Error to retrieve orders.', error)
    );
  }
}
