package com.project.aim.matching.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.aim.matching.dto.MatchingDTO;
import com.project.aim.matching.entity.Matching;
import com.project.aim.matching.service.MatchingService;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "http://localhost:3000")
public class MatchingController {

	private final MatchingService matchingService;

	public MatchingController(MatchingService matchingService) {
		this.matchingService = matchingService;
	}

	/* 매칭 리스트 */
	@GetMapping("/matching/mypage/{userEmail}")
	public ResponseEntity<List<MatchingDTO>> getMatchingsByUserEmail(@PathVariable String userEmail) {
		List<MatchingDTO> matchings = matchingService.getMatchingsByUserEmail(userEmail);
		return ResponseEntity.ok(matchings);
	}

	@GetMapping("/matching/mypage/{userEmail}/{matchingIdx}")
	public ResponseEntity<MatchingDTO> getMatchingByUserEmailAndIndex(@PathVariable String userEmail,
			@PathVariable Long matchingIdx) {
		MatchingDTO matching = matchingService.getMatchingByUserEmailAndIndex(userEmail, matchingIdx);
		if (matching != null) {
			return ResponseEntity.ok(matching);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/* 매칭 폼 업로드 */
	@PostMapping("/matching/upload")
	public ResponseEntity<MatchingDTO> uploadMatchingForm(@RequestBody MatchingDTO matchingDTO) {
		MatchingDTO uploadedMatching = matchingService.uploadMatchingForm(matchingDTO);
		return ResponseEntity.ok(uploadedMatching);
	}

	/* 상대방에게 요청 */
	@PostMapping("/matching/send")
	public ResponseEntity<Void> sendMatchingForm(@RequestBody MatchingDTO matchingDTO,
			@RequestParam("recipientEmail") String recipientEmail) {
		matchingService.sendMatchingForm(matchingDTO, recipientEmail);
		return ResponseEntity.ok().build();
	}
	
	/* 매칭 상태 업데이트 */
    @PutMapping("/matching/mypage/{userEmail}/{matchingIdx}/status")
    public ResponseEntity<Void> updateMatchingStatus(@PathVariable Integer matchingIdx,
            @RequestParam("status") int status) {
        matchingService.updateMatchingStatus(matchingIdx, status);
        return ResponseEntity.ok().build();
    }
}