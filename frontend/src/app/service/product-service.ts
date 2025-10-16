import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../product/product';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor (private http:HttpClient) {}
  
  findAll() : Observable<Array<Product>> {
    return this.http.get<Array<Product>>("http://localhost:8080/api/product/findAll");
  }

}
