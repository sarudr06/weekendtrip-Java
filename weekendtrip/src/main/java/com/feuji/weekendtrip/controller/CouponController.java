package com.feuji.weekendtrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.weekendtrip.model.Coupon;
import com.feuji.weekendtrip.service.CouponService;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "weekendtrip/coupon")
public class CouponController {
	@Autowired
	private CouponService couponService;

	@GetMapping(value = "/findallcoupon")
	public ResponseEntity<List<Coupon>> getAllCoupons() {
		List<Coupon> coupons = couponService.getAllCoupons();
		System.out.println(coupons);
		return ResponseEntity.ok().body(coupons);

	}

}