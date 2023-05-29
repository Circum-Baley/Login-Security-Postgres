package com.userlogin.userapp;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

import com.github.javafaker.Faker;
import com.userlogin.userapp.entities.Address;
import com.userlogin.userapp.entities.Consumption;
import com.userlogin.userapp.entities.Device;
import com.userlogin.userapp.entities.Profile;
import com.userlogin.userapp.entities.Role;
import com.userlogin.userapp.entities.User;
import com.userlogin.userapp.entities.UserInRole;
import com.userlogin.userapp.entities.Vehicle;
import com.userlogin.userapp.repositories.AddressRepository;
import com.userlogin.userapp.repositories.ConsumptionRepository;
import com.userlogin.userapp.repositories.DeviceRepository;
import com.userlogin.userapp.repositories.ProfileRepository;
import com.userlogin.userapp.repositories.RoleRepository;
import com.userlogin.userapp.repositories.UserInRoleRepository;
import com.userlogin.userapp.repositories.UserRepository;
import com.userlogin.userapp.repositories.VehicleRepository;

@SpringBootApplication
@ComponentScan("com.userlogin.userapp") // to scan packages mentioned
@Configuration // /Login-Security-CRS/src/main/java/com/userlogin/userApp/LoginSecurityCrsApplication.java
@EnableJpaRepositories("com.userlogin.userapp.repositories")
public class LoginSecurityCrsApplication implements ApplicationRunner {

	private final static Logger log = LoggerFactory.getLogger(LoginSecurityCrsApplication.class);

	Random random = new Random();
	@Autowired
	private Faker faker;

	@Autowired
	private ProfileRepository profileRepository;

	@Autowired
	private ConsumptionRepository consumptionRepository;
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserInRoleRepository userInRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private DeviceRepository deviceRepository;

	public static void main(String[] args) {
		SpringApplication.run(LoginSecurityCrsApplication.class, args);
		log.info("Benvenidos мать ублюдок");
		UUID uuid = UUID.randomUUID();
		log.info("{}", uuid.toString());
	}

	/*
	 * LandingPage
	 */

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.xhtml");
		registry.addViewController("/api-vehicle").setViewName("redirect:/apiVehicle.html");

	}

//	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**").addResourceLocations("classpath:/src/main/resources/static/template/")
//				.setCacheControl(CacheControl.maxAge(1, TimeUnit.HOURS).cachePublic());
//	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role roles[] = { new Role("ADMIN"), new Role("ROOT"), new Role("USER"), new Role("SUPPORT") };

		for (Role role : roles) {
			roleRepository.save(role);
		}

		for (int i = 0; i < 20; i++) {

			User user = new User();
			user.setUsername(faker.name().username());
			user.setPassword(faker.animal().name());

			User created = userRepository.save(user);

			UserInRole userInRole = new UserInRole(created, roles[new Random().nextInt(4)]);

			log.info("ID {} Usuario {} Password {} - ROL {}", created.getId(), created.getUsername(),
					created.getPassword(), userInRole.getRole().getName());

			userInRoleRepository.save(userInRole);
		}

		for (int i = 0; i < 50; i++) {
			List<User> users = userRepository.findAll();

			Random random = new Random();
			int randomIndex = random.nextInt(users.size());

			Vehicle vehicle = new Vehicle(String.format(faker.regexify("[A-Z]{4}") + "-" + faker.regexify("[0-9]{2}")),
					users.get(randomIndex));

			vehicleRepository.save(vehicle);
		}
		for (int i = 0; i < 50; i++) {
			List<Vehicle> vehicles = vehicleRepository.findAll();

			Random randon = new Random();
			int randonVehicles = randon.nextInt(vehicles.size());

			Consumption consumption = new Consumption();
			consumption.setAmount(faker.number().randomDouble(2, 30000, 60000));
			consumption.setDescription(faker.superhero().descriptor());
			consumption.setNumber(faker.number().randomNumber());
			// Definir el rango de fechas
			LocalDate startDate = LocalDate.of(2020, 1, 1);
			LocalDate endDate = LocalDate.of(2020, 5, 30);

			// Convertir LocalDate a Date
			Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

			// Generar una fecha aleatoria dentro del rango

			consumption.setDate(faker.date().between(start, end));

			consumption.setVehicle(vehicles.get(randonVehicles));
			consumptionRepository.save(consumption);

		}
		for (int i = 0; i < 20; i++) {
			List<User> users = userRepository.findAll();

			int randomUser = random.nextInt(users.size());
			Profile profile = new Profile(faker.pokemon().name(), faker.name().lastName(), faker.date().birthday(),
					users.get(randomUser));
			profileRepository.save(profile);

			List<Profile> profiles = profileRepository.findAll();

			int randomProfile = random.nextInt(profiles.size());
			Address address = new Address(faker.address().streetAddress(), faker.address().buildingNumber(),
					faker.address().cityName(), profiles.get(randomProfile));
			addressRepository.save(address);
//			log.info("{}{}{}{}{}",address.getId(),address.getStreet(),address.getNumber(),address.getCity(),address.getProfile());

		}
		for (int i = 0; i < 20; i++) {
			List<Profile> profiles = profileRepository.findAll();
			int randomProfile = random.nextInt(profiles.size());
			Device device = new Device();
			device.setName(faker.app().name());
			device.setBrand(faker.space().meteorite());
			device.setModel(faker.aviation().airport());
			// Definir el rango de fechas
			LocalDate startDate = LocalDate.of(2020, 1, 1);
			LocalDate endDate = LocalDate.of(2020, 5, 30);
			// Convertir LocalDate a Date
			Date start = Date.from(startDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			Date end = Date.from(endDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			device.setDateAcquisition(faker.date().between(start, end));
			device.setSerialNumber(faker.internet().uuid());
			device.setProfile(profiles.get(randomProfile));
			deviceRepository.save(device);
		}

	}
}
