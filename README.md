# Stock Market Visualizer

A full-stack application to visualize stock data using a Spring Boot backend and an Angular frontend. The backend fetches historical stock data from Alpha Vantage’s free API, and the frontend displays the data in an interactive chart.

## Features

- **RESTful API Backend:**  
  - Built with Spring Boot.
  - Fetches daily stock data (including historical data) from Alpha Vantage utilizing the free API key. Set to fetch data from the last 100 trading days.
  - CORS configured to allow requests from the Angular frontend.

- **Dynamic Angular Frontend:**  
  - Built with Angular.
  - Displays stock data using Chart.js (via ng2-charts).
  - Allows users to input a stock symbol to fetch and display data.
  - Responsive and enhanced with basic titles and axis labels.

## Technologies Used

- **Backend:**  
  - Java, Spring Boot
  - Maven
  - Alpha Vantage API
  - CORS configuration

- **Frontend:**  
  - Angular (standalone components)
  - TypeScript
  - Chart.js / ng2-charts
  - HTML, CSS

## Getting Started

### Prerequisites

- Java 21
- Node.js (with npm)
- Git

### Setup Instructions

1. **Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/stock-market-visualizer.git
   cd stock-market-visualizer
