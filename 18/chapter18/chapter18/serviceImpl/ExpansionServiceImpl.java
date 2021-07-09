package chapter18.serviceImpl;

import chapter18.models.*;
import chapter18.repositories.*;
import chapter18.services.*;

import java.time.*;
import java.time.format.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

import static chapter18.utils.StringUtilsExpansionPlansServiceImplMessages.*;


public class ExpansionServiceImpl implements ExpansionService {
    // DateTimeFormatter
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN.getMessage());
    private Function<ZonedDateTime, String> formatter = x -> dateTimeFormatter.withLocale(Locale.getDefault()).format(x);
    private ExpansionRepository expansionRepository;
    // Scheduling plans
    private ScheduledExecutorService planScheduledExecutorService;
    private ScheduledFuture<?> plansForFuture;
    private Runnable runPlannerTask = () -> getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),
            LocalTime.now()),
            TimeZone.getDefault().toZoneId()),
            "shooting lessons for the workers this weekend",
            null));

    public ExpansionServiceImpl() {
        super();
    }

    public ExpansionServiceImpl(ExpansionRepository expansionRepository) {
        this.expansionRepository = expansionRepository;
    }

    @Override
    public Queue<ExpansionModel> getTodoExpansionList() {
        return expansionRepository.getTodoExpansionData();
    }

    @Override
    public Set<ExpansionModel> getDoneExpansionList() {
        return expansionRepository.getDoneExpansionData();
    }

    @Override
    public <K> Stream<K> toParallelStream(Collection<K> list) {
        if (list == null || list.isEmpty()) {
            return Stream.empty();
        }
        return list.stream().parallel();
    }


    @Override
    public boolean addPlan(ExpansionModel expansionModel) {
        // expansionModel.setStartDate(formatter.apply(ZonedDateTime.now()));
        return getTodoExpansionList().offer(new ExpansionModel(ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),
                LocalTime.now()),
                TimeZone.getDefault().toZoneId()),
                expansionModel.getPlan(),
                null));
    }

    @Override
    public ExpansionModel markPlanToDone() {
        getDoneExpansionList().add(new ExpansionModel(getTodoExpansionList().peek().getStartDate(),
                getTodoExpansionList().peek().getPlan(),
                ZonedDateTime.of(LocalDateTime.of(LocalDate.now(),
                        LocalTime.now()),
                        TimeZone.getDefault().toZoneId())));

        return getTodoExpansionList().poll();
    }

    @Override
    public boolean displayUpcomingPlanProcess() {
        if (getTodoExpansionList().peek() == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void shootingPlanner() {
        planScheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        plansForFuture = planScheduledExecutorService.scheduleAtFixedRate(runPlannerTask, 0, 20, TimeUnit.DAYS);

    }
}
