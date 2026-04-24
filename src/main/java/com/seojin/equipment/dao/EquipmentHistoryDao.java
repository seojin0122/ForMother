package com.seojin.equipment.dao;

import java.util.ArrayList;
import java.util.List;

import com.seojin.equipment.dto.EquipmentHistory;

public class EquipmentHistoryDao {

	private static EquipmentHistoryDao instance = new EquipmentHistoryDao();
	private List<EquipmentHistory> histories = new ArrayList<>();
	private int sequence = 1;

	private EquipmentHistoryDao() {
		histories.add(new EquipmentHistory(sequence++, "2025-08-01", "광주상회", "SC-001", "쇼케이스",
				"서진", "출고", "광주상회", "신규 설치"));
		histories.add(new EquipmentHistory(sequence++, "2025-08-10", "남도주류", "ICE-002", "제빙기",
				"서진", "A/S", "광주수리센터", "얼음 생성 불량"));
		histories.add(new EquipmentHistory(sequence++, "2025-08-12", "본사", "REF-003", "냉장고",
				"서진", "입고", "본사창고", "점검 후 입고"));
	}

	public static EquipmentHistoryDao getInstance() {
		return instance;
	}

	public void insert(EquipmentHistory history) {
		history.setHno(sequence++);
		histories.add(history);
	}

	public List<EquipmentHistory> selectAll() {
		return new ArrayList<>(histories);
	}

	public List<EquipmentHistory> selectByStickerNo(String stickerNo) {
		List<EquipmentHistory> result = new ArrayList<>();
		for (EquipmentHistory history : histories) {
			if (history.getStickerNo().equals(stickerNo)) {
				result.add(history);
			}
		}
		return result;
	}

	public List<EquipmentHistory> searchByCompanyName(String companyName) {
		List<EquipmentHistory> result = new ArrayList<>();
		for (EquipmentHistory history : histories) {
			if (history.getCompanyName() != null &&
				history.getCompanyName().contains(companyName)) {
				result.add(history);
			}
		}
		return result;
	}
}