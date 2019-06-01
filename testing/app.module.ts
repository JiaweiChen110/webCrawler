import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { HttpModule } from '@angular/http';
import { HttpClientModule }    from '@angular/common/http';

import { ArticleService } from './article/article.service';


import { AppComponent } from './app.component';
import { ArticleComponent } from './article/article.component';



@NgModule({
  declarations: [
    AppComponent,
    ArticleComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    HttpClientModule
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }