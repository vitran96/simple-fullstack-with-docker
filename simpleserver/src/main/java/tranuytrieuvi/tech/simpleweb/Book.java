package tranuytrieuvi.tech.simpleweb;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "books")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Column
    private UUID id;
    @Column
    private String title;
    @Column
    private String author;
    @Column
    private String description;
}
