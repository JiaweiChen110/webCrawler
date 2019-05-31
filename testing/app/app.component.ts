import { Component, OnInit } from '@angular/core';
import { Page } from './page';
import { PageService } from './page.service';
import { Observable, Subject } from 'rxjs';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'My Search';

  lastSearch: string;
  index:Page[] = [
    {id: 1, title:'first', body:'this is the first'},
    {id: 2, title:'second', body:'this is the second'},
    {id: 3, title:'third', body:'this is the third'}
  ];

  //constructor(private pageService: PageService) { }

  ngOnInit(){}

  search(query: string){
    this.lastSearch = query;

    //this.pageService.getPages(query)
    //    .subscribe(index => this.index = index);
  }
}