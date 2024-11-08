import { TestBed } from '@angular/core/testing';

import { RecycledProductService } from './recycled-product.service';

describe('RecycledProductService', () => {
  let service: RecycledProductService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RecycledProductService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
