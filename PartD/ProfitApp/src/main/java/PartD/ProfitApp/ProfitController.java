package PartD.ProfitApp;


import org.springframework.web.bind.annotation.*;


import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/profit")
public class ProfitController {
    private final ProfitRepository repo;
    public ProfitController(ProfitRepository repo) { this.repo = repo; }

    /** GET /api/profit/2025-07 → { "month": "...", "amount": ... } */
    @GetMapping("/{month}")
    public Profit getByMonth(@PathVariable String month) {
        return repo.findById(month).orElseThrow(
                () -> new NoSuchElementException("No profit for month " + month));
    }
}
