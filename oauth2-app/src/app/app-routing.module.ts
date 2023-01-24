import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthorizeComponent } from './authorize/authorize.component';
import { SingupComponent } from './singup/singup.component';

const routes: Routes = [
  { path: 'singup', component: SingupComponent, pathMatch: 'full'},
  { path: 'authorized', component: AuthorizeComponent, pathMatch: 'full'},
  { path: '', redirectTo: "singup", pathMatch: 'full'},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
