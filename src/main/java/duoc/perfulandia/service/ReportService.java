package duoc.perfulandia.service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import duoc.perfulandia.model.InventoryReport;
import duoc.perfulandia.model.ReportLowStock;
import duoc.perfulandia.model.Repo.ProductRepo;
import lombok.Data;
import java.util.Collection;
@Data
@Service
public class ReportService {

    @Autowired
    private ProductRepo productRepo;

    public List<ReportLowStock> getProductsBelowStock(int threshold) {
        return productRepo.findByInventoryLessThan(threshold).stream()
                .map(p -> new ReportLowStock(p.getId(), p.getName(), p.getInventory()))
                .collect(Collectors.toList());
    }

    public List<InventoryReport> getInventoryReport() {
        return productRepo.findAll().stream()
                .map(p -> new InventoryReportDTO(p.getId(), p.getName(), p.getInventory()))
                .collect(Collectors.toList());
    }
}

