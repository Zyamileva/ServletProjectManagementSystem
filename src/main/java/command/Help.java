package command;

import view.View;

public class Help implements Command {
    public static final String HELP = "help";
    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public boolean canExecute(String input) {
        return input.equals(HELP);
    }

    @Override
    public void execute() {
        view.write(String.format("Enter %s to see all command", Help.HELP));
        view.write(String.format("Enter %s to exit program", Exit.EXIT));
        view.write(String.format("Enter %s to add developer", AddDeveloper.ADD_DEVELOPER));
        view.write(String.format("Enter %s to get the salary (sum) of all project developers",
                SalleryOfProjects.SALLERY_OF_PROJECTS));
        //view.write(String.format("Enter %s command to add book to the library", AddBook.ADD_BOOK));
        //view.write(String.format("Enter %s command to add journal to the library", "add journal"));
    }
}


