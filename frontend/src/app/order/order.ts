import { Component } from '@angular/core';
import { OrderService } from '../service/order-service';

@Component({
  selector: 'app-order',
  imports: [],
  templateUrl: './order.html',
  styleUrl: './order.css'
})
export class Order {

  constructor(private orderService:OrderService) {}

  placeOrder() {
    this.orderService.placeOrder().subscribe( data => console.log(data) );
  }

}
