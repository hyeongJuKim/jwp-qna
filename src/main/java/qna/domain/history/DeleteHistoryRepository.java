package qna.domain.history;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeleteHistoryRepository extends JpaRepository<DeleteHistory, Long> {
}
