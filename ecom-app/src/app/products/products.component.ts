import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {error} from "@angular/compiler-cli/src/transformers/util";

// @ts-ignore
// @ts-ignore
@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{

  products:any

  constructor(private http:HttpClient) {
  }

  ngOnInit():void {
    this.http.get("http://localhost:8888/INVENTORY-SERVICE/products").subscribe({
      next:(data)=>{
        this.products=data;
      },
      error:(err)=>{}

    })
  }

}
