package pl.ejaksla.onlinejudge.base

import groovy.transform.TypeChecked
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import pl.ejaksla.onlinejudge.OnlinejudgeApplication
import spock.lang.Specification

import javax.transaction.Transactional

    @TypeChecked
    @SpringBootTest(classes = [OnlinejudgeApplication])
    @Transactional
    @Rollback
    abstract class IntegrationSpec extends Specification {
        @Autowired
        protected WebApplicationContext webApplicationContext

        MockMvc mockMvc

        @Before
        void setupMockMvc() {
            mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//                    .apply(springSecurity())
                    .build()
        }

    }

