package com.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.domain.CustomerDTO;
import com.spring.domain.PremiereDAO;
import com.spring.domain.PremiereDTO;
import com.spring.domain.PremiereWinDTO;
import com.spring.domain.PremiereWinListDTO;

@Service
public class AjaxPremiereService {
	
	PremiereDAO dao;
	
	@Autowired
	public void setDao(PremiereDAO dao) {
		this.dao = dao;
	}
	
	public int count() {
		return dao.count();
	}
	
	public List<PremiereDTO> list(int from, int pageRows){
		return dao.select(from, pageRows);
	}
	
	public List<PremiereDTO> selectByUid(int uid){
		return dao.selectByUid(uid);
	}
	
	public int write(PremiereDTO dto) {
		return dao.insert(dto);
	}

	public int update(PremiereDTO dto) {
		return dao.update(dto);
	}
	
	@Transactional
	public int deleteByUid(int [] uids) {
		dao.deleteApplyByUid(uids);
		return dao.deleteByUid(uids);
	}

	public int updateNoFile(PremiereDTO dto) {
		return dao.updateNoFile(dto);
	}

	// 응모정보 넣기 
	@Transactional
	public int apply(int prUid, String nickname, String email) {
		int cusUid = dao.findUidById(nickname);
		return dao.apply(prUid, cusUid, email);
	}

	// 응모한 아이디인지 확인하기 
	public int chkId(int prUid, String nickname) {
		return dao.chkId(prUid, nickname);
	}
	
	// 응모된 계정 추첨 -> 당첨자 닉네임 리턴 
	public List<PremiereWinDTO> selectWin(int prUid, int count){
		System.out.println("서비스 넘어옴");
		return dao.selectWin(prUid , count);
	}

	// 시사회 제목, uid -> select box
	public List<PremiereDTO> getTitle() {
		return dao.getTitle();
	}
	
	// 당첨자 저장 완료
	@Transactional
	public int updateBool(int prUid, String email, String nicknames){
		dao.updateBool(prUid, email);
		String title = dao.applyTitle(prUid);
		System.out.println(title);
		String content = "빠짐 없이 꼭 참석해주세요.<br>"
				+ "신청 인원이 많아서 전원 1매씩으로 발표합니다.<br>"
				+ "표가 더 필요한 분들은 표 나눔 게시판 통해서, 부득이하게 못가게 된 분의 표를 나눔 받으세요.<br>"
				+ "코로나 확산 방지 문제로 인해 좌석 선택 불가.<br>"
				+ "2인석으로 타인과 자리가 붙어있을 수도 있습니다.<br>"
				+ "입장전 휴대폰은 밀봉 조치됩니다.<br>"
				+ "1인1매씩<br><br>"
				+ "<div style='color:red'><당첨자 닉네임><br>";
		content += nicknames + "</div>";
		content += "<br><br>※당첨된 분들은 관람 후 자유게시판 등에 후기 등 꼭 남겨주세요..<br>"
				+ "※신규 가입자 분들은 게시판 활동 꾸준히 하시면 다른 시사회 당첨 확률도 올라가니 열심히 활동해주시기 바랍니다.<br>"
				+ "게시판 활동 없이 시사회 신청만 하시는 분들은 안 뽑습니다.<br>"
				+ "또한 시사회 상습 불참자에게 패널티 적용하니 꼭 참석해주세요.<br>"
				+ "1인 1매 당첨자 티켓은 본인 것만 받을 수 있습니다(다른 회원 티켓까지 대리 수령을 요구하면 안 됩니다.)<br>"
				+ "부득이 참석 못하여 양도할 경우 양도 받은 사람은 해당 닉네임 티켓만 수령 가능합니다. 다른 사람 티켓까지 함께 수령할 수는 없습니다.<br>"
				+ "※ 티켓은 [당첨매체/성함/본인 확인용 비밀번호] 확인 후 선착순 배부되며, 좌석 선택은 절대 불가합니다. <br>"
				+ "※ 티켓은 티켓 배부처에서 오후 6시 10분부터 상영전까지 진행 될 예정입니다. (단, 현장이 혼잡할 시 예정된 시간보다 다소 일찍 진행될 수 있습니다.) <br>"
				+ "※ 현장에서 코로나 방역지침 및 보안서약서 관련 문서 작성이 이루어질 예정으로, 늦어도 영화 시작 20분 전까지 현장 도착하시어 19시까지 모두 영화관 입장 부탁드리겠습니다.<br><br>"
				+ "[코로나 방역지침 안내]<br>"
				+ "1. 당일 현장에서 티켓 배부 전 코로나 19 확산 방지 지침 및 권고에 따라 시사회에 참석하시는 모든 분들을 대상으로 체온 측정 및 문진표 작성이 진행 될 예정입니다.<br>"
				+ "2. 체온 측정과 문진표 작성 및 티켓 배부 시 거리 간격 유지에도 협조 부탁드립니다.<br>"
				+ "3. 문진표 미작성자, 코로나 19 유증상자(체온 37.5도 이상의 발열 및 기침, 목아픔 등 호흡기 유사 증상), 마스크 미착용자는 티켓 수령이 불가합니다.<br>"
				+ "4. 철저한 방역 지침 준수를 위해 상영관 내 지정된 좌석 외 좌석으로 이동은 불가하고, 식음료 섭취가 불가하며 반드시 마스크 착용을 부탁드립니다.<br>"
				+ "5. 보안상 상영관 입장 시 휴대폰 밀봉 작업이 예정되어 있는 점 참고 부탁드리겠습니다.<br>"
				+ "6. 보안상 상영 중 나이트 비전이 진행되며, 상영관 내 촬영이 불가하므로 이 점 양해 부탁드립니다.";
		return dao.applyList(title, content);
	}
	
	public List<String> getFileName(int [] uids) {
		return dao.getFileName(uids);
	}

	// 메인페이지용
	// 시사회 최근 글 3개
	public List<PremiereDTO> getThreeRecently() {
		return dao.getThreeRecently();
	}
	
	// 당첨자 리스트 출력
	public List<PremiereWinListDTO> winList(int from, int pageRows){
		return dao.winList(from, pageRows);
	}
	
	public List<PremiereWinListDTO> selectWinView(int uid){
		return dao.selectWinView(uid);
	}
}

























