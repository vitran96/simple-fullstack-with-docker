import { Component, Input, OnInit } from '@angular/core';
import { Book } from '../../models/book.model';
import { BookService } from '../../services/book.service';

@Component({
  selector: 'app-book-details',
  standalone: true,
  imports: [],
  templateUrl: './book-details.component.html',
  styleUrl: './book-details.component.scss'
})
export class BookDetailsComponent implements OnInit {
  @Input() viewMode = false;
  @Input() book: Book = {};

  constructor(
    private bookService: BookService,
  ) {}

  ngOnInit(): void {
    if (!this.viewMode) {
      this.getBook(this.book);
    }
  }

  getBook(book: Book) {
    this.bookService.get(book.id)
      .subscribe({
        next: (data: Book) => {
          this.book = data;
          console.log(data);
        },
        error: (error: any) => {
          console.log(error);
        }
      })
  }

}
