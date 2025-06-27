package duoc.perfulandia.controller;

import duoc.perfulandia.model.InventoryReport;
import duoc.perfulandia.model.ReportLowStock;
import duoc.perfulandia.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/low-stock")
    public List<ReportLowStock> getLowStock(@RequestParam(defaultValue = "10") int threshold) {
        return reportService.getProductsBelowStock(threshold);
    }

    @GetMapping("/inventory")
    public List<InventoryReport> getInventory() {
        return reportService.getInventoryReport();
    }
}
