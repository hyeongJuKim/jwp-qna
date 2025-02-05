package qna.domain.history;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static qna.domain.history.DeleteHistoryTest.createQuestiDelete;

@DataJpaTest
public class DeleteHistoryRepositoryTest {
    @Autowired
    DeleteHistoryRepository deleteHistoryRepository;

    @Test
    @DisplayName("삭제이력이 저장되는지 테스트한다")
    void saveQuestiDeleteHistory() {
        DeleteHistory questiDeleteHistory = deleteHistoryRepository.save(createQuestiDelete());
        assertAll(
                () -> assertThat(questiDeleteHistory.getId()).isNotNull(),
                () -> assertThat(questiDeleteHistory.getContentId()).isEqualTo(questiDeleteHistory.getContentId()),
                () -> assertThat(questiDeleteHistory.getContentType()).isEqualTo(questiDeleteHistory.getContentType()),
                () -> assertThat(questiDeleteHistory.getCreateDate()).isEqualTo(questiDeleteHistory.getCreateDate()),
                () -> assertThat(questiDeleteHistory.getDeletedByUser()).isEqualTo(
                        questiDeleteHistory.getDeletedByUser())
        );

    }

    @Test
    @DisplayName("삭제이력이 삭제되는지 테스트한다")
    void deleteHistoryRepository() {
        DeleteHistory save = deleteHistoryRepository.save(createQuestiDelete());
        deleteHistoryRepository.deleteById(save.getId());
        Optional<DeleteHistory> getHistoryRepository = deleteHistoryRepository.findById(save.getId());
        assertThat(getHistoryRepository.isPresent()).isFalse();
    }

}
