package tranuytrieuvi.tech.simpleweb;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BookRepo extends JpaRepository<Book, UUID> {
}
