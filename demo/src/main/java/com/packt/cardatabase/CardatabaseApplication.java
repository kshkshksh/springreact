package com.packt.cardatabase;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.packt.cardatabase.domain.Car;
import com.packt.cardatabase.domain.CarRepository;
import com.packt.cardatabase.domain.Owner;
import com.packt.cardatabase.domain.OwnerRepository;



@SpringBootApplication
public class CardatabaseApplication implements CommandLineRunner{
	
	private static final Logger logger=
			LoggerFactory.getLogger(CardatabaseApplication.class);
	
	@Autowired
	private CarRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(CardatabaseApplication.class, args);

	}
	@Autowired
	private OwnerRepository orepository;
	
	@Override
	public void run(String...args) throws Exception{
		//소유자 객체를 추가하고 데이터베이스에 저장
		Owner owner1 = new Owner("John" , "Johnson");
		Owner owner2 = new Owner("Mary" , "Robinson");
		orepository.saveAll(Arrays.asList(owner1, owner2));
		
		repository.save(new Car("Ford", "Mustang", "Red", "ADF-1121", 2021, 59000 ,owner1));
		repository.save(new Car("Nissan", "Leaf", "White", "SSJ-3002", 2019, 29000 , owner2));
		repository.save(new Car("Toyota", "Prius", "Silver", "KKO-0212", 2020, 39000 , owner2));
		
		//모든 자동차를 가져와 콘솔에 로깅한다
		for (Car car : repository.findAll()) {
			logger.info(car.getBrand()+ " " +car.getModel());
		}
	}

}
