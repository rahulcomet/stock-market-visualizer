import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

export interface NewsItem {
  title: string;
  url: string;
  summary: string;
  timePublished: string;
  overallSentimentLabel: string;
}

@Injectable({ providedIn: 'root' })
export class NewsService {
  private base = 'http://localhost:8080/api'; // or pull from config

  constructor(private http: HttpClient) {}

  getTop3(ticker: string) {
    return this.http.get<NewsItem[]>(`${this.base}/news/top3`, { params: { ticker } });
  }
}
