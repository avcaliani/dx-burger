import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { Response } from '../common/response.model';
import { environment } from 'src/environments/environment';
import { Order } from './order.model';

@Injectable({providedIn: 'root'})
export class OrderService {

  newOrder = new Subject<void>();
  constructor(private http: HttpClient) { }

  getBurgers(): Observable<Response> {
    return this.http.get<Response>(`${environment.api}/burger`);
  }

  getOrders(): Observable<Response> {
    return this.http.get<Response>(`${environment.api}/order`);
  }

  getOrder(id: number): Observable<Response> {
    return this.http.get<Response>(`${environment.api}/order/${id}`);
  }

  calculatePrice(order: Order): Observable<Response> {
    return this.http.post<Response>(`${environment.api}/order/price`, order);
  }

  save(order: Order): Observable<Response> {
    return this.http.post<Response>(`${environment.api}/order/`, order);
  }

  notify(): void {
    this.newOrder.next();
  }
}
