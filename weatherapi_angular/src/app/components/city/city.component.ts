import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { WeatherService } from '../../services/weather.service';

@Component({
  selector: 'app-city',
  templateUrl: './city.component.html',
  styleUrls: ['./city.component.css']
})
export class CityComponent implements OnInit {
  private url: any;
  private city: any;
  private img: any;

  constructor(private route:ActivatedRoute, private router: Router, private ws: WeatherService) { 
    
  }

  ngOnInit() {
    this.findCity();
    this.findImage();
  }
  findCity() {
    this.route.params
    .subscribe(data=> {
        this.url = data;
        this.ws.findCity(this.url, (data2)=>{
          this.city = data2;
        })
      }
    );
  }
  findImage() {
    this.route.params
    .subscribe(data=> {
      if(data.city == 'seattle') {
        this.img = 'https://images.pexels.com/photos/944636/pexels-photo-944636.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=350';
      }
      if(data.city == 'sanjose') {
        this.img = 'https://images.pexels.com/photos/196642/pexels-photo-196642.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940';
      }
      if(data.city == 'burbank') {
        this.img = 'https://images.pexels.com/photos/305256/pexels-photo-305256.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940';
      }
      if(data.city == 'dallas') {
        this.img = 'https://images.pexels.com/photos/280193/pexels-photo-280193.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940';
      }
      if(data.city == 'dc') {
        this.img = 'https://images.pexels.com/photos/208702/pexels-photo-208702.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=350';
      }
      if(data.city == 'chicago') {
        this.img = 'https://images.pexels.com/photos/167200/pexels-photo-167200.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940';
      }
    });
  }
}
