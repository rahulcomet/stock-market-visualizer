import { Component } from '@angular/core';
import { CommonModule, NgFor, NgIf } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { StockChartComponent } from './stock-chart/stock-chart.component';
import { NewsService, NewsItem } from './news.service';
import { finalize } from 'rxjs/operators';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    StockChartComponent,
    NgIf,
    NgFor
  ],
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  // Form and state properties
  ticker: string = 'AAPL'; // Default ticker
  lastSearchedTicker: string = '';
  news: NewsItem[] = [];
  loading: boolean = false;
  error: string | null = null;

  constructor(private newsService: NewsService) {
    this.loadTop3(); // Load news for the default ticker on init
  }

  loadTop3(): void {
    if (!this.ticker?.trim()) {
      return; // Button should be disabled, but as a safeguard.
    }

    this.loading = true;
    this.error = null;

    this.newsService.getTop3(this.ticker.toUpperCase())
      .pipe(finalize(() => this.loading = false))
      .subscribe({
        next: (data) => {
          this.news = data;
          this.lastSearchedTicker = this.ticker.toUpperCase();
          if (data.length === 0) {
            this.error = `No news found for ${this.lastSearchedTicker}. Try another ticker.`;
          }
        },
        error: (err) => {
          console.error(err);
          this.news = []; // Clear old news on error
          this.error = 'Failed to fetch news. Please check the ticker and try again.';
        }
      });
  }
}
