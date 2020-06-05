package fr.esgi.api.config;

import fr.esgi.api.models.request.Request;
import fr.esgi.api.services.IRequestService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RequestBatchBean {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final IRequestService requestService;

    // @Scheduled(
    // cron = "0,30 * * * * *")
    public void cronJob() {
        logger.info("> cronJob");

        // Add scheduled logic here
        List<Request> requests = requestService.findAll();
        logger.info("There are {} requests in the data store.",
                requests.size());

        logger.info("< cronJob");
    }

    // @Scheduled(
    // initialDelay = 5000,
    // fixedRate = 15000)
    public void fixedRateJobWithInitialDelay() {
        logger.info("> fixedRateJobWithInitialDelay");

        // Add scheduled logic here
        // Simulate job processing time
        long pause = 5000;
        long start = System.currentTimeMillis();
        do {
            if (start + pause < System.currentTimeMillis()) {
                break;
            }
        } while (true);
        logger.info("Processing time was {} seconds.", pause / 1000);

        logger.info("< fixedRateJobWithInitialDelay");
    }

    // @Scheduled(
    // initialDelay = 5000,
    // fixedDelay = 15000)
    public void fixedDelayJobWithInitialDelay() {
        logger.info("> fixedDelayJobWithInitialDelay");

        // Add scheduled logic here
        // Simulate job processing time
        long pause = 5000;
        long start = System.currentTimeMillis();
        do {
            if (start + pause < System.currentTimeMillis()) {
                break;
            }
        } while (true);
        logger.info("Processing time was {} seconds.", pause / 1000);

        logger.info("< fixedDelayJobWithInitialDelay");
    }

}
