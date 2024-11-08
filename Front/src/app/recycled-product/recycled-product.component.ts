import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { recycledProduct } from '../models/recycledProduct';
import { RecycledProductService } from '../service/recycled-product.service';

@Component({
  selector: 'app-recycled-product',
  templateUrl: './recycled-product.component.html',
  styleUrls: ['./recycled-product.component.css']
})
export class RecycledProductComponent implements OnInit {
  products: recycledProduct[] = [];  // List of recycled products
  newProduct: recycledProduct = new recycledProduct();  // New product to add
  selectedProduct: recycledProduct | null = null;  // Product selected for editing

  constructor(
    private recycledProductService: RecycledProductService,
    private cdr: ChangeDetectorRef // Inject ChangeDetectorRef
  ) { }

  ngOnInit(): void {
    this.getProducts();  // Fetch products when the component initializes
  }
  getProductsFiltre(): void {
    this.recycledProductService.getProductsByPercentage().subscribe({
      next: (products) => {
        this.products = products;  // Update the products list with filtered data
        console.log('Filtered Products:', this.products);  // Debugging statement
      },
      error: (error) => {
        console.error('Error fetching filtered products:', error);
      }
    });
  }
  
  // Fetch recycled products
  getProducts(): void {
    this.recycledProductService.getProducts().subscribe({
      next: (products) => {
        this.products = products;
        console.log('Products fetched:', this.products);  // Debugging statement
        this.cdr.detectChanges(); // Manually trigger change detection
      },
      error: (error) => {
        console.error('Error fetching products:', error);
      }
    });
  }

  // Add a new recycled product
  addProduct(): void {
    this.recycledProductService.addProduct(this.newProduct).subscribe({
      next: (response) => {
        console.log('Recycled product added:', response);
        this.getProducts();  // Refresh the product list
        this.newProduct = new recycledProduct();  // Reset the form
      },
      error: (error) => {
        console.error('Error adding product:', error);
      }
    });
  }

  // Delete a recycled product using productUri
  deleteProduct(productUri: string): void {
    this.recycledProductService.deleteProduct(productUri).subscribe({
      next: (response) => {
        console.log('Recycled product deleted:', response);
        this.getProducts();  // Refresh the product list
      },
      error: (error) => {
        console.error('Error deleting product:', error);
      }
    });
  }
  updateProduct(): void {
    if (this.selectedProduct) {
      this.recycledProductService.updateProduct(this.selectedProduct).subscribe({
        next: (response) => {
          console.log('Recycled product updated:', response);
          this.getProducts();  // Refresh the product list
          this.selectedProduct = null;  // Clear the selected product
        },
        error: (error) => {
          console.error('Error updating product:', error);
        }
      });
    }
  }
  
  
  
  // Select a product for editing
  editProduct(product: recycledProduct): void {
    this.selectedProduct = { ...product };  // Clone the product for editing
  }

  // Cancel editing and reset the form
  cancelEdit(): void {
    this.selectedProduct = null;  // Reset the selected product
  }
}

