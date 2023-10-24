import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { StockDataDTO } from './StockDataDTO';

@Injectable({
  providedIn: 'root'
})
export class StockService {

  private apiUrl = 'http://localhost:8080/api/stock';

  constructor(private http: HttpClient) { }

  getStockData(symbol: string): Observable<StockDataDTO> {
    return this.http.get<StockDataDTO>(`${this.apiUrl}?symbol=${symbol}`);
  }
}
