package in.virtusa.expensetrackerapi.controller;

import in.virtusa.expensetrackerapi.entity.Expense;
import in.virtusa.expensetrackerapi.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExpenseController {


    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/getAllExpenses")
    public List<Expense> getAllExpenses(Pageable page) {
        return expenseService.getAllExpenses(page).toList();
    }

    @GetMapping("/getExpenseById/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return expenseService.getExpenseById(id);
    }


    @DeleteMapping("/deleteExpense/{id}")
    public void deleteExpenseById(@PathVariable Long id) {
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/newExpenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense) {
        return expenseService.saveExpenseDetails(expense);
    }

    @PutMapping("/updateExpense/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense, @PathVariable Long id){
        return expenseService.updateExpenseDetails(id, expense);
    }

    @GetMapping("/expense/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category, Pageable page) {
        return expenseService.readByCategory(category, page);
    }

    @GetMapping("/expense/name")
    public List<Expense> getExpensesByName(@RequestParam String keyword, Pageable page) {
        return expenseService.readByName(keyword, page);
    }

    @GetMapping("/expense/date")
    public List<Expense> getExpensesByDates(@RequestParam(required = false) Date startDate,
                                            @RequestParam(required = false) Date endDate,
                                            Pageable page) {
        return expenseService.readByDate(startDate, endDate, page);
    }
}






















