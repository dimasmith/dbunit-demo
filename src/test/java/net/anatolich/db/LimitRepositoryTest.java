package net.anatolich.db;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import net.anatolich.model.Limit;
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

import java.util.List;

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
    @DatabaseSetup("classpath:datasets/limits.xml")
    @DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = "classpath:datasets/limits.xml")
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
}
