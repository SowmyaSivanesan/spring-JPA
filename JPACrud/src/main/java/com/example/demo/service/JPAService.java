package com.example.demo.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.jparepo.JPARepository;
import com.example.demo.model.JPAModel;
import com.example.demo.model.Response;

@Service
public class JPAService {

	@PersistenceContext
	private EntityManager entityManager;
	

	Response rsp = new Response();

	@Autowired
	private JPARepository jparepo;

	public Response createUser(JPAModel values) {

		try {

			String uuid = UUID.randomUUID().toString();
			values.setsNo(uuid);
			values.setCreatedBy(uuid);
			values.setUpdatedBy(uuid);
			
			Date date = new Date(Calendar.getInstance().getTime().getTime());
			
			values.setCreatedDate(date);
			values.setUpdatedDate(date);

			jparepo.save(values);

			rsp.setData("User created successfully");
			rsp.setResponseCode(200);
			rsp.setRespondMsg("Success");

		} catch (Exception e) {
			e.printStackTrace();

			rsp.setData("Cannot create successfully");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("Error");

		}

		return rsp;
	}

	@SuppressWarnings("unchecked")
	public Response getAllUser() {

		try {

			List<JPAModel> list = jparepo.findAll();

			JSONArray jsonArry = new JSONArray();

			for (JPAModel values : list) {

				JSONObject jsonObj = new JSONObject();
				jsonObj.put("sNo", values.getsNo());
				jsonObj.put("firstName", values.getFirstName());

				jsonArry.add(jsonObj);

			}
			rsp.setjData(jsonArry);

			rsp.setData("User created successfully");
			rsp.setResponseCode(200);
			rsp.setRespondMsg("Success");

		} catch (Exception e) {

			e.printStackTrace();

			rsp.setData("Cannot create successfully");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("Error");

		}
		rsp.setData("");

		return rsp;
	}

	public Response updateUser(JPAModel values) {

		try {

			Optional<JPAModel> optObj = jparepo.findById(values.getsNo());

			if (optObj.isPresent()) {

				JPAModel update = optObj.get();
				update.setEmail(values.getEmail());

				jparepo.save(update);

				rsp.setData("User updated successfully");
				rsp.setResponseCode(200);
				rsp.setRespondMsg("Success");

			} else {

				rsp.setData("No such user");
				rsp.setResponseCode(500);
				rsp.setRespondMsg("Error");

			}

		} catch (Exception e) {
			e.printStackTrace();

			rsp.setData("Failed to update user");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("Error");

		}

		return rsp;
	}

	public Response getOneUser() {

		return rsp;
	}

	public Response deleteUser(String sNo) {

		try {

			jparepo.deleteById(sNo);

			rsp.setData("User deleted successfully!");
			rsp.setResponseCode(200);
			rsp.setRespondMsg("Success");
		} catch (Exception e) {
			e.printStackTrace();

			rsp.setData("Failed to delete user");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("Error");
		}

		return rsp;
	}

	public Response scamUser() {

		return rsp;
	}

	public Response loginUser(String email, String pswrd) {
		
		try {
			
			Query query = entityManager.createQuery("Select u from JPAModel u where u.email = :email AND u.pswrd = :pswrd");
			query.setParameter("email", email);
			query.setParameter("pswrd", pswrd);
			
			@SuppressWarnings("unchecked")
			List<JPAModel> value = query.getResultList();
			
			String result;
			
			if (value.isEmpty()) {
				
				
				result = "No User found";
				
				
			} else {
				
				result = "Existing User";
				

			}
			
			

			rsp.setData(result);
//			rsp.setResponseCode(200);
//			rsp.setRespondMsg("Success");
			
		} catch (Exception e) {
			e.printStackTrace();
			
			
			rsp.setData("Failed to login");
			rsp.setResponseCode(500);
			rsp.setRespondMsg("Error");
			
		}
		
		

		return rsp;
	}

}
