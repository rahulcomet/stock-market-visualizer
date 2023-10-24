import { Component, OnInit } from '@angular/core';
import { StockService } from '../stock.service';
import { StockDataDTO } from '../StockDataDTO';
import { ChartConfiguration, ChartType } from 'chart.js';
import { BaseChartDirective } from 'ng2-charts';

@Component({
  selector: 'app-stock-chart',
  standalone: true,
  imports: [BaseChartDirective],
  templateUrl: './stock-chart.component.html',
  styleUrls: ['./stock-chart.component.css']
})
export class StockChartComponent implements OnInit {
  stockData: StockDataDTO | undefined;

  public chartData: ChartConfiguration['data'] = {
    labels: [],
    datasets: [
      {
        data: [],
        label: 'Stock Price',
        borderColor: 'blue',
        fill: false
      }
    ]
  };

  public chartOptions: ChartConfiguration['options'] = {
    responsive: true,
    scales: {
      x: { display: true },
      y: { display: true }
    }
  };

  public chartType: ChartType = 'line';

  constructor(private stockService: StockService) {}

  ngOnInit(): void {
    this.fetchStockData('IBM');
  }

    // Uncomment this block to test with static data:
    
    /*this.chartData = {
      labels: ['2025-01-01', '2025-01-02', '2025-01-03'],
      datasets: [{
        data: [100, 105, 100],
        label: 'Stock Price',
        borderColor: 'blue',
        fill: false
      }]
    };*/
    

  fetchStockData(symbol: string): void {
    this.stockService.getStockData(symbol).subscribe({
      next: data => {
        console.log('Stock data:', data);
        this.stockData = data;
        this.parseStockData(data);
      },
      error: err => console.error('Error fetching stock data', err)
    });
  }

  parseStockData(data: StockDataDTO): void {
    if (data['Time Series (Daily)']) {
      const timeSeries = data['Time Series (Daily)'];
      const dates = Object.keys(timeSeries).sort();
      const prices = dates.map(date => parseFloat(timeSeries[date]["4. close"]));
      
      // Reassign a new object to trigger change detection
      this.chartData = {
        labels: dates,
        datasets: [
          {
            data: prices,
            label: 'Stock Price',
            borderColor: 'blue',
            fill: false
          }
        ]
      };
    }
  }
}
