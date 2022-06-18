package com.fireprohibition.CBomb.service.jobs;

import com.fireprohibition.CBomb.service.ScreeningMovieService;
import lombok.RequiredArgsConstructor;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class finishScreeningMovieJob implements Job {

    private final ScreeningMovieService screeningMovieService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
//        screeningMovieService.finishScreeningMovie();
    }
}
