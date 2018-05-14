package br.com.web.servexpress.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.web.servexpress.model.Feedback;
import br.com.web.servexpress.repository.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	public void register(Feedback feedback) {
		this.feedbackRepository.save(feedback);
	}

	public List<Feedback> findTop5() {
		return this.feedbackRepository.findTop5();
	}

}
