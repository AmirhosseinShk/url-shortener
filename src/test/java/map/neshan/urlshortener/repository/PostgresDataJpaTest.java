package map.neshan.urlshortener.repository;

import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY;

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@DataJpaTest()
@AutoConfigureEmbeddedDatabase(type = AutoConfigureEmbeddedDatabase.DatabaseType.POSTGRES, provider = ZONKY)
public @interface PostgresDataJpaTest {
    @AliasFor(annotation = DataJpaTest.class)
    boolean showSql() default true;

    @AliasFor(annotation = DataJpaTest.class)
    boolean useDefaultFilters() default true;

    @AliasFor(annotation = DataJpaTest.class)
    ComponentScan.Filter[] includeFilters() default {};

    @AliasFor(annotation = DataJpaTest.class)
    ComponentScan.Filter[] excludeFilters() default {};

    @AliasFor(annotation = DataJpaTest.class)
    Class<?>[] excludeAutoConfiguration() default {};

}
