package com.piper.valley.viewmodels;

import com.piper.valley.models.common.LineChartData;
import com.piper.valley.models.domain.Order;
import com.piper.valley.models.domain.Store;
import com.piper.valley.models.domain.StoreProduct;
import com.piper.valley.models.enums.OrderStatus;
import com.piper.valley.models.repository.OrderRepository;
import com.piper.valley.models.service.OrderService;
import com.piper.valley.models.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class StoreOwnerStatisticsViewModel {

	@Autowired
	StoreService storeService;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	//Get User Stores.
	public HashMap<String, Object> create(Long Id) {
		HashMap<String, Object> model = new HashMap<>();
		Collection<Store> Accepted = storeService.getAllAcceptedUserStores(Id);

		//statistics charts data
		Collection<String> Names = Accepted.stream().map(Store::getName).collect(Collectors.toList());

		//Collection<Integer> ProductsCount = Accepted.stream().map(x -> x.getStoreProducts().size()).collect(Collectors.toList());
		Collection<Integer> ProductsCount = Accepted.stream().map(x -> x.getStoreProducts().stream().map(StoreProduct::getStoreViews).reduce(0, (a, b) -> a + b)).collect(Collectors.toList());

		List<List<Integer>> perWeekStore = new ArrayList<>();

		Calendar calendar = Calendar.getInstance();

		Date now = new Date();

		calendar.add(Calendar.WEEK_OF_YEAR,-1);
		Date lastweek = calendar.getTime();

		List<OrderStatus> orderStatuses = new ArrayList<>();
		orderStatuses.add(OrderStatus.DELIVERED);
		orderStatuses.add(OrderStatus.PROCESSED);
		List<LineChartData> lineChartData = new ArrayList<>();
		String[] colors = {"#50b497", "#3498DB", "#F39C12", "#3498DB", "#F39C12", "#6f42c1"};
		int ic = 0;
		for (Store store : Accepted) {
			perWeekStore.add(new ArrayList<>());

			for (int i = 0; i < 8; i++) {
				perWeekStore.get(perWeekStore.size() - 1).add(0);
			}

			List<Order> lastOrders
					= orderRepository.findAllByStoreProduct_Store_IdAndProcessedDateAfterAndOrderStatusIn(store.getId(), lastweek, orderStatuses);

			for (Order order : lastOrders)
			{
				Duration diff = Duration.between(order.getProcessedDate().toInstant(), now.toInstant());
				Integer offset = 7 - ((int) diff.toDays());
				Integer increment = perWeekStore.get(perWeekStore.size() - 1).get(offset);
				perWeekStore.get(perWeekStore.size() - 1).set(offset, increment+1);
			}

			lineChartData.add(new LineChartData(perWeekStore.get(perWeekStore.size()-1), store.getName(), colors[ic++], true));
			ic = ic % colors.length;
		}

		List<String> dayNames = new ArrayList<>();
		for (int i = 0; i < 7; i++) {
			int day = calendar.get(Calendar.DAY_OF_WEEK);;
			switch (day) {
				case 1:
					dayNames.add("Last Sunday");
					break;
				case 2:
					dayNames.add("Last Monday");
					break;
				case 3:
					dayNames.add("Last Tuesday");
					break;
				case 4:
					dayNames.add("Last Wednesday");
					break;
				case 5:
					dayNames.add("Last Thursday");
					break;
				case 6:
					dayNames.add("Last Friday");
					break;
				case 7:
					dayNames.add("Last Saturday");
			}
			calendar.add(Calendar.DAY_OF_WEEK,1);
		}
		dayNames.add("Today");


		model.put("accepted", Accepted);
		model.put("names", Names);
		model.put("productCount", ProductsCount);
		model.put("lineChartData", lineChartData);
		model.put("dayNames", dayNames);

		return model;
	}

}
