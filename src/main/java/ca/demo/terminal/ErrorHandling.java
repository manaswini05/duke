package ca.demo.terminal;

public class ErrorHandling {

    static void error_handling(String input) throws MyException {
        String first = input.split(" ")[0];
        if (first.equals("todo") || first.equals("deadline") || first.equals("event") || first.equals("done") || first.equals("list")
                || first.equals("bye") || first.equals("delete") || first.equals("find")) {
            if (input.equals("todo") || input.equals("deadline") || input.equals("event") || input.equals("done") || input.equals("delete") || input.equals("find")) {
                throw new MyException("OOPS!!! The description of a " + input + " cannot be empty.");
            }
        } else {
            throw new MyException(("OOPS!!! I'm sorry, but I don't know what that means :-("));
        }
    }

    public Integer handle_error(String inputString) {
        try {
            error_handling(inputString);
        } catch (MyException e) {
            System.out.println("------------------------------");
            System.out.println(e);
            System.out.println("------------------------------");
            return 1;
        }
        return 0;
    }
}
