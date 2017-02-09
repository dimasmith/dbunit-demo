package net.anatolich.db;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import net.anatolich.model.Limit;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

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
public class CustomDataSourceTest {

    @Test
    @DatabaseSetup(value = "classpath:datasets/CustomDataSourceTest/emptyColumns-setup.xml")
    public void emptyColumns() throws Exception {

    }
}
