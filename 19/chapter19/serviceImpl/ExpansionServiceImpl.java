package chapter19.serviceImpl;

import chapter19.models.ExpansionModel;
import chapter19.repositories.ExpansionRepository;
import chapter19.services.DataFilesService;
import chapter19.services.ExpansionService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Queue;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Function;

import static chapter19.utils.StringUtilsExpansionPlansServiceImplMessages.PATTERN;

public class ExpansionServiceImpl implements ExpansionService {
    // DateTimeFormatter
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(PATTERN.getMessage());
    private Function<ZonedDateTime, String> formatter = x -> dateTimeFormatter.withLocale(Locale.getDefault()).format(x);
    private ExpansionRepository expansionRepository;

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
}
