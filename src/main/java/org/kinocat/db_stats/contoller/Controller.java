package org.kinocat.db_stats.contoller;

import lombok.AllArgsConstructor;
import org.kinocat.db_stats.entity.SalesAndTrafficByAsin;
import org.kinocat.db_stats.entity.SalesAndTrafficByDate;
import org.kinocat.db_stats.service.SalesAndTrafficByAsinService;
import org.kinocat.db_stats.service.SalesAndTrafficByDateService;
import org.kinocat.db_stats.service.SchedulingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class Controller {
    final SalesAndTrafficByDateService dateService;
    final SalesAndTrafficByAsinService asinService;
    final SchedulingService schedulingService;

    @GetMapping(value = {"/by_date", "/by_date/{date1}", "/by_date/{date1}/{date2}"})
    public ResponseEntity<List<SalesAndTrafficByDate>> read(
            @PathVariable(required = false) String date1,
            @PathVariable(required = false) String date2) {

        if (date1 != null) {
            return date2 != null ?
                    new ResponseEntity<>(dateService.readByDateBetween(date1, date2), HttpStatus.OK) :
                    new ResponseEntity<>(dateService.readByDate(date1), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(dateService.readAll(), HttpStatus.OK);
        }
    }

    @GetMapping(value = {"/by_asin", "/by_asin/{asin}"})
    public ResponseEntity<List<SalesAndTrafficByAsin>> read(
            @PathVariable(required = false) String asin) {

        if (asin != null) {
            return new ResponseEntity<>(asinService.readByAsins(asin), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(asinService.readAll(), HttpStatus.OK);
        }
    }

    @GetMapping("/debugCaching") //for demonstration
    public String debugCaching() {
        return schedulingService.debugCaching();
    }
}
