import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { StockChartComponent } from './stock-chart/stock-chart.component';


@Component({
  selector: 'app-root',
  imports: [StockChartComponent],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';
}
