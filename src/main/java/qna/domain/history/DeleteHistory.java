package qna.domain.history;

import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.*;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import qna.domain.answer.Answer;
import qna.domain.common.ContentType;
import qna.domain.question.Question;
import qna.domain.user.User;

@EntityListeners(AuditingEntityListener.class)
@Entity
public class DeleteHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private ContentType contentType;
    private Long contentId;
    @CreatedDate
    private LocalDateTime createDate;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delete_by_id")
    private User deletedByUser;

    public DeleteHistory(ContentType contentType, Long contentId, User deletedByUser, LocalDateTime createDate) {
        this.contentType = contentType;
        this.contentId = contentId;
        this.deletedByUser = deletedByUser;
        this.createDate = createDate;
    }

    protected DeleteHistory() {

    }

    public static DeleteHistory createQuestionDeleteHistory(Question question, LocalDateTime now) {
        return new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), now);
    }

    public static DeleteHistory createAnswerDeleteHistory(Answer answer, LocalDateTime now) {
        return new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), now);
    }

    public Long getId() {
        return id;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public Long getContentId() {
        return contentId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public User getDeletedByUser() {
        return deletedByUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DeleteHistory that = (DeleteHistory) o;
        return Objects.equals(id, that.id) &&
                contentType == that.contentType &&
                Objects.equals(contentId, that.contentId) &&
                Objects.equals(deletedByUser, that.deletedByUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, contentType, contentId, deletedByUser);
    }

    @Override
    public String toString() {
        return "DeleteHistory{" +
                "id=" + id +
                ", contentType=" + contentType +
                ", contentId=" + contentId +
                ", createDate=" + createDate +
                '}';
    }
}
