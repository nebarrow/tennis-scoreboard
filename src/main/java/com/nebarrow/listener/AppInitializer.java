package com.nebarrow.listener;

import com.nebarrow.service.FinishedMatchPersistenceService;
import com.nebarrow.service.MatchScoreCalculationService;
import com.nebarrow.service.NewMatchService;
import com.nebarrow.service.OngoingMatchService;
import com.nebarrow.util.ServiceLocator;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServiceLocator.init(sce.getServletContext());
        sce.getServletContext().setAttribute(FinishedMatchPersistenceService.class.getName(), new FinishedMatchPersistenceService());
        sce.getServletContext().setAttribute(MatchScoreCalculationService.class.getName(), new MatchScoreCalculationService());
        sce.getServletContext().setAttribute(NewMatchService.class.getName(), new NewMatchService());
        sce.getServletContext().setAttribute(OngoingMatchService.class.getName(), new OngoingMatchService());
    }
}
