import { Component } from '@angular/core';
import { ProductService } from '../service/product-service';

@Component({
  selector: 'app-product',
  imports: [],
  templateUrl: './product.html',
  styleUrl: './product.css'
})
export class Product {

  constructor (private productService:ProductService) {}

  findAllProduct() : void {
    this.productService.findAll().subscribe( data => console.log(data) );
  }

}
