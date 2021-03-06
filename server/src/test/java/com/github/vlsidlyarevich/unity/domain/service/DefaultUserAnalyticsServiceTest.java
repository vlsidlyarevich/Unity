package com.github.vlsidlyarevich.unity.domain.service;

import com.github.vlsidlyarevich.unity.common.model.AnalysisReport;
import com.github.vlsidlyarevich.unity.domain.model.UserAnalytics;
import com.github.vlsidlyarevich.unity.domain.exception.ResourceNotFoundException;
import com.github.vlsidlyarevich.unity.domain.repository.UserAnalyticsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;

import static com.github.vlsidlyarevich.unity.TestUtils.createAnalysisReport;
import static com.github.vlsidlyarevich.unity.TestUtils.createUserAnalytics;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class DefaultUserAnalyticsServiceTest {

    private UserAnalyticsService userAnalyticsService;

    @Mock
    private UserAnalyticsRepository userAnalyticsRepository;

    @Before
    public void setUp() {
        this.userAnalyticsService = new DefaultUserAnalyticsService(userAnalyticsRepository);
    }

    @Test
    public void add_Success_IfValid() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        doReturn(userAnalytics).when(userAnalyticsRepository).save((UserAnalytics) anyObject());

        userAnalyticsService.add(userAnalytics);

        verify(userAnalyticsRepository).save(userAnalytics);
    }

    @Test(expected = IllegalArgumentException.class)
    public void add_ExceptionThrown_IfNull() throws Exception {
        UserAnalytics userAnalytics = null;
        doThrow(IllegalArgumentException.class).when(userAnalyticsRepository).save(userAnalytics);

        userAnalyticsService.add(null);
    }

    @Test
    public void find_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();
        doReturn(userAnalytics).when(userAnalyticsRepository).findOne(userAnalytics.getId());

        assertThat(userAnalyticsService.find(userAnalytics.getId()), equalTo(userAnalytics));

        verify(userAnalyticsRepository).findOne(userAnalytics.getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void find_Null_IfNotPresent() throws Exception {
        doReturn(null).when(userAnalyticsRepository).findOne(anyString());

        userAnalyticsService.find("id");
    }

    @Test
    public void findByUserId_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        doReturn(userAnalytics).when(userAnalyticsRepository).findByUserId(userAnalytics.getUserId());

        assertThat(userAnalyticsService.findByUserId(userAnalytics.getUserId()), is(userAnalytics));

        verify(userAnalyticsRepository).findByUserId(userAnalytics.getUserId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findByUserId_Null_IfNotPresent() throws Exception {
        assertThat(userAnalyticsService.findByUserId("userid"), nullValue());

        userAnalyticsRepository.findByUserId("userid");
    }

    @Test
    public void findAll_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();
        ArrayList userAnalyticsList = new ArrayList() {{
            add(userAnalytics);
        }};

        doReturn(userAnalyticsList).when(userAnalyticsRepository).findAll();

        assertThat(userAnalyticsService.findAll(), containsInAnyOrder(userAnalyticsList.toArray()));

        verify(userAnalyticsRepository).findAll();
    }

    @Test
    public void findAll_Empty_IfNotPresent() throws Exception {
        doReturn(Collections.emptyList()).when(userAnalyticsRepository).findAll();

        assertThat(userAnalyticsService.findAll(), emptyCollectionOf(UserAnalytics.class));

        verify(userAnalyticsRepository).findAll();
    }

    @Test
    public void delete_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        userAnalyticsService.delete(userAnalytics.getId());

        verify(userAnalyticsRepository).delete(userAnalytics.getId());
    }

    @Test
    public void deleteReport_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();

        doReturn(userAnalytics).when(userAnalyticsRepository).findByUserId(userAnalytics.getUserId());
        doReturn(userAnalytics).when(userAnalyticsRepository).save(Matchers.any(UserAnalytics.class));

        userAnalyticsService.deleteReport(userAnalytics.getUserId(), userAnalytics.getReports().get(0).getId());

        assertThat(userAnalyticsService.findByUserId(userAnalytics.getUserId()).getReports().size(), is(0));
    }

    @Test
    public void deleteAll_Success_IfPresent() throws Exception {
        userAnalyticsService.deleteAll();

        verify(userAnalyticsRepository).deleteAll();
    }

    @Test
    public void deleteAllReports_Success_IfPresent() throws Exception {
        UserAnalytics userAnalytics = createUserAnalytics();
        AnalysisReport analysisReport = createAnalysisReport();
        userAnalytics.getReports().add(analysisReport);

        doReturn(userAnalytics).when(userAnalyticsRepository).findByUserId(userAnalytics.getUserId());
        doReturn(userAnalytics).when(userAnalyticsRepository).save(Matchers.any(UserAnalytics.class));

        userAnalyticsService.deleteAllReports(userAnalytics.getUserId());

        assertThat(userAnalyticsService.findByUserId(userAnalytics.getUserId()).getReports().size(), is(0));
    }
}
