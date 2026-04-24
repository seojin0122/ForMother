package com.seojin.equipment.service;

import java.util.List;

import com.seojin.equipment.dao.EquipmentDao;
import com.seojin.equipment.dao.EquipmentHistoryDao;
import com.seojin.equipment.dto.Equipment;
import com.seojin.equipment.dto.EquipmentHistory;

public class EquipmentHistoryService {

	private static EquipmentHistoryService instance = new EquipmentHistoryService();
	private EquipmentHistoryDao historyDao = EquipmentHistoryDao.getInstance();
	private EquipmentDao equipmentDao = EquipmentDao.getInstance();

	private EquipmentHistoryService() {
	}

	public static EquipmentHistoryService getInstance() {
		return instance;
	}

	public void registHistory(EquipmentHistory history) throws Exception {
		validateHistory(history);

		Equipment equipment = equipmentDao.selectByStickerNo(history.getStickerNo());
		if (equipment == null) {
			throw new Exception("이력을 등록할 장비가 존재하지 않습니다.");
		}

		historyDao.insert(history);
		updateEquipmentStatus(equipment, history);
	}

	public List<EquipmentHistory> getHistoryList() {
		return historyDao.selectAll();
	}

	public List<EquipmentHistory> getHistoryByStickerNo(String stickerNo) {
		if (stickerNo == null || stickerNo.trim().isEmpty()) {
			return historyDao.selectAll();
		}
		return historyDao.selectByStickerNo(stickerNo);
	}

	private void validateHistory(EquipmentHistory history) throws Exception {
		if (history == null) {
			throw new Exception("이력 정보가 없습니다.");
		}
		if (history.getHistoryDate() == null || history.getHistoryDate().trim().isEmpty()) {
			throw new Exception("날짜는 필수입니다.");
		}
		if (history.getStickerNo() == null || history.getStickerNo().trim().isEmpty()) {
			throw new Exception("스티커번호는 필수입니다.");
		}
		if (history.getActionType() == null || history.getActionType().trim().isEmpty()) {
			throw new Exception("구분은 필수입니다.");
		}
	}

	private void updateEquipmentStatus(Equipment equipment, EquipmentHistory history) {
		String actionType = history.getActionType();

		if ("출고".equals(actionType)) {
			equipment.setStatus("출고중");
			equipment.setCurrentLocation(history.getCompanyName());
		} else if ("입고".equals(actionType)) {
			equipment.setStatus("회사보유");
			equipment.setCurrentLocation("회사창고");
		} else if ("A/S".equals(actionType)) {
			equipment.setStatus("A/S중");
			equipment.setCurrentLocation(history.getPartnerName());
		}

		equipmentDao.update(equipment);
	}
}