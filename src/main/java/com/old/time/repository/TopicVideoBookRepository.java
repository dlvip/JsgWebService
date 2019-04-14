package com.old.time.repository;

import com.old.time.domain.TopicVideoBookEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicVideoBookRepository extends JpaRepository<TopicVideoBookEntry, Integer> {


    TopicVideoBookEntry findTopicVideoBookEntryByTopicId(Integer topicId);

    TopicVideoBookEntry findTopicVideoBookEntryByVideoId(Integer videoId);

    TopicVideoBookEntry findTopicVideoBookEntryByBookId(Integer bookId);
}
