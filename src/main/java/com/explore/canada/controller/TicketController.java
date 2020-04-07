package com.explore.canada.controller;

import com.explore.canada.beans.TicketInfo;
import com.explore.canada.dao.BusDAO;
import com.explore.canada.dao.IBusDAO;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
public class TicketController {

    @GetMapping(value = "/findbus")
    @ResponseBody
    public List<TicketInfo> findBus(@RequestParam(name = "source") String source,
                                    @RequestParam(name = "destination") String destination) {
        IBusDAO busDAO = new BusDAO();
        return busDAO.loadBusDetailsBySourceAndDestinations(source, destination);
    }

    @GetMapping(value = "/findbusbyid")
    @ResponseBody
    public TicketInfo findBus(@RequestParam(name = "busid") String busId) {
        IBusDAO busDAO = new BusDAO();
        return busDAO.loadBusDetailsById(busId);
    }

}
