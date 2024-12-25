package in.virtusa.expensetrackerapi.constant;

public class SecurityConstant {
    // Roles
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    // Endpoints
    public static final String REGISTER = "/register";
    public static final String LOGIN = "/login";
    public static final String UPDATE_EXPENSE = "/api/v1/updateExpense/**";
    public static final String DELETE_EXPENSE = "/api/v1/deleteExpense/**";
    public static final String GET_EXPENSE_BY_CATEGORY = "/api/v1/expense/category";
    public static final String GET_EXPENSE_BY_NAME = "/api/v1/expense/name";
    public static final String GET_EXPENSE_BY_DATE = "/api/v1/expense/date";
    public static final String NEW_EXPENSE = "/api/v1/newExpenses";
    public static final String GET_ALL_EXPENSES = "/api/v1/getAllExpenses";
    public static final String GET_EXPENSE_BY_ID = "/api/v1/getExpenseById/**";

    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    private SecurityConstant() {

    }
}
