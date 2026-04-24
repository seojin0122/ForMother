package com.seojin.equipment.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.seojin.equipment.dto.Equipment;

public class EquipmentDao {

	private static EquipmentDao instance = new EquipmentDao();
	private List<Equipment> equipments = new ArrayList<>();
	private int sequence = 1;

	private EquipmentDao() {
		equipments.add(new Equipment(sequence++, "SC-001", "쇼케이스", "2021", "서진", "회사창고", "회사보유", "정상"));
		equipments.add(new Equipment(sequence++, "ICE-002", "제빙기", "2020", "서진", "광주상회", "출고중", "점검 필요"));
		equipments.add(new Equipment(sequence++, "REF-003", "냉장고", "2019", "서진", "수리업체", "A/S중", "냉각 문제"));
	}

	public static EquipmentDao getInstance() {
		return instance;
	}

	public void insert(Equipment equipment) {
		equipment.setEno(sequence++);
		equipments.add(equipment);
	}

	public List<Equipment> selectAll() {
		return new ArrayList<>(equipments);
	}

	public Equipment selectByStickerNo(String stickerNo) {
		for (Equipment equipment : equipments) {
			if (equipment.getStickerNo().equals(stickerNo)) {
				return equipment;
			}
		}
		return null;
	}

	public void update(Equipment equipment) {
		for (int i = 0; i < equipments.size(); i++) {
			if (equipments.get(i).getStickerNo().equals(equipment.getStickerNo())) {
				equipments.set(i, equipment);
				return;
			}
		}
	}

	public void delete(String stickerNo) {
		Iterator<Equipment> iterator = equipments.iterator();
		while (iterator.hasNext()) {
			Equipment equipment = iterator.next();
			if (equipment.getStickerNo().equals(stickerNo)) {
				iterator.remove();
				return;
			}
		}
	}
	
	public boolean existsByStickerNo(String stickerNo) {
		return selectByStickerNo(stickerNo) != null;
	}

	public List<Equipment> searchByProductName(String productName) {
		List<Equipment> result = new ArrayList<>();
		for (Equipment equipment : equipments) {
			if (equipment.getProductName() != null &&
				equipment.getProductName().contains(productName)) {
				result.add(equipment);
			}
		}
		return result;
	}

	public List<Equipment> searchByStatus(String status) {
		List<Equipment> result = new ArrayList<>();
		for (Equipment equipment : equipments) {
			if (equipment.getStatus() != null &&
				equipment.getStatus().equals(status)) {
				result.add(equipment);
			}
		}
		return result;
	}
	
	public List<Equipment> searchByStickerNo(String stickerNo) {
		List<Equipment> result = new ArrayList<>();
		for (Equipment equipment : equipments) {
			if (equipment.getStickerNo() != null &&
				equipment.getStickerNo().contains(stickerNo)) {
				result.add(equipment);
			}
		}
		return result;
	}
}