package com.feuji.weekendtrip.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feuji.weekendtrip.model.Coupon;
import com.feuji.weekendtrip.repository.CouponRepository;
import com.feuji.weekendtrip.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private CouponRepository couponRepository;

	@Override
	public List<Coupon> getAllCoupons() {
		return couponRepository.findAll();
	}

}