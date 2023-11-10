package com.project.aim.matching.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.project.aim.matching.dto.MatchingDTO;
import com.project.aim.matching.entity.Matching;
import com.project.aim.matching.repository.MatchingRepository;

@Service
public class MatchingService {

	private final MatchingRepository matchingRepository;
	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public MatchingService(MatchingRepository matchingRepository, SimpMessagingTemplate messagingTemplate) {
		this.matchingRepository = matchingRepository;
		this.messagingTemplate = messagingTemplate;
	}

	public List<MatchingDTO> getMatchingsByUserEmail(String userEmail) {
		List<Matching> matchingList = matchingRepository.findMatchingsByUserEmail(userEmail);
		return matchingList.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	/* 매칭 폼 조회 */
	private MatchingDTO convertToDTO(Matching matching) {
		MatchingDTO matchingDTO = new MatchingDTO();
		matchingDTO.setMatchingIdx(matching.getMatchingIdx());
		matchingDTO.setMatchingTitle(matching.getMatchingTitle());
		matchingDTO.setProvider(matching.getProvider());
		matchingDTO.setUserSendName(matching.getUserSendName());
		matchingDTO.setMatchingDetails(matching.getMatchingDetails());
		matchingDTO.setMatchingGoal(matching.getMatchingGoal());
		matchingDTO.setMatchingCostsMin(matching.getMatchingCostsMin());
		matchingDTO.setMatchingCostsMax(matching.getMatchingCostsMax());
		matchingDTO.setAdMethodsDetails(matching.getAdMethodsDetails());
//		matchingDTO.setDate(matching.getDate());
		matchingDTO.setDate(LocalDate.now());
		matchingDTO.setUpdateDate(matching.getUpdateDate());
		matchingDTO.setStatus(matching.getStatus());
		matchingDTO.setUserGetEmail(matching.getUserGetEmail());
		matchingDTO.setUserSendEmail(matching.getUserSendEmail());
		return matchingDTO;
	}

	/* 매칭 폼 업로드 */
	public MatchingDTO uploadMatchingForm(MatchingDTO matchingDTO) {
		Matching matching = convertToEntity(matchingDTO);
		Matching savedMatching = matchingRepository.save(matching);
		return convertToDTO(savedMatching);
	}

	/* 매칭 폼 전달 */
	public void sendMatchingForm(MatchingDTO matchingDTO, String recipientWebSocketSessionId) {
		Matching matching = convertToEntity(matchingDTO);
		matchingRepository.save(matching);

		// 웹 소켓을 통해 매칭 폼을 전달합니다.
		messagingTemplate.convertAndSendToUser(recipientWebSocketSessionId, "/topic/matching", matchingDTO);
	}

	private Matching convertToEntity(MatchingDTO matchingDTO) {
		Matching matching = new Matching();
		matching.setMatchingTitle(matchingDTO.getMatchingTitle());
		matching.setProvider(matchingDTO.getProvider());
		matching.setUserSendName(matchingDTO.getUserSendName());
		matching.setMatchingDetails(matchingDTO.getMatchingDetails());
		matching.setMatchingGoal(matchingDTO.getMatchingGoal());
		matching.setMatchingCostsMin(matchingDTO.getMatchingCostsMin());
		matching.setMatchingCostsMax(matchingDTO.getMatchingCostsMax());
		matching.setAdMethodsDetails(matchingDTO.getAdMethodsDetails());
//		matching.setDate(matchingDTO.getDate());
		matching.setDate(LocalDate.now());
		matching.setUpdateDate(matchingDTO.getUpdateDate());
		matching.setStatus(matchingDTO.getStatus());
		matching.setUserGetEmail(matchingDTO.getUserGetEmail());
		matching.setUserSendEmail(matchingDTO.getUserSendEmail());
		return matching;
	}

	public MatchingDTO getMatchingByUserEmailAndIndex(String userEmail, Long matchingIdx) {
		Matching matching = matchingRepository.findByUserGetEmailAndMatchingIdx(userEmail, matchingIdx);
		if (matching != null) {
			return convertToDTO(matching);
		} else {
			return null;
		}
	}
	
	/* 매칭 상태 업데이트 */
	public void updateMatchingStatus(Integer matchingIdx, int newStatus) {
		Matching matching = matchingRepository.findById(matchingIdx).orElse(null);
		if (matching != null) {
			matching.setStatus(newStatus);
			matchingRepository.save(matching);
		}
	}
}