import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OrderEmptyComponent } from './order/order-empty/order-empty.component';
import { OrderEditComponent } from './order/order-edit/order-edit.component';
import { OrderEditResolver } from './order/order-edit/order-edit.resolver';

const routes: Routes = [
  { path: '', redirectTo: 'order', pathMatch: 'full' },
  {
    path: 'order',
    children: [
      { path: '', component: OrderEmptyComponent, pathMatch: 'full' },
      {
        path: 'new',
        component: OrderEditComponent,
        resolve: { burgers: OrderEditResolver }
      },
      {
        path: ':id',
        component: OrderEditComponent,
        resolve: { burgers: OrderEditResolver }
      }
    ]
  },
  { path: '**', redirectTo: 'order', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
