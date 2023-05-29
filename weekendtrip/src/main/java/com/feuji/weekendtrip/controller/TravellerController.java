package com.feuji.weekendtrip.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.weekendtrip.model.City;
import com.feuji.weekendtrip.model.Passenger;
import com.feuji.weekendtrip.model.Traveller;
import com.feuji.weekendtrip.service.CityService;
import com.feuji.weekendtrip.service.PassengersService;
import com.feuji.weekendtrip.service.TravellersService;
import com.feuji.weekendtrip.serviceImplementation.EmailSenderService;
import com.feuji.weekendtrip.serviceImplementation.PdfGeneratorForAdmin;
import com.feuji.weekendtrip.serviceImplementation.PdfGeneratorForUser;
import com.itextpdf.text.DocumentException;

//@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/weekendtrip/traveller")
public class TravellerController {

	@Autowired
	private CityService cityService;
	@Autowired
	private EmailSenderService senderService;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private TravellersService travellersService;

	@Autowired
	PassengersService passengersService;

	@Autowired
	PdfGeneratorForAdmin generator;

	@Autowired
	PdfGeneratorForUser generatorForUser;

	@GetMapping("/getalltravellers")
	public ResponseEntity<List<Traveller>> getAllTraveller() {
		return ResponseEntity.ok(travellersService.getTraveller());
	}

	@GetMapping("/gettraveller/{id}")
	public ResponseEntity<Traveller> getTravellerById(@PathVariable(value = "id") long id) {
		return ResponseEntity.ok(travellersService.getTravellerById(id));
	}

	@ResponseBody
	@PostMapping(value = "/savetraveller", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Traveller> saveTraveller(@RequestBody Traveller traveller) {
		return ResponseEntity.ok(travellersService.saveTraveller(traveller));
	}

	@DeleteMapping(value = "/deletetraveller/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deleteTraveller(@PathVariable(value = "id") long id) {
		travellersService.deleteTravellerById(id);
		return ResponseEntity.ok().body("Traveller are deleted");
	}

	@GetMapping("/getallpassenger")
	public ResponseEntity<List<Passenger>> getAllPasssengers() {
		return ResponseEntity.ok(passengersService.getPassengers());
	}

	@GetMapping("/getalltravellerspagination")
	public ResponseEntity<Map<String, Object>> getAllTravellersPagination(@RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "5") int size) {
		try {
			List<Traveller> travellersList = new ArrayList<>();
			Pageable paging = PageRequest.of(page, size);
			Page<Traveller> page2;
			page2 = travellersService.findAll(paging);
			travellersList = page2.getContent();

			Map<String, Object> map = new HashMap<>();
			map.put("travellers", travellersList);
			map.put("currentPage", page2.getNumber());
			map.put("totalItems", page2.getTotalElements());
			map.put("totalPages", page2.getTotalPages());

			return new ResponseEntity<>(map, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}

	@ResponseBody
	@PostMapping(value = "/savepassenger", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Passenger> savePassenger(@RequestBody Passenger passenger) {
		return ResponseEntity.ok(passengersService.savePassengers(passenger));
	}

	@DeleteMapping(value = "/deletepassenger/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> deletePassenger(@PathVariable(value = "id") long id) {
		passengersService.deletePasssengerById(id);
		return ResponseEntity.ok().body("passeneger are deleted");
	}

	@GetMapping("/paymentstatus/{id}/{packprice}")
	public ResponseEntity<Traveller> getPaymentStatus(@PathVariable int id, @PathVariable double packprice) {

		Traveller traveller = travellersService.getTravellerById(id);

		int num = (int) Math.random();
		System.out.println(num);
		if (num > 8) {
			traveller.setPaymentStatus(false);
		} else {
			traveller.setPaymentStatus(true);
		}
		traveller.setPackagePrice(packprice);
		return ResponseEntity.ok(travellersService.saveTraveller(traveller));
	}

	@GetMapping("/pdf/admin")
	public void generatePdf(HttpServletResponse response) throws DocumentException, IOException {
		response.setContentType("application/pdf");
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);

		List<Passenger> passengers = passengersService.getPassengers();
		List<Traveller> trav = travellersService.getTraveller();
		PdfGeneratorForAdmin generator = new PdfGeneratorForAdmin();
		generator.setPassengersList(passengers);
		generator.setTravellersList(trav);
		generator.generate(response);
	}

	@GetMapping("/getcity/{id}")
	public ResponseEntity<City> getCityById(@PathVariable(value = "id") long id) {
		return ResponseEntity.ok(cityService.getById(id));
	}

	@GetMapping("/pdf/user/{travellerId}/{amount}")
	public void generatePdfForUser(@PathVariable(value = "travellerId") long travellerId,
			@PathVariable(value = "amount") double amount) throws DocumentException, IOException {
System.out.println(amount);
		response.setContentType("application/pdf");

		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
		String currentDateTime = dateFormat.format(new Date());
		String headerkey = "Content-Disposition";
		String headervalue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
		response.setHeader(headerkey, headervalue);
		Traveller traveller = travellersService.getTravellerById(travellerId);

		System.out.println(travellerId);
		generatorForUser.generate(response, travellerId, amount);
		try {
			triggerMail(traveller);
		} catch (MessagingException e) {

			e.printStackTrace();
		}

	}

	public void triggerMail(Traveller traveller) throws MessagingException {
		senderService.sendSimpleEmail(traveller.getTravellerEmail(), "Weekendtrips booking confirmation ",
				"This is informed that your booking with mail " + traveller.getTravellerEmail() + "is confirmed .");

	}

	@GetMapping("/getallbooked/{email}")
	public ResponseEntity<List<Traveller>> getAllBooked(@PathVariable String email) {
		return ResponseEntity.ok(travellersService.findByEmail(email));
	}
}