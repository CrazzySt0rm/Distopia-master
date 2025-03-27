package noidea.Dystopia.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import noidea.Dystopia.dto.FeedbackDTO;
import noidea.Dystopia.models.Feedback;
import noidea.Dystopia.repositories.FeedbackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Transactional
    public Feedback createFeedback(FeedbackDTO feedbackDTO) {
        return feedbackRepository.save(Feedback.builder()
                .guestsFeedback(feedbackDTO.getGuestsFeedback())
                .build());
    }
    public List<Feedback> readFeedback() {
        return feedbackRepository.findAll();
    }

    public void deleteFeedback(@PathVariable(value = "id") Long id) {
        feedbackRepository.deleteById(id);
    }
}
