import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthorComponent } from './components/author/author.component';
import { NewAuthComponent } from './components/new-auth/new-auth.component';
import { EditAuthComponent } from './components/edit-auth/edit-auth.component';

const routes: Routes = [
  { path: '', pathMatch: 'full', component: AuthorComponent },
  { path: "new", pathMatch: 'full', component: NewAuthComponent },
  { path: "edit/:id", pathMatch: 'full', component: EditAuthComponent },
  { path: '**', redirectTo: '' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
