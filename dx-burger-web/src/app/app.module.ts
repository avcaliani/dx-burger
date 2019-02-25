import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { OrderEditResolver } from './order/order-edit/order-edit.resolver';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './common/header/header.component';
import { FooterComponent } from './common/footer/footer.component';
import { OrderListComponent } from './order/order-list/order-list.component';
import { OrderEmptyComponent } from './order/order-empty/order-empty.component';
import { OrderEditComponent } from './order/order-edit/order-edit.component';
import { BurgerIngredientsComponent } from './order/order-edit/burger-ingredients/burger-ingredients.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    OrderListComponent,
    OrderEmptyComponent,
    OrderEditComponent,
    BurgerIngredientsComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    OrderEditResolver
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
