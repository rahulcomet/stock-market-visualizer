# Stock Market Visualizer

A full-stack application to visualize stock data and display recent market news with sentiment analysis using a Spring Boot backend and an Angular frontend. The backend fetches historical stock data from Alpha Vantage’s free API, and the frontend displays the data in an interactive chart.

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

**Clone the Repository:**
   ```bash
   git clone https://github.com/yourusername/stock-market-visualizer.git
   cd stock-market-visualizer
  ``` 

## Running Locally

1. **Start the Backend:**
   - Open a terminal, navigate to the backend folder:
     ```bash
     cd backend
     ```
   - Run the Spring Boot application:
     ```bash
     ./mvnw spring-boot:run
     ```
   - Verify the backend is running by visiting:
     ```
     http://localhost:8080/api/hello
     ```
     (This should display a confirmation message.)

2. **Start the Frontend:**
   - Open a separate terminal, navigate to the frontend folder:
     ```bash
     cd frontend
     ```
   - Install dependencies (if not done already):
     ```bash
     npm install
     ```
   - Run the Angular application:
     ```bash
     ng serve
     ```
   - Open your browser at:
     ```
     http://localhost:4200
     ```

3. **Interacting with the Application:**
   - On the frontend page, enter a stock symbol (e.g., IBM, SPY) in the input field.
   - Click the "Fetch Data" button to update the chart with data fetched from the backend.
