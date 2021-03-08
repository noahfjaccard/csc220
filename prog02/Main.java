package prog02;

/**
 *
 * @author vjm
 */
public class Main {

    /** Processes user's commands on a phone directory.
      @param fn The file containing the phone directory.
      @param ui The UserInterface object to use
             to talk to the user.
      @param pd The PhoneDirectory object to use
             to process the phone directory.
     */
    public static void processCommands(String fn, UserInterface ui, PhoneDirectory pd) {
        pd.loadData(fn);

        String[] commands = {
                "Add/Change Entry",
                "Look Up Entry",
                "Remove Entry",
                "Save Directory",
        "Exit"};

        String name, number, oldNumber;

        while (true) {
            int c = ui.getCommand(commands);
            switch (c) {
            case -1:
                ui.sendMessage("You clicked the red x, restarting.");
                break;
            case 0:
                // Ask for the name.
                // !!! Check for null (cancel) or "" (blank)
                // Ask for the number.
                // !!! Check for cancel.  Blank is o.k.
                // Call addOrChangeEntry
                // Report the result
                name = ui.getInfo("Enter the name: ");
                number = pd.lookupEntry(name);
                if (name == null)
                    break;
                if (name.length() == 0)
                {
                    ui.sendMessage("Invalid input for name");
                    break;
                }
                if (number == null)
                {
                    number = ui.getInfo("Enter the number: ");
                    oldNumber = pd.addOrChangeEntry(name, number);
                }
                else
                {
                    number = ui.getInfo("Enter the number: ");
                    oldNumber = pd.addOrChangeEntry(name, number);
                }
                if (oldNumber == null)
                    ui.sendMessage(name + " has been successfully added");
                else
                    ui.sendMessage(name + " has been successfully changed");
                break;
            case 1:
                name = ui.getInfo("Enter the name: ");
                number = pd.lookupEntry(name);
                if (name == null)
                    break;
                if (name.length() == 0)
                {
                    ui.sendMessage("Invalid input for name");
                    break;
                }
                if (number == null)
                {
                    ui.sendMessage(name + " does not exist");
                    break;
                }
                else
                    ui.sendMessage(name + "'s number is " + number);
                break;
            case 2:
                name = ui.getInfo("Enter the name: ");
                number = pd.lookupEntry(name);
                if (name == null)
                    break;
                if (name.length() == 0)
                {
                    ui.sendMessage("Invalid input for name");
                    break;
                }
                if (number == null)
                {
                    ui.sendMessage(name + " does not exist");
                    break;
                }
                pd.removeEntry(name);
                break;
            case 3:
                pd.save();
                break;
            case 4:
                ui.sendMessage("Goodbye!");
                return;
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fn = "csc220.txt";
        PhoneDirectory pd = new SortedPD();
        UserInterface ui = new GUI();
        processCommands(fn, ui, pd);
    }
}
