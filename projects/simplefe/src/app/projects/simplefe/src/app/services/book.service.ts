import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Book } from '../models/book.model';
import { environment } from './../../../../../../environments/environment';

const baseUrl = environment.baseUrl;

console.log("baseUrl", baseUrl);

@Injectable({
  providedIn: 'root'
})
export class BookService {

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get<Book[]>(`${baseUrl}/books`);
  }

  get(id: any) {
    return this.http.get<Book>(`${baseUrl}/book/${id}`);
  }

  create(data: any) {
    return this.http.post<Book>(`${baseUrl}/book`, data);
  }

  update(id: any, data: any) {
    return this.http.put<Book>(`${baseUrl}/book/${id}`, data);
  }

  delete(id: any) {
    return this.http.delete(`${baseUrl}/book/${id}`);
  }
}
