import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Response } from '../../common/response.model';
import { OrderService } from '../order.service';
import { Burger } from '../order.model';

@Injectable()
export class OrderEditResolver implements Resolve<Burger[]> {

  constructor(private service: OrderService) { }

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Burger[]> | Promise<Burger[]> | Burger[] {
    return this.service.getBurgers()
      .pipe(map(
        (response: Response) => <Burger[]>response.data
      )).pipe(catchError(
        (error: any) => {
          console.error('Error to retrieve burgers.', error);
          return [];
        }
      ));
  }
}
