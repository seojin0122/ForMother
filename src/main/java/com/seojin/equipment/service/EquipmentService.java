package com.seojin.equipment.service;

import java.util.List;

import com.seojin.equipment.dao.EquipmentDao;
import com.seojin.equipment.dto.Equipment;

public class EquipmentService {

	private static EquipmentService instance = new EquipmentService();
	private EquipmentDao equipmentDao = EquipmentDao.getInstance();

	private EquipmentService() {
	}

	public static EquipmentService getInstance() {
		return instance;
	}

	public void registEquipment(Equipment equipment) throws Exception {
		validateEquipment(equipment);

		if (equipmentDao.existsByStickerNo(equipment.getStickerNo())) {
			throw new Exception("이미 존재하는 스티커번호입니다.");
		}

		equipmentDao.insert(equipment);
	}

	public List<Equipment> getEquipmentList() {
		return equipmentDao.selectAll();
	}

	public Equipment getEquipmentDetail(String stickerNo) {
		if (stickerNo == null || stickerNo.trim().isEmpty()) {
			return null;
		}
		return equipmentDao.selectByStickerNo(stickerNo);
	}

	public void modifyEquipment(Equipment equipment) throws Exception {
		validateEquipment(equipment);

		Equipment origin = equipmentDao.selectByStickerNo(equipment.getStickerNo());
		if (origin == null) {
			throw new Exception("존재하지 않는 장비입니다.");
		}

		equipment.setEno(origin.getEno());
		equipmentDao.update(equipment);
	}

	public void removeEquipment(String stickerNo) throws Exception {
		if (stickerNo == null || stickerNo.trim().isEmpty()) {
			throw new Exception("스티커번호가 없습니다.");
		}

		Equipment origin = equipmentDao.selectByStickerNo(stickerNo);
		if (origin == null) {
			throw new Exception("삭제할 장비가 존재하지 않습니다.");
		}

		equipmentDao.delete(stickerNo);
	}

	public List<Equipment> searchByProductName(String productName) {
		if (productName == null || productName.trim().isEmpty()) {
			return equipmentDao.selectAll();
		}
		return equipmentDao.searchByProductName(productName);
	}

	public List<Equipment> searchByStatus(String status) {
		if (status == null || status.trim().isEmpty()) {
			return equipmentDao.selectAll();
		}
		return equipmentDao.searchByStatus(status);
	}

	private void validateEquipment(Equipment equipment) throws Exception {
		if (equipment == null) {
			throw new Exception("장비 정보가 없습니다.");
		}
		if (equipment.getStickerNo() == null || equipment.getStickerNo().trim().isEmpty()) {
			throw new Exception("스티커번호는 필수입니다.");
		}
		if (equipment.getProductName() == null || equipment.getProductName().trim().isEmpty()) {
			throw new Exception("품명은 필수입니다.");
		}
	}
}