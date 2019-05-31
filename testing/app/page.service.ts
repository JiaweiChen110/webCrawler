import { Injectable } from '@angular/core';
import { Page } from './page';
// import { Headers, Http, Response } from '@angular/http';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class PageService {
  private baseUrl = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getPages(query: string="UCR"):  Observable<Page[]> {
    return this.http.get<Page[]>(this.baseUrl + '/api/pages?query=' + query);
  }
}