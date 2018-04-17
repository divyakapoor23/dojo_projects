import { Injectable } from '@angular/core';
import {HttpClient } from "@angular/common/http";

@Injectable()
export class WeatherService {
  private key: any;
  private url: any;
  constructor(private http: HttpClient) { 
    this.key = "920e33015f0d774d8778e2e6eecb0ec4"; //my openweathermap api key

  }
  
  findCity(params, cb) {
    if(params.city == 'dc') {
      this.url = 'http://api.openweathermap.org/data/2.5/weather?id=4138106&units=imperial&APPID='+this.key;
    }
    else if(params.city == 'sanjose') {
      this.url = 'http://api.openweathermap.org/data/2.5/weather?id=5393021&units=imperial&APPID='+this.key;
    }
    else {
      this.url = 'http://api.openweathermap.org/data/2.5/weather?q='+params.city+'&units=imperial&APPID='+this.key;
    }

    this.http.get(this.url)
    .subscribe(data=>cb(data));
  }

}
