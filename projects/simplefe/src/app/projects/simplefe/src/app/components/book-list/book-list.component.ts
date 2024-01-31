import { Component, OnInit } from '@angular/core';
import { Book } from '../../models/book.model';
import { BookService } from '../../services/book.service';
import { BookDetailsComponent } from '../book-details/book-details.component';

@Component({
  selector: 'app-book-list',
  standalone: true,
  imports: [BookDetailsComponent],
  templateUrl: './book-list.component.html',
  styleUrl: './book-list.component.scss'
})
export class BookListComponent implements OnInit {
  books?: Book[];
  currentBook: Book = {};
  currentIndex = -1;
  title = '';

  constructor(private bookService: BookService) { }

  ngOnInit(): void {
    this.loadBooks();
  }

  loadBooks(): void {
    this.bookService.getAll()
      .subscribe({
        next: (data: Book[]) => {
          this.books = data;
          console.log(data);
        },
        error: (error: any) => {
          console.log(error);
        }
      });
  }

  refreshList(): void {
    this.loadBooks();
    this.currentIndex = -1;
    this.currentBook = {};
  }

  setActiveBook(book: Book, index: number): void {
    // console.log("book", book);
    this.currentBook = book;
    this.currentIndex = index;
  }
}
