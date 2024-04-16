package com.whn.hellospring;

import com.alibaba.fastjson.JSONObject;
import com.whn.hellospring.mapper.CoffeeMapper;
import com.whn.hellospring.model.CoffeeDO;
import com.whn.hellospring.model.CoffeeOrderDO;
import com.whn.hellospring.model.OrderState;
import com.whn.hellospring.repository.CoffeeOrderRepository;
import com.whn.hellospring.repository.CoffeeRepository;
import com.whn.hellospring.service.CoffeeOrderService;
import com.whn.hellospring.service.CoffeeService;
import com.whn.hellospring.service.JuheService;
import com.whn.hellospring.service.OilService;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ComponentScan("com")
@SpringBootApplication
@RestController
@Slf4j
@EnableJpaRepositories
@EnableTransactionManagement
public class HelloSpringApplication extends SpringBootServletInitializer implements ApplicationRunner {

	@Autowired
	private CoffeeRepository coffeeRepository;

	@Autowired
	private CoffeeOrderRepository orderRepository;

	@Autowired
	private CoffeeMapper coffeeMapper;

	@Autowired
	private CoffeeService coffeeService;

	@Autowired
	private CoffeeOrderService orderService;

	@Autowired
	private OilService oilService;

	@Autowired
	private JuheService juheService;

	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
	}

	// 继承SpringBootServletInitializer 实现configure 方便打war 外部服务器部署。

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HelloSpringApplication.class);
	}

	@Transactional
	@Override
	public void run(ApplicationArguments args) throws Exception {
//		initOrders();

//		findOrders();

//		testMybatis();

//		breakPage();

//		bucks();//初步测试

//		oil();

//		testRedis();

//		calculationIntervalMileage();


	}

	private void calculationIntervalMileage() {
		oilService.calculationIntervalMileage(1L);
	}

	private void testRedis() {
		Jedis jedis = new Jedis("192.168.0.217");
		jedis.auth("root");//加入这一段代码
		System.out.println("Server is running: "+jedis.ping());
		System.out.println("connection to server sucessfully");
		jedis.set("title","hellword");
		System.out.println(jedis.get("title"));
	}

	private void oil() {
		juheService.getOilPrice();

	}

	private void bucks() {
		log.info("findAllCoffee{}",coffeeRepository.findAll());
		Optional<CoffeeDO> latte = coffeeService.findOneCoffee("Latte");
		if (latte.isPresent()){
//			CoffeeOrderDO order = orderService.createOrder( latte.get());
//			log.info("update",orderService.updateState(order,OrderState.INIT));
//			log.info("update",orderService.updateState(order,OrderState.PAID));
		}
	}


	/**
	 * 分页测试
	 * Mybatis PageHelper
	 */
//	private void breakPage() {
//		//第一页数据
//		coffeeMapper.findAllWithRowBounds(new RowBounds(1,3))
//				.forEach(c->log.info("page(1) Coffee{}",c));
//
//		//第二页数据
//		coffeeMapper.findAllWithRowBounds(new RowBounds(2,3))
//				.forEach(c->log.info("page(2) Coffee{}",c));
//
//		log.info("=============================");
//
//		//page传递为0的时候会取出所有数据
//		coffeeMapper.findAllWithRowBounds(new RowBounds(1,0))
//				.forEach(c->log.info("Page(1) 所有数据 Coffee{}",c));
//
//		log.info("=============================");
//
//		//使用参数来获取
//		coffeeMapper.findAllWithParam(1,3)
//				.forEach(c->log.info("Page(1) 使用参数来获取3 Coffee{}",c));
//
//
//		List<Coffee> list = coffeeMapper.findAllWithParam(2,3);
//		PageInfo<Coffee> page = new PageInfo<>(list);
//		log.info("page{}",page);
//
//	}

//	private void testMybatis() {
//		Coffee c = Coffee.builder().name("espresso")
//				.price(Money.of(CurrencyUnit.of("CNY"),20.0))
//				.build();
//
//		int count = coffeeMapper.save(c);
//		log.info("Save{} coffee:{}",count,c);
//
//		c = Coffee.builder().name("latte")
//				.price(Money.of(CurrencyUnit.of("CNY"),25.0))
//				.build();
//
//		count = coffeeMapper.save(c);
//		log.info("Save{} coffee:{}",count,c);
//
//		c = coffeeMapper.findById(c.getId());
//		log.info("find coffee:{}",c);
//
//	}


	@RequestMapping("/hello")
	public String hello(){
		return "hello spring";
	}

	private void initOrders(){

		//创建一杯咖啡
		CoffeeDO espresso = CoffeeDO.builder().name("espresso")
				.price(Money.of(CurrencyUnit.of("CNY"),20.0))
				.build();
		coffeeRepository.save(espresso);
		log.info("Coffee:{}",espresso);


		//创建一杯咖啡
		CoffeeDO latte = CoffeeDO.builder().name("latte")
				.price(Money.of(CurrencyUnit.of("CNY"),30.0))
				.build();
		coffeeRepository.save(latte);
		log.info("Coffee:{}",latte);


		//创建一个订单，包含一杯咖啡
		CoffeeOrderDO order = CoffeeOrderDO.builder()
				.customer("Li Lei")
//				.items(Collections.singletonList(espresso))
				.state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order:{}",order);


		//创建一个订单，包含两杯咖啡
		 order = CoffeeOrderDO.builder()
				.customer("Li Lei")
//				.items(Arrays.asList(espresso,latte))
				 .state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order:{}",order);


		//创建一个订单，包含两杯咖啡
		order = CoffeeOrderDO.builder()
				.customer("Li Lei")
//				.items(Arrays.asList(espresso,latte))
				.state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order:{}",order);

		//创建一个订单，包含两杯咖啡
		order = CoffeeOrderDO.builder()
				.customer("Li Lei")
//				.items(Arrays.asList(espresso,latte))
				.state(OrderState.INIT)
				.build();
		orderRepository.save(order);
		log.info("Order:{}",order);
	}


//	private void  findOrders(){
//		//获取咖啡
//		coffeeRepository
//				.findAll()
//				.forEach(c->log.info("Loading{}",c));
//
//		//获取前三，更新时间降序，ID升序
//		List<CoffeeOrder> list = orderRepository.findTop3ByOrderByUpdateTimeDescIdAsc();
//		log.info("findTop3ByOrderByUpdateTimeDescIdAsc:{}" ,getJoinedOrderId(list));
//
//		//获取Li Lei 的订单id
//		list = orderRepository.findByCustomerOrderById("Li Lei");
//		log.info("findByCustomerOrderById:{}" ,getJoinedOrderId(list));
//
//		//列出订单，订单中的咖啡
//		list.forEach(o->{
//			log.info("Order{}",o.getId());
//			o.getItems().forEach(i->log.info(" Item{}",i));
//		});
//
//		//获取latte的咖啡列表
//		list = orderRepository.findByItems_Name("latte");
//		log.info("findByItems_Name:{}" ,getJoinedOrderId(list));
//	}

	/**
	 * 将列表中的id用，拼接
	 * @param list
	 * @return
	 */
	private String getJoinedOrderId(List<CoffeeOrderDO> list) {
		return list.stream().map(o->o.getId().toString())
				.collect(Collectors.joining(","));
	}
}
