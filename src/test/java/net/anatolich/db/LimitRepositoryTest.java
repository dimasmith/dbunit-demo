package net.anatolich.db;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import net.anatolich.model.Limit;
import net.anatolich.model.PeriodicLimit;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@TestExecutionListeners({
        DbUnitTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class
})


@SpringBootApplication
@DirtiesContext
@EntityScan(basePackageClasses = {Limit.class})
public class LimitRepositoryTest {

    @Autowired
    private LimitRepository limitRepository;

    @Test
    @DatabaseSetup("classpath:datasets/limit-repository-test/find-limits.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:datasets/limit-repository-test/find-limits.xml")
    public void findLimits() throws Exception {
        List<Limit> saLimits = limitRepository.findAllByEntityTypeAndEntityReference("SA", "42");

        assertThat(saLimits, Matchers.hasSize(1));
    }

    @Test
    @ExpectedDatabase(value = "classpath:datasets/limit-repository-test/create-limit.xml", table = "limits")
    public void createLimit() throws Exception {
        Limit limit = new Limit("42", "SA-internal", "42", "GBP");
        limitRepository.save(limit);
    }

    @Test
    @DatabaseSetup("classpath:datasets/limit-repository-test/load-periodical-limits.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:datasets/limit-repository-test/load-periodical-limits.xml")
    public void loadPeriodicalLimits() throws Exception {
        Limit limit = limitRepository.findOne("42");

        assertThat(limit.getPeriodicLimits(), hasSize(2));
        assertThat(limit.getPeriodicLimits(), hasItems(
                hasProperty("id", is("1")),
                hasProperty("id", is("2"))
        ));
    }

    @Test
    @ExpectedDatabase(value = "classpath:datasets/limit-repository-test/create-limit-with-periodic-limits.xml", table = "limits")
    @ExpectedDatabase(value = "classpath:datasets/limit-repository-test/create-limit-with-periodic-limits.xml", table = "periodic_limits")
    public void createLimitWithPeriodicLimits() throws Exception {
        Limit limit = new Limit("42", "SA", "42", "GBP");
        limit.addPeriodicLimit(new PeriodicLimit("1", "10", "DAILY"));
        limit.addPeriodicLimit(new PeriodicLimit("2", "100", "WEEKLY"));

        limitRepository.save(limit);
    }
}
