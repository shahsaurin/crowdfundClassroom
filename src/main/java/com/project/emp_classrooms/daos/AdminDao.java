package com.project.emp_classrooms.daos;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.emp_classrooms.entities.Admin;
import com.project.emp_classrooms.repositories.AdminRepository;

@Component
public class AdminDao {

	@Autowired
	AdminRepository adminRepository;
	

//	BASIC CRUD:
	
	public Admin createAdmin(Admin admin) {
		return adminRepository.save(admin);
	}
	
	public List<Admin> findAllAdmins() {
		return (List<Admin>) adminRepository.findAll();
	}
	
	public Admin findAdminById(int adminId) {
		Optional<Admin> optAdmin = adminRepository.findById(adminId);
		if(optAdmin.isPresent()) {
			return optAdmin.get();
		}
		return null;
	}
	
	public Admin findAdminByCredentials(String username, String password) throws Exception {
		List<Admin> admins = (List<Admin>) adminRepository.findAdminByCredentials(username, password);
		if(admins.isEmpty()) {
			throw new Exception("Admin not found!");
		}
		return admins.get(0);
	}
	
	public void deleteAllAdmins() {
		adminRepository.deleteAll();
	}
	
	public void test() {
		
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword("admin");
		admin.setSuperAdmin(true);
		admin = createAdmin(admin);
		
	}

}
