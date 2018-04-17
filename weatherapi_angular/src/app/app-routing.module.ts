import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CityComponent } from './components/city/city.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: CityComponent },
  { path: ":city", component: CityComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

// Default (root) url - 'http://localhost:4200/' should immediately redirect us to your local Dojo url. 
// Seattle, WA - 'http://localhost:4200/seattle'
// San Jose, CA - 'http://localhost:4200/sanjose'
// Burbank, CA - 'http://localhost:4200/burbank'
// Dallas, TX - 'http://localhost:4200/dallas'
// Washington D.C. - 'http://localhost:4200/dc'
// Chicago, IL - 'http://localhost:4200/chicago'