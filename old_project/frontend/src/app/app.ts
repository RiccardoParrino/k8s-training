import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Order } from "./order/order";
import { Product } from "./product/product";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Order, Product],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('frontend');
}
